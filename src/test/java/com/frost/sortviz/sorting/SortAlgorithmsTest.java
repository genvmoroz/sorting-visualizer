package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies that every algorithm orders the bars ascending by height across a range of input shapes
 * — empty, single, two-element, duplicates, already-sorted, reverse-sorted, and random. Tests live
 * in the same package so they can drive the protected {@link Sort#sort()} directly, skipping the
 * GUI and the final color sweep. The injected step delay defaults to zero, so the runs are instant.
 */
class SortAlgorithmsTest {

    private static final long SEED = 42L;

    static Stream<Sort> algorithms() {
        return Stream.of(
                new ExchangeSort(),
                new BubbleSort(),
                new SelectionSort(),
                new CocktailSort(),
                new GnomeSort(),
                new QuickSort(),
                new CombSort(),
                new HeapSort(),
                new ShellSort());
    }

    /** A named list of bar heights; the name keeps the parameterized test reports readable. */
    private record Shape(String name, float[] heights) {
        @Override
        public String toString() {
            return name;
        }
    }

    private static Stream<Shape> shapes() {
        return Stream.of(
                new Shape("empty", new float[]{}),
                new Shape("single", new float[]{7f}),
                new Shape("two-element", new float[]{2f, 1f}),
                new Shape("duplicates", new float[]{5f, 1f, 5f, 1f, 3f, 3f, 5f, 1f}),
                new Shape("already-sorted", ramp(32, true)),
                new Shape("reverse-sorted", ramp(32, false)),
                new Shape("random", randomHeights(64)));
    }

    /** Cross-product of every algorithm with every input shape. */
    static Stream<Arguments> algorithmsAndShapes() {
        List<Shape> shapes = shapes().toList();
        return algorithms().flatMap(algorithm -> shapes.stream().map(shape -> Arguments.of(algorithm, shape)));
    }

    @ParameterizedTest(name = "{0} sorts {1}")
    @MethodSource("algorithmsAndShapes")
    void sortsAscending(Sort algorithm, Shape shape) throws InterruptedException {
        List<Bar> bars = new ArrayList<>();
        for (float height : shape.heights()) {
            bars.add(new Bar(height));
        }
        List<Float> expected = bars.stream().map(Bar::getHeight).sorted().toList();

        algorithm.setBars(bars);
        algorithm.sort();

        List<Float> actual = bars.stream().map(Bar::getHeight).toList();
        assertEquals(expected, actual, algorithm.getName() + " did not order the " + shape + " input ascending");
    }

    /** Strictly ascending (1..size) or strictly descending (size..1) heights. */
    private static float[] ramp(int size, boolean ascending) {
        float[] heights = new float[size];
        for (int i = 0; i < size; i++) {
            heights[i] = ascending ? i + 1 : size - i;
        }
        return heights;
    }

    /** Deterministic pseudo-random heights in [1, 1000]. */
    private static float[] randomHeights(int size) {
        Random random = new Random(SEED);
        float[] heights = new float[size];
        for (int i = 0; i < size; i++) {
            heights[i] = random.nextInt(1000) + 1;
        }
        return heights;
    }
}
