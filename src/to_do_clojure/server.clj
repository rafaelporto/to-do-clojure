(ns to-do-clojure.server 
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.json :refer [wrap-json-body]]
            [schema.core :as s]
            [to-do-clojure.diplomat.http-server :refer [todo-routes]]))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (s/set-fn-validation! true)
  (reset! server 
          (run-server (wrap-json-body todo-routes {:keywords? true}) {:port 8080}))
  (println "Server started on port 8080"))