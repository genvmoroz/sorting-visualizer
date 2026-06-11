package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

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
        while (i < bars.size()) {
            bars.get(i).setColor(Bar.SELECT_COLOR);
            if (i == 0 || bars.get(i - 1).getHeight() <= bars.get(i).getHeight()) {
                sleep();
                bars.get(i).setColor(Bar.DEFAULT_COLOR);
                i++;
            } else {
                bars.get(i - 1).setColor(Bar.CHECK_COLOR);
                sleep();
                swap(i, i - 1);
                bars.get(i - 1).setColor(Bar.DEFAULT_COLOR);
                bars.get(i).setColor(Bar.DEFAULT_COLOR);
                i--;
            }
        }
    }
}
