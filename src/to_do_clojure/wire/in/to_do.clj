(ns to-do-clojure.wire.in.to-do
  (:require [schema.core :as s]))

(s/defschema ToDoInput
  "The input schema for a ToDo"
  {:id s/Int
   :title s/Str
   :description s/Str
   :due-date s/Str
   :priority s/Int})