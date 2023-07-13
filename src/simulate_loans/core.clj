(ns simulate-loans.core
  (:require [clojure.math :refer [pow]]
            [clj-time.core :as t]
            [clj-time.format :as f]))

(def iof-additional (/ 0.38 100))
(def iof-daily (/ 0.0082 100))


(defn str->date
  [string-date]
  (f/parse (f/formatter "dd MM YYYY") string-date))

(defn sum-month-date
  [{:keys [date n-month]}]
  (t/plus date (t/months n-month)))

(defn array-date
  [{:keys [date n-month]}]
  (println (sum-month-date {:date date :n-month (- n-month 1)}))
  (if (> n-month 0)
    (recur {:date date :n-month (- n-month 1)})))

(println (array-date {:date (str->date "6 7 2023") :n-month 5}))

(defn pmt
  "Pagamento de mesmo valor"
  [loan-amount
   interest-rate
   n-installments]
  (let [term-1 (* (pow (+ 1 interest-rate) n-installments) interest-rate)
        term-2 (- (pow (+ 1 interest-rate) n-installments) 1)]
    (* loan-amount (/ term-1 term-2))))

(defn simulate-loans
  [loan-amount
   interest-rate
   n-installments]
  (pmt loan-amount (/ interest-rate 100) n-installments))

(println (simulate-loans 100000 2.16 18))

