(ns to-do-clojure.server 
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.json :refer [wrap-json-body]]
            [schema.core :as s]
            [to-do-clojure.diplomat.http-server :refer [todo-routes]]))

(def to-do-server
  (run-server 
   (wrap-json-body todo-routes {:keywords? true}) {:port 8080}))

(defn stop-to-do-server [timeout]
  (to-do-server :timeout timeout))

(defn -main [& args]
  (s/set-fn-validation! true)
  (run-server
    (wrap-json-body todo-routes {:keywords? true}) {:port 8080})
  (println "Server started on port 8080"))