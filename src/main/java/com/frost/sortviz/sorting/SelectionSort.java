package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

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
        for (int i = 0; i < models.size(); i++) {
            models.get(i).setColor(Model.SELECT_COLOR);
            sleep();
            int minIndex = i;
            for (int j = i + 1; j < models.size(); j++) {
                models.get(j).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(j).getHeight() < models.get(minIndex).getHeight()) {
                    if (minIndex != i) {
                        models.get(minIndex).setColor(Model.DEFAULT_COLOR);
                    }
                    minIndex = j;
                    models.get(minIndex).setColor(MIN_COLOR);
                    sleep();
                } else {
                    models.get(j).setColor(Model.DEFAULT_COLOR);
                }
            }
            sleep();
            if (i != minIndex) {
                swap(i, minIndex);
            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(minIndex).setColor(Model.DEFAULT_COLOR);
        }
    }
}
