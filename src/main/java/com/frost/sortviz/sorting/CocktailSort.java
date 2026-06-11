package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

/**
 * Cocktail (bidirectional bubble) sort: alternates a left-to-right pass that pushes the largest
 * element up with a right-to-left pass that pushes the smallest element down, shrinking the active
 * range from both ends. O(n^2).
 */
public final class CocktailSort extends Sort {

    public CocktailSort() {
        super("Cocktail sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int left = 0;
        int right = bars.size() - 1;
        do {
            for (int i = left; i < right; i++) {
                bars.get(i).setColor(Bar.SELECT_COLOR);
                bars.get(i + 1).setColor(Bar.CHECK_COLOR);
                sleep();
                if (bars.get(i).getHeight() > bars.get(i + 1).getHeight()) {
                    swap(i, i + 1);
                }
                bars.get(i).setColor(Bar.DEFAULT_COLOR);
                bars.get(i + 1).setColor(Bar.DEFAULT_COLOR);
            }
            right--;
            for (int i = right; i > left; i--) {
                bars.get(i).setColor(Bar.SELECT_COLOR);
                bars.get(i - 1).setColor(Bar.CHECK_COLOR);
                sleep();
                if (bars.get(i).getHeight() < bars.get(i - 1).getHeight()) {
                    swap(i, i - 1);
                }
                bars.get(i).setColor(Bar.DEFAULT_COLOR);
                bars.get(i - 1).setColor(Bar.DEFAULT_COLOR);
            }
            left++;
        } while (left <= right);
    }
}
