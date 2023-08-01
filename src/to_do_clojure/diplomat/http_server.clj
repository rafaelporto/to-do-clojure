(ns to-do-clojure.diplomat.http-server 
  (:require [clojure.data.json :as json]
            [clojure.pprint :refer [pprint]]
            [compojure.core :refer [defroutes GET POST]]
            [schema.core :as s]
            [to-do-clojure.adapters.to-do :refer [wire->to-do-input]]
            [to-do-clojure.db.store :as store]
            [to-do-clojure.wire.in.to-do :refer [ToDoInput]]))

(defn- wrap-json-body [handler]
  (fn [request]
    (pprint "Entrou no wrap-json-body")
    (pprint (json/read-str (:body request)))
    (let [request (-> request
                      (assoc :body (json/read-str (:body request)))
                      (assoc :headers (assoc (:headers request) "Content-Type" "application/json")))]
      (pprint (:body request))
      (handler (:body request)))))

(s/defn create-to-do 
  "Create a ToDo"
  [input]
  (pprint input)
  (let [todo (wire->to-do-input input)]
    (-> todo
        (store/upsert))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str todo)}))

 (defn version
  []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (str "0.0.1")})

(defroutes todo-routes
   (wrap-json-body 
    (POST "/to-do" [body]
      (pprint (type body))
      (create-to-do body)))
   (GET "/version" [] (version)))