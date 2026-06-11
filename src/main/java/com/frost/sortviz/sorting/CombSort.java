package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

import java.awt.Color;

/**
 * Comb sort: an improved bubble sort that compares elements a shrinking {@code gap} apart, killing
 * small out-of-place values ("turtles") early. The gap shrinks by the ideal factor 1.3 each pass
 * until it reaches 1 and no swaps remain. Average O(n^2 / 2^p).
 */
public final class CombSort extends Sort {

    /** Empirically optimal gap shrink factor (~1.3). */
    private static final double SHRINK_FACTOR = 1.247330950103979;

    public CombSort() {
        super("Comb sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int gap = models.size();
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / SHRINK_FACTOR);
            }
            swapped = false;
            for (int i = 0; i + gap < models.size(); i++) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i + gap).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(i).getHeight() > models.get(i + gap).getHeight()) {
                    models.get(i).setColor(Color.GREEN);
                    models.get(i + gap).setColor(Color.GREEN);
                    sleep();
                    swap(i, i + gap);
                    swapped = true;
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i + gap).setColor(Model.DEFAULT_COLOR);
            }
        }
    }
}
