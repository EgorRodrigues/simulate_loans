(ns simulate-loans.logic)

(defn testa-fila []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
    (println "fila")
    (println espera)
    (println (seq espera))
    (println (seq (conj espera "333")))
    (println (seq (pop espera)))
    (println (peek espera))))

(testa-fila)