(ns reserves-manager.persistance.reserve-ballance-repository)

(def ballances (atom []))

(deref ballances)

(defn save [ballance]
  (swap! ballances conj ballance)
  ballance)

(defn list-ballances [reserve]
  (filter #(= (:name (:reserve %)) (:name reserve)) @ballances))

(defn find-latest-ballance [reserve]
  (let [reserve-ballances (list-ballances reserve)]
   	(reduce (fn [latest current]
                (if (> (.getTime (:date current)) (.getTime (:date latest)))
                  current
                  latest))
              (first reserve-ballances)
              (rest reserve-ballances))))


