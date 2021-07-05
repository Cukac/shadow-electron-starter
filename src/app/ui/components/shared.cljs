(ns app.ui.components.shared
  (:require [helix.dom :as d]
            [keechma.next.helix.classified :refer [defclassified]]))

(defclassified HomepageWrapper  :div "bg-back1 h-screen w-screen home-layout")
(defclassified Menu             :div "w-full h-full flex flex-row bg-back1 justify-between pr-4 relative z-1000")
(defclassified MenuItems        :div "h-full flex flex-row justify-end")
(defclassified DataContainer    :div "w-full h-full bg-back1 flex items-center overflow-scroll")
(defclassified PrimaryButton :button "text-secondary border-2 border-secondary rounded-xl px-8 py-3 ml-8 my-auto")
(defclassified PrimaryLink :a "text-secondary border-2 border-secondary rounded-xl px-8 py-3 mx-auto my-auto")
