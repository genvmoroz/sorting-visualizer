# Sorting Visualizer

A Swing desktop application that animates classic sorting algorithms over a list of bars, driven from a console menu.

## Language

**Bar**:
The single unit being sorted and drawn — a numeric value (its height) together with the color it is currently rendered in. The list of bars is the data every algorithm reorders.
_Avoid_: Model, element, item

### Highlight states

While an algorithm runs, each bar is colored to show its role in the current step.

**Selected**:
The bar the algorithm is anchored on this step — the current or pivot element.

**Compared**:
A bar being checked against the selected one.
_Avoid_: checked

**Swapped**:
A pair of bars in the moment just after they exchange places.

**Sorted sweep**:
The final pass that flashes every bar in turn to confirm the list is ordered.
