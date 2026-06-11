package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

import java.awt.Color;

/**
 * Selection sort: for each position {@code i}, find the smallest element in the remaining list and
 * swap it into place. Performs at most one swap per outer step. O(n^2).
 */
public final class SelectionSort extends Sort {

    /** Marks the bar currently believed to be the minimum of the unsorted tail. */
    private static final Color MIN_COLOR = new Color(122, 0, 0);

    public SelectionSort() {
        super("Selection sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        for (int i = 0; i < bars.size(); i++) {
            bars.get(i).setColor(Bar.SELECT_COLOR);
            sleep();
            int minIndex = i;
            for (int j = i + 1; j < bars.size(); j++) {
                bars.get(j).setColor(Bar.CHECK_COLOR);
                sleep();
                if (bars.get(j).getHeight() < bars.get(minIndex).getHeight()) {
                    if (minIndex != i) {
                        bars.get(minIndex).setColor(Bar.DEFAULT_COLOR);
                    }
                    minIndex = j;
                    bars.get(minIndex).setColor(MIN_COLOR);
                    sleep();
                } else {
                    bars.get(j).setColor(Bar.DEFAULT_COLOR);
                }
            }
            sleep();
            if (i != minIndex) {
                swap(i, minIndex);
            }
            bars.get(i).setColor(Bar.DEFAULT_COLOR);
            bars.get(minIndex).setColor(Bar.DEFAULT_COLOR);
        }
    }
}
