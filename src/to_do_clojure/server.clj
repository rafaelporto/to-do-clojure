(ns to-do-clojure.server 
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.json :refer [wrap-json-body]]
            [to-do-clojure.diplomat.http-server :refer [todo-routes]]))

(defn -main [& args]
  (run-server (wrap-json-body todo-routes {:keywords? true }) {:port 8080})
  (println "Server started on port 8080"))