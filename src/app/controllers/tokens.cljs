(ns app.controllers.tokens
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [app.domain.settings :refer [ad-id-token-ls-name ad-access-token-ls-name]]
            [hodgepodge.core :refer [local-storage get-item remove-item]]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))

(derive :tokens ::pipelines/controller)

(defn clear-tokens! []
  (remove-item local-storage ad-id-token-ls-name)
  (remove-item local-storage ad-access-token-ls-name))

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state*]}]
                       (let [id-token (get-item local-storage ad-id-token-ls-name)
                             access-token (get-item local-storage ad-access-token-ls-name)]
                         (pipeline! [value {:keys [state*]}]
                           (reset! state* {:id-token id-token
                                           :access-token access-token}))))

   :log-out (pipeline! [value {:keys [state*] :as ctrl}]
              (clear-tokens!)
              (reset! state* nil))})

(defmethod ctrl/prep :tokens [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :tokens [_ state]
  state)
