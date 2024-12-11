(ns reserves-manager.persistance.reserve.reserve-repository
  (:require
   [datomic.client.api :as d]
   [reserves-manager.persistance.database :as db]
   [reserves-manager.models.reserve :as reserve-model]))

(def conn (db/get-connection))

(defn save [reserve]
  (d/transact conn {:tx-data [{:reserve/name (:name reserve)}]}))

(defn list-reserves []
  (let [reserve-names (d/q '[:find ?name
                             :where
                             [_ :reserve/name ?name]]
                           (d/db conn))]
    (map #(reserve-model/->Reserve (first %)) (set reserve-names))))

(defn find-reserve-by-name [name]
  (let [reserve-names (d/q '[:find ?name
                            :in $ ?n
                            :where
                            [?r :reserve/name ?n]
                            [?r :reserve/name ?name]]
                          (d/db conn)
                          name)]
    (println reserve-names)
    (when (not-empty reserve-names) (reserve-model/->Reserve (ffirst reserve-names)))))
