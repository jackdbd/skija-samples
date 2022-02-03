(ns demo.bitmap
  (:require
   [clojure.java.io :as io])
  (:import
   (org.jetbrains.skija Canvas Paint PaintMode Path Point Surface)))

(set! *warn-on-reflection* true)

(defn color
  "Cast a 0x value to an integer. A 0x color is alpha-red-green-blue."
  [^long l]
  (.intValue (Long/valueOf l)))

(def RED (color 0xFFFF0000))
(def GREEN (color 0xFF00FF00))
(def BLUE (color 0xFF0000FF))

(defn write-file!
  [filepath bytes]
  (with-open [writer (io/output-stream filepath)]
    (.write writer bytes)))

(defn draw-triangle!
  "Draw a triangle on a canvas."
  [{:keys [^Canvas canvas p0 p1 p2]}]
  (let [paint (Paint.)
        positions (into-array Point [p0 p1 p2])
        colors (into-array Integer/TYPE [(color 0xFFFF0000) (color 0xFF00FF00) (color 0xFF0000FF)])]
    (.drawTriangles canvas positions colors paint)))

(defn draw-path!
  "Draw a path on a canvas."
  [{:keys [^Canvas canvas color]
    :or {color BLUE}}]
  (let [paint (-> (Paint.)
                  (.setColor color)
                  (.setMode (PaintMode/STROKE))
                  (.setStrokeWidth 3.5))
        path (-> (Path.)
                 (.moveTo 253 216)
                 (.cubicTo 283 163.5 358 163.5 388 216)
                 (.cubicTo 358 268.5 283 268.5 253 216)
                 (.closePath))]
    (.drawPath canvas path paint)))

(defn draw-circle!
  "Draw a circle on a canvas."
  [{:keys [^Canvas canvas color cx cy radius]
    :or {color RED}}]
  (let [paint (-> (Paint.)
                  (.setColor color))]
    (.drawCircle canvas cx cy radius paint)))

(defn -main [& _args]
  (let [width 640
        height 480
        ;; create an in-memory, bitmap-backed drawing surface
        ;; https://github.com/JetBrains/skija/blob/64b715ceb0764e366eb98ff852d3c42fe30328ab/shared/java/Surface.java#L298
        surface (Surface/makeRasterN32Premul width height)
        canvas (.getCanvas surface)]

    (.clear canvas (color 0xFFFFFFFF))

    (draw-triangle! {:canvas canvas
                     :p0 (Point. (/ width 2) (/ height 10))
                     :p1 (Point. (/ width 10) (- height (/ height 10)))
                     :p2 (Point. (- width (/ width 10)) (- height (/ height 10)))})

    (draw-path! {:canvas canvas})
    ;; (draw-path! {:canvas canvas :color (color 0xFF00FF00)})

    (draw-circle! {:canvas canvas
                   :cx (/ width 2)
                   :cy (/ height 2)
                   :radius 25
                   :color (color 0xFFFFA500)})

    (let [png-bytes (-> (.makeImageSnapshot surface)
                        (.encodeToData)
                        (.getBytes))]
      (write-file! "assets/output.png" png-bytes))

    (.close surface)))
