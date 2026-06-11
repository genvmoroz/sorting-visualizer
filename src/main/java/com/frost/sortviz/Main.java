package com.frost.sortviz;

import com.frost.sortviz.sorting.BubbleSort;
import com.frost.sortviz.sorting.CocktailSort;
import com.frost.sortviz.sorting.CombSort;
import com.frost.sortviz.sorting.ExchangeSort;
import com.frost.sortviz.sorting.GnomeSort;
import com.frost.sortviz.sorting.HeapSort;
import com.frost.sortviz.sorting.QuickSort;
import com.frost.sortviz.sorting.SelectionSort;
import com.frost.sortviz.sorting.ShellSort;
import com.frost.sortviz.sorting.Sort;
import com.frost.sortviz.view.Visualization;

import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Entry point and console controller. It opens the {@link Visualization} window, owns the shared
 * list of bars, and drives a simple text menu: add bars, shuffle them, or run an algorithm. The
 * chosen algorithm runs on this thread while the window repaints itself on the EDT.
 */
public final class Main {

    /** Maximum random bar value; also the natural canvas height in pixels. */
    private static final int MAX_BAR_VALUE = 500;

    private final List<Model> bars = new ArrayList<>();
    private final List<Sort> algorithms;
    private final Visualization visualization;

    private Main(Visualization visualization) {
        this.visualization = visualization;
        this.visualization.setModels(bars);
        this.algorithms = List.of(
                new ExchangeSort(),
                new BubbleSort(),
                new SelectionSort(),
                new CocktailSort(),
                new GnomeSort(),
                new QuickSort(),
                new CombSort(),
                new HeapSort(),
                new ShellSort());
        for (Sort algorithm : algorithms) {
            algorithm.setModels(bars);
            algorithm.setStepDelay(this::stepDelayMillis);
        }
    }

    public static void main(String... args) throws InterruptedException, InvocationTargetException {
        Visualization window = createOnEdt();
        new Main(window).run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMainMenu();
            switch (readInt(scanner)) {
                case 1 -> addBars(scanner);
                case 2 -> shuffleBars();
                case 3 -> runAlgorithm(scanner);
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Pick one of the listed options.");
            }
        }
    }

    private void addBars(Scanner scanner) {
        System.out.print("How many bars to add? ");
        int count = readInt(scanner);
        if (count <= 0) {
            System.out.println("Nothing added.");
            return;
        }
        for (int i = 0; i < count; i++) {
            bars.add(new Model(ThreadLocalRandom.current().nextInt(1, MAX_BAR_VALUE + 1)));
        }
        visualization.setModels(bars);
        System.out.println("Now showing " + bars.size() + " bars.");
    }

    private void shuffleBars() {
        if (bars.isEmpty()) {
            System.out.println("No bars yet — add some first (option 1).");
            return;
        }
        visualization.setSortName("");
        for (Model bar : bars) {
            bar.setHeight(ThreadLocalRandom.current().nextInt(1, MAX_BAR_VALUE + 1));
        }
        visualization.refreshScale();
        System.out.println("Bars shuffled.");
    }

    private void runAlgorithm(Scanner scanner) {
        if (bars.isEmpty()) {
            System.out.println("No bars yet — add some first (option 1).");
            return;
        }
        printAlgorithmMenu();
        int choice = readInt(scanner);
        if (choice < 1 || choice > algorithms.size()) {
            System.out.println("No algorithm for that number.");
            return;
        }
        Sort algorithm = algorithms.get(choice - 1);
        visualization.setSortName(algorithm.getName());
        System.out.println("Running " + algorithm.getName() + "...");
        algorithm.start();
        System.out.println(algorithm.getName() + " finished.");
    }

    /** Per-step delay tied to canvas width and array size, matching the original feel. */
    private int stepDelayMillis() {
        int size = bars.size();
        return size == 0 ? 0 : visualization.canvasWidth() / (size * 2);
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("=== Sorting Visualizer ===");
        System.out.println("  1. Add bars");
        System.out.println("  2. Shuffle bars");
        System.out.println("  3. Run a sorting algorithm");
        System.out.println("  0. Exit");
        System.out.print("Choose: ");
    }

    private void printAlgorithmMenu() {
        System.out.println("Choose an algorithm:");
        for (int i = 0; i < algorithms.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, algorithms.get(i).getName());
        }
        System.out.print("Choose: ");
    }

    /** Reads an integer, re-prompting on bad input and exiting cleanly when stdin closes. */
    private int readInt(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        System.out.println();
        System.out.println("Input closed. Goodbye!");
        System.exit(0);
        return -1; // unreachable
    }

    /** Builds the window on the Event Dispatch Thread, as Swing requires. */
    private static Visualization createOnEdt() throws InterruptedException, InvocationTargetException {
        AtomicReference<Visualization> ref = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> ref.set(new Visualization()));
        return ref.get();
    }
}
