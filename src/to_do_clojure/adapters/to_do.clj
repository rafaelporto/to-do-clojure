(ns to-do-clojure.adapters.to-do
  (:require [schema.core :as s]
            [to-do-clojure.models.to-do :as model]
            [to-do-clojure.wire.in.to-do :as wire-in]))

(s/defn wire->to-do-input :- model/ToDoInput
  "Convert a wire input to a ToDo"
  [{:keys [id title description due-date priority]} :- wire-in/ToDoInput]
  {:id (if (= id nil) 0 id)
   :title title
   :description description
   :due-date due-date
   :priority priority})