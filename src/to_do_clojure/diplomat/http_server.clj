(ns to-do-clojure.diplomat.http-server 
  (:require [clojure.data.json :as json]
            [clojure.pprint :refer [pprint]]
            [compojure.core :refer [defroutes GET POST]]
            [schema.core :as s]
            [to-do-clojure.adapters.to-do :refer [wire->to-do-input]]
            [to-do-clojure.db.store :as store]
            [to-do-clojure.wire.in.to-do :refer [ToDoInput]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(s/defn create-to-do 
  "Create a ToDo"
  [request] 
  (pprint (:body request))
  (let [body ( :body request)
        todo (wire->to-do-input body)]
    (-> todo
        (store/upsert))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str todo)}))

 (s/defn get-to-do
  [id :- s/Int]
  (let [todo (store/get-by-id id)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str todo)}))

(defroutes todo-routes 
  (POST "/to-do" req
    (create-to-do req))
  (GET "/to-do/:id" req (get-to-do (:id (:params req)))))