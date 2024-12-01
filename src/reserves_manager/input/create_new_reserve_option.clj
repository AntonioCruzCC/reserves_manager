(ns reserves-manager.input.create-new-reserve-option
  (:require
   [reserves-manager.controllers.reserve-controller :as reserve-controller]
   [reserves-manager.controllers.reserve-ballance-controller :as reserve-ballance-controller]))

(defn parse-currency []
  (let [reserve-ballance (parse-double (read-line))]
    (if (nil? reserve-ballance)
      (println "The new reserve ballance must be a number"))
    reserve-ballance))

(defn create-reserve-ballance [reserve]
  (loop []
    (println "Type the current ballance of the new reserve")
    (let [ballance (parse-currency)]
      (if (nil? ballance)
        (recur)
        (let [ballace (reserve-ballance-controller/new-ballance reserve ballance (new java.util.Date))]
          (println "Ballance created and linked to the reserve")
          (assoc reserve :latestBallance ballace))))))

(defn handle-option []
  (println "Type the name of the new reserve, or empty to go back")
  (let [reserve-name (read-line)]
    (if (not-empty reserve-name)
      (try
        (let [new-reserve (reserve-controller/new-reserve reserve-name)]
          (create-reserve-ballance new-reserve))
        (catch Exception e (println (.getMessage e)))))))
