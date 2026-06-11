package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

import java.awt.Color;

/**
 * Naive exchange sort: for each position {@code i}, scan the rest of the list and swap whenever a
 * smaller element is found, so the smallest remaining value bubbles into {@code i}. O(n^2).
 */
public final class ExchangeSort extends Sort {

    public ExchangeSort() {
        super("Exchange sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        for (int i = 0; i < models.size(); i++) {
            models.get(i).setColor(Model.SELECT_COLOR);
            for (int j = i + 1; j < models.size(); j++) {
                models.get(j).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(i).getHeight() > models.get(j).getHeight()) {
                    swap(i, j);
                    models.get(j).setColor(Color.BLACK);
                    models.get(i).setColor(Model.SELECT_COLOR);
                }
                models.get(j).setColor(Model.DEFAULT_COLOR);
            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
        }
    }
}
