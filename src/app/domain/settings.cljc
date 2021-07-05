(ns app.domain.settings
  #?(:cljs (:require-macros [app.domain.settings :refer [env-var]])))

#?(:clj
   (defmacro env-var [env-var-name]
     (System/getenv env-var-name)))

(def backend-endpoint (env-var "BACKEND_URL"))

(def jwt-ls-name "als-education-jwt")

(def ad-id-token-ls-name "ad-id-token")
(def ad-access-token-ls-name "ad-access-token")

(def ad-query-params
  {:authorize-url "https://login.microsoftonline.com/60c86d26-b84a-4f67-b697-ce6ed68d1552/oauth2/v2.0/authorize"
   :client-id "1cbf02d2-20f2-487a-bbab-8f705c437033"
   :redirect-uri "https://als-demo.verybigthings.com/azure-ad/callback"
   :response-type "id_token token"
   :scope "openid email profile"
   :state #js{:page "login"}})
