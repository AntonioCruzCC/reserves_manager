(ns reserves-manager.input.list-reserves-option
  (:require
   [reserves-manager.controllers.reserve-controller :as reserve-controller]))

(defn handle-option []
  (println "List of reserves:")
  (doseq [reserve (reserve-controller/list-reserves)] (println reserve)))
