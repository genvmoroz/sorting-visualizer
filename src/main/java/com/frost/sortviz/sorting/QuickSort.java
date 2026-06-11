package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

import java.awt.Color;

/**
 * Quick sort with a middle-element pivot. Each partition step walks an index in from each end,
 * tracking the pivot's moving position, then recurses into the two sides. Average O(n log n).
 */
public final class QuickSort extends Sort {

    /** Colors bars while the right index scans inward. */
    private static final Color SCAN_COLOR = new Color(136, 24, 9);

    /** Colors the two bars that were just swapped. */
    private static final Color SWAP_COLOR = new Color(157, 206, 0);

    public QuickSort() {
        super("Quick sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        quicksort(0, bars.size() - 1);
    }

    private void quicksort(int start, int end) throws InterruptedException {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int pivot = start + (end - start) / 2;
        while (i < j) {
            sleep();
            while (i < pivot && bars.get(i).getHeight() <= bars.get(pivot).getHeight()) {
                i++;
                sleep();
            }
            while (j > pivot && bars.get(pivot).getHeight() <= bars.get(j).getHeight()) {
                j--;
                bars.get(j).setColor(SCAN_COLOR);
                bars.get(pivot).setColor(SCAN_COLOR);
                sleep();
            }
            bars.get(i).setColor(Bar.DEFAULT_COLOR);
            bars.get(j).setColor(Bar.DEFAULT_COLOR);
            bars.get(pivot).setColor(Bar.DEFAULT_COLOR);
            if (i < j) {
                swap(i, j);
                bars.get(i).setColor(SWAP_COLOR);
                bars.get(j).setColor(SWAP_COLOR);
                if (i == pivot) {
                    pivot = j;
                } else if (j == pivot) {
                    pivot = i;
                }
            }
            sleep();
        }
        bars.get(i).setColor(Bar.DEFAULT_COLOR);
        bars.get(j).setColor(Bar.DEFAULT_COLOR);
        bars.get(pivot).setColor(Bar.DEFAULT_COLOR);
        quicksort(start, pivot);
        quicksort(pivot + 1, end);
    }
}
