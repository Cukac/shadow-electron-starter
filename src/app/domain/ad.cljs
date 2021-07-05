(ns app.domain.ad
  (:require [router.util :refer [encode-query-params]]
            [app.domain.settings :refer [ad-query-params]]
            [hodgepodge.core :refer [local-storage get-item]]))

(defn get-common-query-params [{:keys [client-id redirect-uri response-type scope state]}]
  {:client_id client-id
   :response_type response-type
   :nonce "asdfghjkl"
   :redirect_uri redirect-uri
  ;;:redirect_uri "http://localhost:3000/azure_ad/callback"
   :scope scope
   :state state})

(def get-oauth-url
  (str (:authorize-url ad-query-params) "?" (encode-query-params (get-common-query-params ad-query-params))))

(def get-refresh-token-url
  (str (:authorize-url ad-query-params) "?"
       (encode-query-params (get-common-query-params ad-query-params))))
