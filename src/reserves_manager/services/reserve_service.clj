(ns reserves-manager.services.reserve-service
  (:require
   [reserves-manager.models.reserve :as reserve-model]
   [reserves-manager.persistance.reserve.reserve-repository :as reserve-repository]
   [reserves-manager.services.reserve-ballance-service :as reserve-ballance-service]))

(defn assoc-latest-ballance [reserve]
  (assoc reserve :latestBallance (reserve-ballance-service/find-latest-ballance reserve)))

(defn list-reserves []
  (map #(assoc-latest-ballance %) (reserve-repository/list-reserves)))

(defn find-by-name [name] (reserve-repository/find-reserve-by-name name))

(defn create-reserve [name]
  (if (find-by-name name) (throw (Exception. "A reserve with this name already exists!")))
  (let [reserve (reserve-model/->Reserve name)]
    (reserve-repository/save reserve)))

