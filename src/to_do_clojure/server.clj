(ns to-do-clojure.server 
  (:require [org.httpkit.server :refer [run-server]]
            [to-do-clojure.diplomat.http-server :refer [todo-routes]]
            [to-do-clojure.diplomat.http-server :refer [home]]))

(defn -main [& args]
  (run-server home {:port 8080})
  (println "Server started on port 8080"))