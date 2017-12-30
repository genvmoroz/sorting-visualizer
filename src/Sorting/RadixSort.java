package VisualisatorSort.Sorting;


import VisualisatorSort.Model;

import java.util.ArrayList;

public class RadixSort extends Sort {

    @Override
    protected void sort() throws InterruptedException {

        // Loop for every bit in the integers
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            // The array to put the partially sorted array into
            ArrayList<Model> tmp = new ArrayList<>();
            models.forEach(tmp::add);
            // The number of 0s
            int j = 0;

            // Move the 0s to the new array, and the 1s to the old one
            for (int i = 0; i < tmp.size(); i++) {
                // If there is a 1 in the bit we are testing, the number will be negative
                boolean move = (int)tmp.get(i).getHeight() << shift >= 0;

                // If this is the last bit, negative numbers are actually lower
                if ((shift == 0) != move) {
                    models.get(i).setColor(Model.SELECT_COLOR);
                    models.get(j).setColor(Model.CHECK_COLOR);
                    models.set(j, tmp.get(i));
                    j++;
                } else {
                    // It's a 1, so stick it in the old array for now
                    tmp.set(i - j, tmp.get(i));
                    sleep();
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
            }

            // Copy over the 1s from the old array
            for (int i = j; i < tmp.size(); i++) {
                models.set(i, tmp.get(i - j));
                sleep();
            }

        }
        drawGreen();
    }
}
