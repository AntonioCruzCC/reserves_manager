(ns reserves-manager.controllers.reserve-ballance-controller
  (:require [reserves-manager.services.reserve-ballance-service :as reserve-ballance-service]))

(defn new-ballance [reserve ballance-value date]
  (reserve-ballance-service/create-ballance reserve ballance-value date))