(ns app.ui.pages.login
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [app.domain.ad :refer [get-oauth-url]]
            [app.ui.components.shared :refer [PrimaryLink]]))

(defnc Renderer [props]
  (d/div {:class "bg-back1 text-primary w-screen h-screen grid grid-cols-1 grid-rows-3 justify-items-center items-end"}
         (d/div {:class "text-3xl"}
                "Login with Microsoft")
         ($ PrimaryLink
            {:href get-oauth-url}
            "Sign In")))

(def Login (with-keechma Renderer))
