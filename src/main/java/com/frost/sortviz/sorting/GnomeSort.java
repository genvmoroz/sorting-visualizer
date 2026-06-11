package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

/**
 * Gnome sort: walks forward while elements are in order and steps back swapping whenever it finds
 * an out-of-order pair, like sorting flower pots one step at a time. O(n^2).
 */
public final class GnomeSort extends Sort {

    public GnomeSort() {
        super("Gnome sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int i = 1;
        while (i < models.size()) {
            models.get(i).setColor(Model.SELECT_COLOR);
            if (i == 0 || models.get(i - 1).getHeight() <= models.get(i).getHeight()) {
                sleep();
                models.get(i).setColor(Model.DEFAULT_COLOR);
                i++;
            } else {
                models.get(i - 1).setColor(Model.CHECK_COLOR);
                sleep();
                swap(i, i - 1);
                models.get(i - 1).setColor(Model.DEFAULT_COLOR);
                models.get(i).setColor(Model.DEFAULT_COLOR);
                i--;
            }
        }
    }
}
