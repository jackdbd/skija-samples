(ns lib.utils
  (:import
   [org.lwjgl.glfw GLFW]
   [org.lwjgl.system MemoryUtil]))

(set! *warn-on-reflection* true)

(defn display-scale
  "todo docs"
  [window]
  (let [x (make-array Float/TYPE 1)
        y (make-array Float/TYPE 1)]
    (GLFW/glfwGetWindowContentScale window x y)
    [(first x) (first y)]))

(defn window
  "Create a window using GLFW"
  [{:keys [width height title]}]
  (GLFW/glfwCreateWindow width height title MemoryUtil/NULL MemoryUtil/NULL))
