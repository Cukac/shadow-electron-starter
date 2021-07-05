(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.toolbox.logging :as l]
            [app.domain.mock :refer [mock-data]]
            [app.ui.components.shared :refer [HomepageWrapper Menu MenuItems DataContainer PrimaryButton]]))

(defnc HomeRenderer [props]
  (let [items (use-sub props :items)
        active-items (get-in items [:active-items])
        current-item (get-in items [:current-item])]
    ($ HomepageWrapper
       ($ DataContainer
          (map-indexed
           (fn [idx item]
             (d/iframe {:key idx
                        :src (:url item)
                        :title (:text item)
                        :class (if (= (:url item) (:url current-item))
                                 "w-full h-full top-0 absolute z-10"
                                 "w-full h-full top-0 absolute z-0")}))
           active-items))

       ($ Menu
          ($ PrimaryButton {:on-click #(dispatch props :tokens :log-out)}
             "Log Out")
          ($ MenuItems
             (map-indexed
              (fn [idx {:keys [src url text] :as sidebar_element}]
                (d/div {:key idx
                        :class "bg-back1 text-primary cursor-pointer flex flex-col items-center justify-center p-2"
                        :on-click #(dispatch props :items :add-item sidebar_element)}
                       (d/div {:class (if (= url (:url current-item))
                                        "flex rounded-xl w-12 h-12 bg-white border-2 border-solid border-secondary justify-center items-center"
                                        "flex rounded-xl w-12 h-12 bg-white border-2 border-solid border-black justify-center items-center")}
                              (d/img {:src  src
                                      :class "w-5/6"}))
                       (d/div {:class "text-xs mt-3 relative"}
                              (d/div {:class (if (not-empty (filter #(= sidebar_element %) active-items))
                                               "bg-secondary rounded h-2 w-2 mx-auto relative -top-1"
                                               "bg-back1 rounded h-2 w-2 mx-auto relative -top-1")})
                              text)))
              mock-data))))))

(def Home (with-keechma HomeRenderer))
