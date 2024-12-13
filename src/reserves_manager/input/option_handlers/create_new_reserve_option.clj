(ns reserves-manager.input.option-handlers.create-new-reserve-option
  (:require
   [reserves-manager.controllers.reserve-controller :as reserve-controller]
   [reserves-manager.controllers.reserve-balance-controller :as reserve-balance-controller]))

(defn parse-currency []
  (let [reserve-balance (parse-double (read-line))]
    (when (nil? reserve-balance)
      (println "The new reserve balance must be a number"))
    reserve-balance))

(defn create-reserve-balance [reserve]
  (loop []
    (println "Type the current balance of the new reserve")
    (let [balance (parse-currency)]
      (if (nil? balance)
        (recur)
        (let [ballace (reserve-balance-controller/new-balance reserve balance (new java.util.Date))]
          (assoc reserve :latestbalance ballace))))))

(defn handle-option []
  (println "Type the name of the new reserve, or empty to go back")
  (let [reserve-name (read-line)]
    (when (not-empty reserve-name)
      (try
        (let [new-reserve (reserve-controller/new-reserve reserve-name)]
          (create-reserve-balance new-reserve)
          (println "balance created successfully")
          )
        (catch Exception e (println (.getMessage e)))))))
