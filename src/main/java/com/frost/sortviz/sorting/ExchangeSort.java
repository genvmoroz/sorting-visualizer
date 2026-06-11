package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

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
        for (int i = 0; i < bars.size(); i++) {
            bars.get(i).setColor(Bar.SELECT_COLOR);
            for (int j = i + 1; j < bars.size(); j++) {
                bars.get(j).setColor(Bar.CHECK_COLOR);
                sleep();
                if (bars.get(i).getHeight() > bars.get(j).getHeight()) {
                    swap(i, j);
                    bars.get(i).setColor(Bar.SWAP_COLOR);
                    bars.get(j).setColor(Bar.SWAP_COLOR);
                    sleep();
                    bars.get(i).setColor(Bar.SELECT_COLOR);
                }
                bars.get(j).setColor(Bar.DEFAULT_COLOR);
            }
            bars.get(i).setColor(Bar.DEFAULT_COLOR);
        }
    }
}
