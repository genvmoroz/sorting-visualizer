package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

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
        int gap = bars.size();
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / SHRINK_FACTOR);
            }
            swapped = false;
            for (int i = 0; i + gap < bars.size(); i++) {
                bars.get(i).setColor(Bar.SELECT_COLOR);
                bars.get(i + gap).setColor(Bar.CHECK_COLOR);
                sleep();
                if (bars.get(i).getHeight() > bars.get(i + gap).getHeight()) {
                    bars.get(i).setColor(Bar.SWAP_COLOR);
                    bars.get(i + gap).setColor(Bar.SWAP_COLOR);
                    sleep();
                    swap(i, i + gap);
                    swapped = true;
                }
                bars.get(i).setColor(Bar.DEFAULT_COLOR);
                bars.get(i + gap).setColor(Bar.DEFAULT_COLOR);
            }
        }
    }
}
