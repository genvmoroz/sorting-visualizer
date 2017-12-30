package VisualisatorSort;

import MyPaint.Sheet;
import VisualisatorSort.Sorting.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static Visualisator visualisator = new Visualisator();
    private static HashMap<Keys, Sort> sorting = new HashMap<>();

    public static ArrayList<Model> models = new ArrayList<>();

    public static void main(String ... args) throws Exception{

       menu();

    }
    private static void menu() {

        loadSort();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            cleanScreen();
            System.out.println("     |__________________MENU__________________|");
            System.out.println("     |             1. Add models.             |");
            System.out.println("     |             2. Random value.           |");
            System.out.println("     |         3. Choice way for sorting.     |");
            System.out.println("     |_________________Choice_________________|");
            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            }catch (Exception e){
                System.out.println("Enter correct number!");
            }
            switch (choice){
                case 1 : {
                    int number = 0;
                    System.out.println("     |_____Enter the number of models._____|");
                    try {
                        number = Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    addModels(number);
                    visualisator.addModels(models);
                    visualisator.start();
                    break;
                }

                case 2 : {
                    visualisator.disSort();
                    System.out.println("<-Completed->");
                    break;
                }
                case 3 : {
                    System.out.println("     |__________________MENU__________________|");
                    System.out.println("     |               1. Default               |");
                    System.out.println("     |               2. Bubble                |");
                    System.out.println("     |               3. Selection.            |");
                    System.out.println("     |               4. SwapSort.             |");
                    System.out.println("     |               5. GnomeSort.            |");
                    System.out.println("     |               6. QuiteSort.            |");
                    System.out.println("     |               7. CombSort.             |");
                    System.out.println("     |               8. StupidSort.           |");
                    System.out.println("     |               9. HeapSort.             |");
                    System.out.println("     |              10. RadixSort.            |");
                    System.out.println("     |              11. ShellSort.            |");
                    System.out.println("     |              12. BitonicSort.          |");
                    System.out.println("     |__________________Choice________________|");
                    int number = 0;
                    try {
                        number = Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    switch (number){
                        case 1 : {
                            System.out.println("<-Start->");
                            DefaultSort logic = (DefaultSort) sorting.get(Keys.DEFAULT);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 2 : {
                            System.out.println("<-Start->");
                            BubbleSort logic = (BubbleSort) sorting.get(Keys.BUBBLE);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 3 : {
                            System.out.println("<-Start->");
                            SelectionSort logic = (SelectionSort) sorting.get(Keys.SELECTION);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 4 : {
                            System.out.println("<-Start->");
                            SwapSort logic = (SwapSort) sorting.get(Keys.SWAP);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 5 : {
                            System.out.println("<-Start->");
                            GnomeSort logic = (GnomeSort) sorting.get(Keys.GNOME);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 6 : {
                            System.out.println("<-Start->");
                            QuiteSort logic = (QuiteSort) sorting.get(Keys.QUITE);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 7 : {
                            System.out.println("<-Start->");
                            CombSort logic = (CombSort) sorting.get(Keys.COMB);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 8 : {
                            System.out.println("<-Start->");
                            StupidSort logic = (StupidSort) sorting.get(Keys.STUPID);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 9 : {
                            System.out.println("<-Start->");
                            HeapSort logic = (HeapSort) sorting.get(Keys.HEAP);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 10 : {
                            System.out.println("<-Start->");
                            RadixSort logic = (RadixSort) sorting.get(Keys.RADIX);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 11 : {
                            System.out.println("<-Start->");
                            ShellSort logic = (ShellSort) sorting.get(Keys.SHELL);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                        case 12 : {
                            System.out.println("<-Start->");
                            BitonicSort logic = (BitonicSort) sorting.get(Keys.BITONIC);
                            logic.start();
                            visualisator.update();
                            break;
                        }
                    }

                    break;
                }
            }
        }
    }
    private static void loadSort() {

        // loading default sorting
        sorting.put(Keys.DEFAULT, new DefaultSort());
        sorting.get(Keys.DEFAULT).addModels(models);
        // loading bubble sorting
        sorting.put(Keys.BUBBLE, new BubbleSort());
        sorting.get(Keys.BUBBLE).addModels(models);
        // loading selection sorting
        sorting.put(Keys.SELECTION, new SelectionSort());
        sorting.get(Keys.SELECTION).addModels(models);
        // loading swap sorting
        sorting.put(Keys.SWAP, new SwapSort());
        sorting.get(Keys.SWAP).addModels(models);
        // loading gnome sorting
        sorting.put(Keys.GNOME, new GnomeSort());
        sorting.get(Keys.GNOME).addModels(models);
        // loading quite sorting
        sorting.put(Keys.QUITE, new QuiteSort());
        sorting.get(Keys.QUITE).addModels(models);
        // loading comb sorting
        sorting.put(Keys.COMB, new CombSort());
        sorting.get(Keys.COMB).addModels(models);
        // loading stupid sorting
        sorting.put(Keys.STUPID, new StupidSort());
        sorting.get(Keys.STUPID).addModels(models);
        // loading stupid sorting
        sorting.put(Keys.HEAP, new HeapSort());
        sorting.get(Keys.HEAP).addModels(models);
        // loading radix sorting
        sorting.put(Keys.RADIX, new RadixSort());
        sorting.get(Keys.RADIX).addModels(models);
        // loading shell sorting
        sorting.put(Keys.SHELL, new ShellSort());
        sorting.get(Keys.SHELL).addModels(models);
        // loading bitonic sorting
        sorting.put(Keys.BITONIC, new BitonicSort());
        sorting.get(Keys.BITONIC).addModels(models);
    }
    private static void addModels(int count){
        for (; count > 0; count--) {
            Model model = new Model((int) (Math.random() * 10000), visualisator);
            models.add(model);
        }
    }
    private static void cleanScreen(){
        for (int i = 0; i < 20; i++)
            System.out.println();
    }
}
