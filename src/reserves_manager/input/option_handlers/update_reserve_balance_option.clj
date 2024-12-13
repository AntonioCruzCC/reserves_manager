(ns reserves-manager.input.option-handlers.update-reserve-balance-option
  (:require
   [reserves-manager.controllers.reserve-controller :as reserve-controller]
   [reserves-manager.input.option-handlers.create-new-reserve-option :as create-new-reserve-option]))

(defn print-reserve-with-option [option reserve]
  (println option "->" (:name reserve))
  {:idx option :reserve reserve})

(defn get-reserve-with-option [option available-reserves]
  (let [long-option (parse-long option)]
    (if (nil? long-option) nil
        (some #(when (= (:idx %) long-option) %) available-reserves))))

(defn handle-option []
  (loop []
    (println "Select the reserve you want to update the balance")
    (let [available-reserves (into [] (map-indexed print-reserve-with-option (reserve-controller/list-reserves)))
          selected-reserve (get-reserve-with-option (read-line) available-reserves)]
      (if (nil? selected-reserve)
        (recur)
        (do
          (create-new-reserve-option/create-reserve-balance (:reserve selected-reserve))
          (println "Reserve balance updated successfully!"))))))