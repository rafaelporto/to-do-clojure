(ns to-do-clojure.diplomat.http-server 
  (:require [clojure.pprint :refer [pprint]]))

(defn home [req]
  (pprint "Entrou aqui")
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "Hello world!")})

(defn version
  []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (str "0.0.1")})

(def todo-routes
  #{["/api/home"
     :get home
     :route-name :index]
  })