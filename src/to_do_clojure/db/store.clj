(ns to-do-clojure.db.store
  (:require [clojure.pprint :refer [pprint]]
            [schema.core :as s]
            [to-do-clojure.components :refer [db]]
            [to-do-clojure.models.to-do :as model]))

(s/defn upsert :- model/ToDoEntity
  "Upsert an entity"
  [input :- model/ToDoEntity]
  (swap! db assoc (:id input) input)
  (pprint @db)
  input)

(s/defn get-by-id :- (s/maybe model/ToDoEntity)
  "Get an entity by id"
  [id :- s/Uuid]
  ((get @db id)))