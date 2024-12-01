(ns reserves-manager.core
  (:require [reserves-manager.input.menu :as menu])
  (:gen-class))

(defn -main
  "Starting point of the application"
  [& _args]
  (menu/print-main-menu))

