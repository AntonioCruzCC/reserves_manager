(ns reserves-manager.input.menu
  (:require 
   [reserves-manager.input.create-new-reserve-option :as create-new-reserve-option]
   [reserves-manager.input.list-reserves-option :as list-reserves-option]))

(declare print-main-menu)

(defn handle-main-menu-input [command]
  (case command
    "1" (create-new-reserve-option/handle-option)
    "2" (list-reserves-option/handle-option)
    (println "Unsupported operation!"))
  (if (not-empty command) (print-main-menu)))


(defn print-main-menu []
  (println "Welcome to the reserves manager system, choose the option to execute:
1-> Create a new reserve;
2-> List reserves;")
  (handle-main-menu-input (read-line)))

(print-main-menu)