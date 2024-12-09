(ns reserves-manager.persistance.reserve.reserve-repository)

(def reserves (atom []))

(defn save [reserve]
  (swap! reserves conj reserve)
  reserve)

(defn list-reserves [] (deref reserves))

(defn find-reserve-by-name [name] (some #(when (= name (:name %)) %) @reserves))
