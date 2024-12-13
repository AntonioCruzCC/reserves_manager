(ns reserves-manager.input.option-handlers.list-reserves-option
  (:require
   [reserves-manager.controllers.reserve-controller :as reserve-controller]))

(defn pretty-print-reserve [reserve] 
  (println "Reserve:"(:name reserve) "balance:" (:balance (:latestbalance reserve))))

(defn handle-option []
  (println "List of reserves:")
  (doseq [reserve (reserve-controller/list-reserves)] (pretty-print-reserve reserve)))
