(ns to-do-clojure.wire.in.to-do
  (:require [schema.core :as s]))

(s/defschema ToDoInput
  "The input schema for a ToDo"
  {(s/optional-key :id) s/Str
   :title s/Str
   :description s/Str
   :due-date s/Str
   :priority s/Int
   :completed s/Bool})