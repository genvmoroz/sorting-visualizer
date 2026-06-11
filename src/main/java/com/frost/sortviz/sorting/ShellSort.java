package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

/**
 * Shell sort: generalised insertion sort that compares elements a {@code gap} apart, shrinking the
 * gap each round until it performs a final ordinary insertion sort over an almost-sorted list.
 */
public final class ShellSort extends Sort {

    public ShellSort() {
        super("Shell sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int gap = bars.size() / 2;
        while (gap > 0) {
            for (int i = gap; i < bars.size(); i++) {
                Bar current = bars.get(i);
                current.setColor(Bar.SELECT_COLOR);
                sleep();
                int j = i;
                while (j >= gap && bars.get(j - gap).getHeight() > current.getHeight()) {
                    bars.get(j - gap).setColor(Bar.CHECK_COLOR);
                    bars.set(j, bars.get(j - gap));
                    sleep();
                    bars.get(j - gap).setColor(Bar.DEFAULT_COLOR);
                    bars.get(j).setColor(Bar.DEFAULT_COLOR);
                    j -= gap;
                    sleep();
                }
                bars.set(j, current);
                current.setColor(Bar.DEFAULT_COLOR);
            }
            gap = (gap == 2) ? 1 : (int) (gap * (5.0 / 11));
        }
    }
}
