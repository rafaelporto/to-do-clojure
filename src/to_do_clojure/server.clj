(ns to-do-clojure.server 
  (:require [org.httpkit.server :refer [run-server]]
            [to-do-clojure.diplomat.http-server :refer [todo-routes]]))

(defn -main [& args]
  (run-server todo-routes {:port 8080})
  (println "Server started on port 8080"))