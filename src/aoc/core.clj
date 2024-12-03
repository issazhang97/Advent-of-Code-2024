(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-file
  "Read a file from source dir, return a vector of line"
  [file-name f]
  (with-open [rdr (io/reader (str "resources/" file-name))]
     (mapv f (line-seq rdr)))
  )

(defn transpose
  "Transpose the matrix"
  [matrix]
  (apply mapv vector matrix))

(defn sum-gap
  []
  (let [matrix (read-file "day1"
                          #(map (fn [x] (Integer/parseInt x))
                                (str/split % #" +")))]
    (let [trans-matrix (transpose matrix)]
      (let [left-list (sort (get trans-matrix 0))
            right-list (sort (get trans-matrix 1))]
        (reduce + (mapv #(abs (- %1 %2)) left-list right-list))))))
