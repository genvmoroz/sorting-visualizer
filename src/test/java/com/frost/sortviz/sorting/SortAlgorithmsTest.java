package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies that every algorithm actually orders the bars ascending by height. Tests live in the
 * same package so they can drive the protected {@link Sort#sort()} directly, skipping the GUI and
 * the final color sweep. The injected step delay defaults to zero, so the runs are instant.
 */
class SortAlgorithmsTest {

    private static final int SIZE = 64;
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

    @ParameterizedTest(name = "{0} orders bars ascending")
    @MethodSource("algorithms")
    void sortsAscending(Sort algorithm) throws InterruptedException {
        Random random = new Random(SEED);
        List<Model> bars = new ArrayList<>();
        List<Float> expected = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            float height = random.nextInt(1000) + 1;
            bars.add(new Model(height));
            expected.add(height);
        }
        expected.sort(null);

        algorithm.setModels(bars);
        algorithm.sort();

        List<Float> actual = bars.stream().map(Model::getHeight).toList();
        assertEquals(expected, actual, algorithm.getName() + " did not order the bars ascending");
    }

    @ParameterizedTest(name = "{0} handles a single element")
    @MethodSource("algorithms")
    void sortsSingleElement(Sort algorithm) throws InterruptedException {
        List<Model> bars = new ArrayList<>(List.of(new Model(7f)));

        algorithm.setModels(bars);
        algorithm.sort();

        assertEquals(7f, bars.getFirst().getHeight());
    }
}
