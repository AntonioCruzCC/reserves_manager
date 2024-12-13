(ns reserves-manager.services.reserve-service
  (:require
   [reserves-manager.models.reserve :as reserve-model]
   [reserves-manager.persistance.reserve.reserve-repository :as reserve-repository]
   [reserves-manager.services.reserve-balance-service :as reserve-balance-service]))

(defn assoc-latest-balance [reserve]
  (assoc reserve :latestbalance (reserve-balance-service/find-latest-balance reserve)))

(defn list-reserves []
  (map #(assoc-latest-balance %) (reserve-repository/list-reserves)))

(defn find-by-name [name] (reserve-repository/find-reserve-by-name name))

(defn create-reserve [name]
  (when (find-by-name name) (throw (Exception. "A reserve with this name already exists!")))
  (let [reserve (reserve-model/->Reserve name)]
    (reserve-repository/save reserve)
    reserve))

