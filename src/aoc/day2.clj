(ns aoc.day2
  (:require [aoc.utils :as utils]))

(defn- all-safe-gap?
  "Filter each candidate by the safe gaps rules"
  [s]
  (let [pairs (partition 2 1 s)]
    (every? #(and (>= % 1) (<= % 3))
            (map #(abs (- (first %) (last %))) pairs))))

(defn- is-ordered?
  "Is the sequence ordered, either asc or desc?"
  [s]
  (or (apply < s) (apply > s)))

(defn part-1
  "Day 02 part 1"
  [input]
  (->> input
       utils/read-input
       utils/to-lines
       (map utils/parse-out-longs)
       (filter is-ordered?)
       (filter all-safe-gap?)
       count))

(defn- drop-nth
  [l n]
  (concat (take n l) (drop (inc n) l)))

(defn- all-versions
  [s]
  (let [size (count s)]
    (loop [[n & ns] (range size) versions (list s)]
      (if (nil? n)
        versions
        (recur ns (conj versions (drop-nth s n)))))))

(defn- is-viable?
  [vs]
  (some #(and (is-ordered? %) (all-safe-gap? %)) vs))

(defn part-2
  [input]
  (->> input
       utils/read-input
       utils/to-lines
       (map utils/parse-out-longs)
       (map all-versions)
       (filter is-viable?)
       count))