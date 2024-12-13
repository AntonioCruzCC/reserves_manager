(ns reserves-manager.services.reserve-balance-service
  (:require
   [reserves-manager.models.reserve-balance :as reserve-balance-model]
   [reserves-manager.persistance.reserve-balance.reserve-balance-repository :as reserve-balance-repository]))

(defn create-balance [reserve balance-value date]
  (let [balance (reserve-balance-model/->Reservebalance (dissoc reserve :latestbalance) balance-value date)]
    (reserve-balance-repository/save balance)
    balance))

(defn find-latest-balance [reserve]
  (let [latest-balance (reserve-balance-repository/find-latest-balance reserve)]
    (if-not (nil? latest-balance) 
      latest-balance 
      (reserve-balance-model/->Reservebalance reserve 0 (new java.util.Date)))))