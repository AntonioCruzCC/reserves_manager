(ns reserves-manager.persistance.database
  (:require [datomic.client.api :as d]
            [reserves-manager.persistance.initialization.namespace-discovery-service :as namespace-discovery-service]))

(def client (d/client {:server-type :datomic-local
                       :system "reserves_manager"
                       :storage-dir "C:/Users/anton/Documents/datomic-databases"}))

(defn get-connection []
  (d/connect client {:db-name "reserves-manager"}))

(defn start-db
  "Create a database and return the connection"
  []
  (d/create-database client {:db-name "reserves-manager"})
  (get-connection))

(defn get-schema-from-namespace [namespace]
  (let [ns-symbol (symbol namespace)]
    (require ns-symbol)
    @(ns-resolve ns-symbol 'schema)))

(defn load-schema-files [base-dir]
  (map #(get-schema-from-namespace %) (namespace-discovery-service/find-schema-namespaces base-dir)))

(defn ensure-schema [conn]
  (let [current-schema (d/q '[:find ?ident
                              :where [?e :db/ident ?ident]]
                            (d/db conn))]
    (doseq [schema-file (load-schema-files "src/reserves_manager/persistance")]
      (doseq [schema-attribute schema-file]
        (when-not (contains? (set current-schema) [(:db/ident schema-attribute)])
          (d/transact conn {:tx-data [schema-attribute]}))))))


(defn initialize-db []
  (let [conn (start-db)]
    (ensure-schema conn)
    (println "Database initialized")
    conn))
