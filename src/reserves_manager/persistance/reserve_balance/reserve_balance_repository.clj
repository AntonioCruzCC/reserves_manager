(ns reserves-manager.persistance.reserve-balance.reserve-balance-repository
  (:require
   [datomic.client.api :as d]
   [reserves-manager.models.reserve-balance :as reserve-balance]
   [reserves-manager.persistance.database :as db]))

(defn save [balance]
  (d/transact (db/get-connection) {:tx-data [{:reserve-balance/reserve [:reserve/name (:name (:reserve balance))]
                               :reserve-balance/balance (:balance balance)
                               :reserve-balance/date (:date balance)}]}))

(defn list-balances [reserve]
  (let [balances-qr (d/q '[:find ?reserve-name ?balance ?date
                            :in $ ?reserve-name
                            :where
                            [?b :reserve-balance/reserve ?r]
                            [?b :reserve-balance/balance ?balance]
                            [?b :reserve-balance/date ?date]
                            [?r :reserve/name ?reserve-name]]
                          (d/db (db/get-connection))
                          (:name reserve))]
    (map #(reserve-balance/->Reservebalance (nth % 0) (nth % 1) (nth % 2)) balances-qr)))

(defn find-latest-balance [reserve]
  (->> (list-balances reserve)
       (apply max-key #(.getTime (:date %)))))
