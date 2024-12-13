(ns reserves-manager.input.menu
  (:require 
   [reserves-manager.input.option-handlers.create-new-reserve-option :as create-new-reserve-option]
   [reserves-manager.input.option-handlers.list-reserves-option :as list-reserves-option]
   [reserves-manager.input.option-handlers.update-reserve-balance-option :as update-reserve-balance-option]))

(declare print-main-menu)

(defn handle-main-menu-input [command]
  (case command
    "1" (create-new-reserve-option/handle-option)
    "2" (list-reserves-option/handle-option)
    "3" (update-reserve-balance-option/handle-option)
    (println "Unsupported operation!"))
  (when (not-empty command) (print-main-menu)))


(defn print-main-menu []
  (println "Welcome to the reserves manager system, choose the option to execute:
1-> Create a new reserve;
2-> List reserves;
3-> Update reserve balance")
  (handle-main-menu-input (read-line)))
