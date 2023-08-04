(ns to-do-clojure.adapters.to-do
  (:require [schema.core :as s]
            [to-do-clojure.models.to-do :as model]
            [to-do-clojure.wire.in.to-do :as wire-in]))

(s/defn wire->to-do-input :- model/ToDoEntity
  "Convert a wire input to a ToDo"
  [{:keys [id title description due-date priority completed]} :- wire-in/ToDoInput]
  {:id (if (= id nil)
         (random-uuid)
         (parse-uuid id))
   :title title
   :description description
   :due-date due-date
   :priority priority
   :completed completed})