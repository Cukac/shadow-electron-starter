(ns app.ui.main
  (:require [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.core :as hx :refer [$]]
            [helix.dom :as d]
            [keechma.next.toolbox.logging :as l]
            [app.ui.pages.home :refer [Home]]
            [app.ui.pages.login :refer [Login]]))

(defnc MainRenderer [props]
  (let [{:keys [page]} (use-sub props :router)
        tokens (use-sub props :tokens)
        role (use-sub props :role)]
    (case role
      :anon ($ Login)
      :user ($ Home)
      (d/div "404"))))

(def Main (with-keechma MainRenderer))
