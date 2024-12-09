(ns reserves-manager.core
  (:require [reserves-manager.input.menu :as menu]
            [reserves-manager.persistance.database :as database])
  (:gen-class))

(defn -main
  "Starting point of the application"
  [& _args]
  (database/initialize-db)
  (menu/print-main-menu))

