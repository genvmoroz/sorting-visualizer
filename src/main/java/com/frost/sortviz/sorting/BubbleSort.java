package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

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
        for (int i = models.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (models.get(j).getHeight() > models.get(j + 1).getHeight()) {
                    sleep();
                    models.get(j).setColor(Model.SELECT_COLOR);
                    models.get(j + 1).setColor(Model.CHECK_COLOR);
                    sleep();
                    swap(j, j + 1);
                    models.get(j).setColor(Model.CHECK_COLOR);
                    models.get(j + 1).setColor(Model.SELECT_COLOR);
                    sleep();
                }
                models.get(j).setColor(Model.DEFAULT_COLOR);
                models.get(j + 1).setColor(Model.DEFAULT_COLOR);
            }
        }
    }
}
