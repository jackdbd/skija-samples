# Skijia samples

Some examples on how to use [Skija](https://github.com/JetBrains/skija) in Clojure.

## Bitmap demo

Execute (`X`) function `-main` in namespace `demo.bitmap`, using the alias `linux`

```sh
clj -X:linux demo.bitmap/-main
```

View the generated PNG with [feh](https://feh.finalrewind.org/).

```sh
feh assets/output.png
```

## GLFW + OpenGL demo

```sh
clj -X:linux demo.opengl/-main
```

## Other

See also [this Clojure example from the JetBrains/skija repository](https://github.com/JetBrains/skija/tree/master/examples/clojure).

TODOs:

- [port bitmap scene to Clojure](https://github.com/HumbleUI/Skija/blob/master/examples/scenes/src/BitmapScene.java)
- [port LWJGL example to Clojure](https://github.com/JetBrains/skija/tree/64b715ceb0764e366eb98ff852d3c42fe30328ab/examples/lwjgl)
- [port JWM example to Clojure](https://github.com/JetBrains/skija/tree/64b715ceb0764e366eb98ff852d3c42fe30328ab/examples/jwm)
