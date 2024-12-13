(ns reserves-manager.controllers.reserve-balance-controller
  (:require [reserves-manager.services.reserve-balance-service :as reserve-balance-service]))

(defn new-balance [reserve balance-value date]
  (reserve-balance-service/create-balance reserve balance-value date))