(ns to-do-clojure.diplomat.http-server 
  (:require [clojure.data.json :as json]
            [clojure.pprint :refer [pprint]]
            [compojure.core :refer [defroutes GET POST]]
            [schema.core :as s]
            [to-do-clojure.adapters.to-do :refer [wire->to-do-input]]
            [to-do-clojure.db.store :as store]))

(s/defn create-to-do 
  "Create a ToDo"
  [request] 
  (pprint (:body request))
  (let [body ( :body request)
        todo (wire->to-do-input body)]
    (pprint todo)
    (-> todo
        (store/upsert))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str todo)}))

 (s/defn get-to-do
  [id :- s/Uuid]
   (pprint id)
   (pprint (type id))
  (let [todo (store/get-by-id id)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str todo)}))

(defroutes todo-routes 
  (POST "/to-do" req create-to-do)
  (GET "/to-do/:id" req 
    (get-to-do (parse-uuid (:id (:params req))))))