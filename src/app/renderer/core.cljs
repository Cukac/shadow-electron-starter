(ns app.renderer.core
  (:require [camel-snake-kebab.core :as csk]
            [oops.core :refer [ocall oget]]
            [hodgepodge.core :refer [local-storage set-item]]
            ["react" :as react]
            ["react-dom" :as rdom]
            [helix.core :as hx :refer [$]]
            [keechma.next.core :as keechma]
            [router.util :refer [decode-query-params]]
            [keechma.next.helix.core :refer [KeechmaRoot]]
            [keechma.next.toolbox.logging :as l]
            [app.app :refer [app]]
            [app.ui.main :refer [Main]]
            [app.domain.settings :refer [ad-id-token-ls-name ad-access-token-ls-name]]))

(enable-console-print!)

(defonce app-instance* (atom nil))

(def query-params-names #{"access_token" "token_type" "state" "session_state" "id_token"})

(defn get-query-params []
  (let [hash (oget js/window :location.hash)
        params  (if (empty? hash)
                  {}
                  (-> (decode-query-params (subs hash 1))
                      (select-keys query-params-names)))
        params' (reduce-kv (fn [acc k v]
                             (if k
                               (assoc acc (keyword (csk/->kebab-case k)) v)
                               acc)) {} params)]
    params'))

(defn handle-ad-token [query-params]
  (let [id-token (:id-token query-params)
        access-token (:access-token query-params)]
    (when id-token
      (set-item local-storage ad-id-token-ls-name id-token))
    (when access-token
      (set-item local-storage ad-access-token-ls-name access-token))))


(defn render
  []
  (when-let [app-instance @app-instance*] (keechma/stop! app-instance))
  (let [app-instance (keechma/start! app)
        processed-params (get-query-params)]
    (handle-ad-token processed-params)
    (ocall js/window :history.replaceState nil nil (str "/" (oget js/window :location.hash)))
    (reset! app-instance* app-instance)
    (rdom/render ($ react/StrictMode
                    ($ KeechmaRoot {:keechma/app app-instance} ($ Main)))
                 (js/document.getElementById "app-container"))))

(defn ^:dev/after-load reload
  "Render the toplevel component for this app."
  []
  (rdom/unmountComponentAtNode (js/document.getElementById "app-container"))
  (render))

(defn ^:export main "Run application startup logic." [] (render))
