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
  [date n-month]
  (t/plus date (t/months n-month)))

#_(defn array-date
    ([date n-month]
     (array-date date n-month []))
    ([date n-month fila]
     (if (> n-month 0)
       (->> (conj fila (sum-month-date date (- n-month 1)))
            (recur date (- n-month 1)))
       fila)))

#_(defn array-date
    [inicial-date n-month]
    (for [month n-month
          :let [date (sum-month-date inicial-date month)]]
      date))

(defn array-date
  [inicial-date n-month]
  (for [month n-month]
    (sum-month-date inicial-date month)))

(map #(sum-month-date (str->date "6 7 2023") %) (range 5))

(println (array-date (str->date "6 7 2023") (range 5)))

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
  {:pmt (pmt loan-amount (/ interest-rate 100) n-installments)})

#_(println (simulate-loans 100000 2.16 18))

