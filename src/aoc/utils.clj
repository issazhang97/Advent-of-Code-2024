(ns aoc.utils
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn read-input
  "Read in the content of the given day-file and return as a blob"
  [day]
  (slurp (if (str/starts-with? day "/") day (io/resource day))))

(defn to-lines
  [input]
  (str/split input #"\r"))

(defn parse-out-longs
  [line]
  (map parse-long (re-seq #"[-+]?\d+" line)))


