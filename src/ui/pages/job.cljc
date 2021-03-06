(ns ui.pages.job
  (:require [rum.core :as rum]
            [scrum.core :as scrum]
            [ui.base :as base]))

(rum/defc Layout < rum/reactive [r]
  (let [{:keys [from to total items]}
        (rum/react (scrum/subscription r [:job-posts]))
        current (when (pos? total) (/ to (- to from)))
        total (when (pos? total) (->> (- to from) (/ total) Math/ceil int))]
    [:main.layout
     (base/Pagination
       {:slug "jobs"
        :current current
        :total total})
     [:div.page-content
      (map #(rum/with-key (base/PostItem %) (:id %)) items)]]))
