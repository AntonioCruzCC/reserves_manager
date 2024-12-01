(ns reserves-manager.services.reserve-ballance-service
  (:require
   [reserves-manager.models.reserve-ballance :as reserve-ballance-model]
   [reserves-manager.persistance.reserve-ballance-repository :as reserve-ballance-repository]))

(defn create-ballance [reserve ballance-value date]
  (let [ballance (reserve-ballance-model/->ReserveBallance reserve ballance-value date)]
    (reserve-ballance-repository/save ballance)))

(defn find-latest-ballance [reserve]
  (reserve-ballance-repository/find-latest-ballance reserve))