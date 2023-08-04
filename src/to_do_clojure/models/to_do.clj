(ns to-do-clojure.models.to-do 
  (:require [schema.core :as s]))

(s/defschema ToDoEntity
  "The input schema for a ToDo"
  {:id s/Uuid
   :title s/Str
   :description s/Str
   :due-date s/Str
   :priority s/Int
   :completed s/Bool})