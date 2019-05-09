package com.frost.vs;

import com.frost.vs.sorting.BubbleSort;
import com.frost.vs.sorting.CombSort;
import com.frost.vs.sorting.GnomeSort;
import com.frost.vs.sorting.HeapSort;
import com.frost.vs.sorting.QuickSort;
import com.frost.vs.sorting.SelectionSort;
import com.frost.vs.sorting.ShellSort;
import com.frost.vs.sorting.Sort;
import com.frost.vs.sorting.SwapSort;
import com.frost.vs.sorting.UsuallySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static Visualization visualization = new Visualization();
    private static HashMap<Keys, Sort> sorting = new HashMap<>();

    public static ArrayList<Model> models = new ArrayList<>();

    public static void main(String... args) throws Exception {
        menu();
    }

    private static void menu() {

        loadSort();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("     |__________________MENU__________________|");
            System.out.println("     |             1. Add models.             |");
            System.out.println("     |             2. Random value.           |");
            System.out.println("     |         3. Choice way for sorting.     |");
            System.out.println("     |_________________Choice_________________|");
            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Enter correct number!");
            }
            switch (choice) {
                case 1: {
                    int number = 0;
                    System.out.println("     |_____Enter the number of models._____|");
                    try {
                        number = Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    addModels(number);
                    visualization.addModels(models);
                    visualization.start();
                    break;
                }

                case 2: {
                    visualization.disSort();
                    System.out.println("<-Completed->");
                    break;
                }
                case 3: {
                    System.out.println("     |__________________MENU__________________|");
                    System.out.println("     |               1. Usually               |");
                    System.out.println("     |               2. Bubble                |");
                    System.out.println("     |               3. Selection.            |");
                    System.out.println("     |               4. SwapSort.             |");
                    System.out.println("     |               5. GnomeSort.            |");
                    System.out.println("     |               6. QuickSort.            |");
                    System.out.println("     |               7. CombSort.             |");
                    System.out.println("     |               8. HeapSort.             |");
                    System.out.println("     |               9. ShellSort.            |");
                    System.out.println("     |__________________Choice________________|");
                    int number = 0;
                    try {
                        number = Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (number) {
                        case 1: {
                            start(sorting.get(Keys.DEFAULT));
                            break;
                        }
                        case 2: {
                            start(sorting.get(Keys.BUBBLE));
                            break;
                        }
                        case 3: {
                            start(sorting.get(Keys.SELECTION));
                            break;
                        }
                        case 4: {
                            start(sorting.get(Keys.SWAP));
                            break;
                        }
                        case 5: {
                            start(sorting.get(Keys.GNOME));
                            break;
                        }
                        case 6: {
                            start(sorting.get(Keys.QUITE));
                            break;
                        }
                        case 7: {
                            start(sorting.get(Keys.COMB));
                            break;
                        }
                        case 8: {
                            start(sorting.get(Keys.HEAP));
                            break;
                        }
                        case 9: {
                            start(sorting.get(Keys.SHELL));
                            break;
                        }
                    }

                    break;
                }
            }
        }
    }

    private static void start(Sort sort) {
        Visualization.SORT_NAME = sort.name;
        sort.start();
        visualization.update();
    }

    private static void loadSort() {
        sorting.put(Keys.DEFAULT, new UsuallySort());
        sorting.get(Keys.DEFAULT).addModels(models);

        sorting.put(Keys.BUBBLE, new BubbleSort());
        sorting.get(Keys.BUBBLE).addModels(models);

        sorting.put(Keys.SELECTION, new SelectionSort());
        sorting.get(Keys.SELECTION).addModels(models);

        sorting.put(Keys.SWAP, new SwapSort());
        sorting.get(Keys.SWAP).addModels(models);

        sorting.put(Keys.GNOME, new GnomeSort());
        sorting.get(Keys.GNOME).addModels(models);

        sorting.put(Keys.QUITE, new QuickSort());
        sorting.get(Keys.QUITE).addModels(models);

        sorting.put(Keys.COMB, new CombSort());
        sorting.get(Keys.COMB).addModels(models);

        sorting.put(Keys.HEAP, new HeapSort());
        sorting.get(Keys.HEAP).addModels(models);

        sorting.put(Keys.SHELL, new ShellSort());
        sorting.get(Keys.SHELL).addModels(models);
    }

    private static void addModels(int count) {
        for (; count > 0; count--) {
            Model model = new Model((int) (Math.random() * 10000), visualization);
            models.add(model);
        }
        for (; count < 0 && !models.isEmpty(); count++) {
            models.remove(0);
        }
    }
}