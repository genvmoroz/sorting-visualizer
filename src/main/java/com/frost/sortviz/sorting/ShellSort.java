package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

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
        int gap = models.size() / 2;
        while (gap > 0) {
            for (int i = gap; i < models.size(); i++) {
                Model current = models.get(i);
                current.setColor(Model.SELECT_COLOR);
                sleep();
                int j = i;
                while (j >= gap && models.get(j - gap).getHeight() > current.getHeight()) {
                    models.get(j - gap).setColor(Model.CHECK_COLOR);
                    models.set(j, models.get(j - gap));
                    sleep();
                    models.get(j - gap).setColor(Model.DEFAULT_COLOR);
                    models.get(j).setColor(Model.DEFAULT_COLOR);
                    j -= gap;
                    sleep();
                }
                models.set(j, current);
                current.setColor(Model.DEFAULT_COLOR);
            }
            gap = (gap == 2) ? 1 : (int) (gap * (5.0 / 11));
        }
    }
}
