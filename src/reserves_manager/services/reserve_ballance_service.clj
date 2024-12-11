(ns reserves-manager.services.reserve-ballance-service
  (:require
   [reserves-manager.models.reserve-ballance :as reserve-ballance-model]
   [reserves-manager.persistance.reserve-ballance.reserve-ballance-repository :as reserve-ballance-repository]))

(defn create-ballance [reserve ballance-value date]
  (let [ballance (reserve-ballance-model/->ReserveBallance (dissoc reserve :latestBallance) ballance-value date)]
    (reserve-ballance-repository/save ballance)))

(defn find-latest-ballance [reserve]
  (let [latest-ballance (reserve-ballance-repository/find-latest-ballance reserve)]
    (if-not (nil? latest-ballance) 
      latest-ballance 
      (reserve-ballance-model/->ReserveBallance reserve 0 (new java.util.Date)))))