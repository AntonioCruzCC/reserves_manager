(ns reserves-manager.persistance.initialization.namespace-discovery-service
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn find-schema-namespaces [base-dir]
  (->> (file-seq (io/file base-dir))
       (filter #(str/ends-with? (.getName %) "schema.clj"))
       (map #(-> (.getPath %)
                 (str/replace "src" "")
                 (str/replace "_" "-")
                 (str/replace #"\\" ".")
                 (str/replace #".clj$" "")
                 (str/replace #"^\." "")))))
