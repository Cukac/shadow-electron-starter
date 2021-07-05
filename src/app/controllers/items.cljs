(ns app.controllers.items
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.toolbox.logging :as l]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.form :as form]
            [keechma.next.controllers.router :as router]
            [app.domain.mock :refer [mock-data]]))

(derive :items ::pipelines/controller)

(def pipelines
  {:add-item    (pipeline! [value {:keys [state*]}]
                  (println "Value: " value)
                  (let [active-items (:active-items @state*)
                        contains-item (contains? (set active-items) value)]
                    (when-not contains-item
                               ;; Add new item to active items
                      (pp/swap! state* assoc :active-items (conj active-items value)))

                               ;; Set new item as current
                    (pp/swap! state* assoc :current-item value)))

   :remove-item (pipeline! [value {:keys [state*]}]
                  (let [active-items (:active-items @state*)]
                             ;; Remove item from active items
                    (pp/swap! state* assoc :active-items (remove #(= value %) active-items))

                             ;; HERE: Add logic for selecting next current item
                    ))
   :set-current (pipeline! [value {:keys [state*]}]
                  (pp/swap! state* assoc :current-item value))})

(defmethod ctrl/prep :items [ctrl]
  (-> ctrl
      (pipelines/register pipelines)))

(defmethod ctrl/start :items [_ state _ _]
  {:active-items [(first mock-data)]
   :items mock-data
   :current-item (first mock-data)})
