(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.entitydb]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]

            ["react-dom" :as rdom]
            [app.controllers.tokens]
            [app.controllers.items]))

(defn page-eq? [router page]
  (= page (:page router)))

(defn role-eq? [role] (fn [deps] (= role (:role deps))))

(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates
   :keechma/controllers
   {:router {:keechma.controller/params true
             :keechma.controller/type :keechma/router
             :keechma/routes [["" {:page "home"}] ":page" ":page/:subpage"]}
    :entitydb   {:keechma.controller/params true
                 :keechma.controller/type :keechma/entitydb}
    :tokens #:keechma.controller {:params true}
    :role #:keechma.controller {:params (fn [{:keys [tokens]}]
                                          (if (and (:id-token tokens)
                                                   (:access-token tokens))
                                            :user
                                            :anon))
                                :deps [:tokens]
                                :type :keechma/subscription}
    :items  #:keechma.controller {:params true}}})
