(ns to-do-clojure.db.store
  (:require [to-do-clojure.components :refer [db]]
            [to-do-clojure.models.to-do :as model]
            [schema.core :as s]))

(s/defn upsert :- model/ToDoInput
  "Upsert an entity"
  [input :- model/ToDoInput]
  (swap! db assoc (:id input) input)
  input)