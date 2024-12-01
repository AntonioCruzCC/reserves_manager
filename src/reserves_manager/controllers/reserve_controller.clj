(ns reserves-manager.controllers.reserve-controller
  (:require [reserves-manager.services.reserve-service :as reserve-service]))


(defn new-reserve [name]
  (reserve-service/create-reserve name))

(defn list-reserves [] (reserve-service/list-reserves))
