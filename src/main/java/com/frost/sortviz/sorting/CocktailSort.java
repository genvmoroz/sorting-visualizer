package com.frost.sortviz.sorting;

import com.frost.sortviz.Model;

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
        int right = models.size() - 1;
        do {
            for (int i = left; i < right; i++) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i + 1).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(i).getHeight() > models.get(i + 1).getHeight()) {
                    swap(i, i + 1);
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i + 1).setColor(Model.DEFAULT_COLOR);
            }
            right--;
            for (int i = right; i > left; i--) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i - 1).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(i).getHeight() < models.get(i - 1).getHeight()) {
                    swap(i, i - 1);
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i - 1).setColor(Model.DEFAULT_COLOR);
            }
            left++;
        } while (left <= right);
    }
}
