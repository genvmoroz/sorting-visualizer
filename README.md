# Sorting Visualizer

A small Java **Swing** application that animates classic sorting algorithms. Bars represent values;
as an algorithm runs, the window recolors bars to show comparisons and swaps in real time while a
text menu in the terminal drives the app.

## Requirements

- **JDK 25** (LTS). Verify with `java -version`.
- Maven — or just use the bundled wrapper (`./mvnw`), no install needed.

## Build & test

```bash
./mvnw clean package
```

This compiles against Java 25, runs the unit tests (every algorithm is checked to actually sort),
and produces a runnable jar at `target/sorting-visualizer-2.0.0.jar`.

## Run

Run straight from sources (the wrapper already uses JDK 25):

```bash
./mvnw -q exec:java
```

or launch the packaged jar. The jar is compiled for Java 25, so launch it with a Java 25 runtime
(`/usr/libexec/java_home -v 25` finds it for you regardless of your default `java`):

```bash
"$(/usr/libexec/java_home -v 25)/bin/java" -jar target/sorting-visualizer-2.0.0.jar
```

A black window opens. Drive it from the terminal:

```
=== Sorting Visualizer ===
  1. Add bars                 # add N random-height bars
  2. Shuffle bars             # re-randomize the current bars
  3. Run a sorting algorithm  # pick one and watch it sort
  0. Exit
```

Typical flow: `1` → `100` (add 100 bars) → `3` → pick an algorithm. Use `2` to reshuffle and try
another. Animation speed scales with bar count and window width.

## Algorithms

Exchange · Bubble · Selection · Cocktail (bidirectional bubble) · Gnome · Quick · Comb · Heap · Shell.

## Project layout

```
src/main/java/com/frost/sortviz
├── Main.java            # entry point + console menu (registry-driven)
├── Bar.java             # one bar: value + color + how to draw itself
├── sorting/             # Sort base class + the nine algorithms
└── view/                # Visualization (JFrame) + VisualizationPanel (JPanel canvas)
src/test/java/com/frost/sortviz/sorting
└── SortAlgorithmsTest.java   # proves each algorithm orders the bars ascending
```

## Notes

- Adding a new algorithm = subclass `Sort`, implement `sort()`, and add it to the list in `Main`.
  Nothing else needs to change; the menu is generated from that list.
- The GUI is created on the Event Dispatch Thread; the chosen algorithm runs on the main thread and
  the window repaints on a Swing timer.
