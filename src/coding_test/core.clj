(ns coding-test.core
  (:gen-class)
  (:use [clojure.pprint])
  (:require [clj-time.core :as t]
            [clj-time.format :as f]))

(def EVENT-YEAR 2018)
(def EVENT-MONTH 1)
(def FULL-CALENDAR 20)

(defn generate-event
  "Generate an Event with a random start and end time."
  []
  (let [start-day (inc (rand-int 26))
        start-hour (rand-int 12)
        end-hour (+ start-hour (rand-int 12))]
    {:start (t/date-time EVENT-YEAR EVENT-MONTH start-day start-hour)
     :end (t/date-time EVENT-YEAR EVENT-MONTH start-day end-hour)}))

(defn generate-calendar
  "Generate a calendar with FULL-CALENDAR number of events"
  []
  (repeatedly FULL-CALENDAR generate-event))

(defn date-between?
  [date start end]
  (and
   (t/after? date start)
   (t/before? date end)))

(defn events-overlap?
  "Determine if there's an overlap between event-1 and event-2."
  [event-1 event-2]
  (or
   (date-between? (:start event-1) (:start event-2) (:end event-2))
   (date-between? (:end event-1) (:start event-2) (:end event-2))
   (date-between? (:start event-2) (:start event-1) (:end event-1))
   (date-between? (:end event-2) (:start event-1) (:end event-1))
   (t/equal? (:start event-1) (:start event-2))))

(defn make-event-sequence
  "Make all unique pairs of events."
  [calendar]
  (if (= (count calendar) 2)
    (list calendar)
    (concat (make-event-sequence (rest calendar))
            (let [head (first calendar)
                  tail (rest calendar)]
              (map (fn [x] (list head x)) tail)))))

(defn find-overlaps
  "Given a calendar, return seq of all overlaps."
  [calendar]
  (->> calendar
       (make-event-sequence)
       (filter (fn [x] (apply events-overlap? x)))))

(def custom-formatter (f/formatter "yyyy-MM-dd-H"))

(defn pretty-overlap
  "Prettify datetimes in overlap."
  [overlap]
  (let [[event-1 event-2] overlap
        {ev-start-1 :start
         ev-end-1   :end} event-1
        {ev-start-2 :start
         ev-end-2   :end} event-2]
    (list
     {:start (f/unparse custom-formatter ev-start-1)
      :end (f/unparse custom-formatter ev-end-1)}
     {:start (f/unparse custom-formatter ev-start-2)
      :end (f/unparse custom-formatter ev-end-2)})))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [c        (generate-calendar)
        overlaps (find-overlaps c)]
    (when (not (empty? overlaps))
      (println "The following overlaps were found:")
      (pprint (map pretty-overlap overlaps)))))
