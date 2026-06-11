package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

/**
 * Bubble sort: repeatedly walks the unsorted prefix swapping adjacent out-of-order pairs, so the
 * largest remaining element settles at the end of each pass. O(n^2).
 */
public final class BubbleSort extends Sort {

    public BubbleSort() {
        super("Bubble sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        for (int i = bars.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bars.get(j).getHeight() > bars.get(j + 1).getHeight()) {
                    sleep();
                    bars.get(j).setColor(Bar.SELECT_COLOR);
                    bars.get(j + 1).setColor(Bar.CHECK_COLOR);
                    sleep();
                    swap(j, j + 1);
                    bars.get(j).setColor(Bar.CHECK_COLOR);
                    bars.get(j + 1).setColor(Bar.SELECT_COLOR);
                    sleep();
                }
                bars.get(j).setColor(Bar.DEFAULT_COLOR);
                bars.get(j + 1).setColor(Bar.DEFAULT_COLOR);
            }
        }
    }
}
