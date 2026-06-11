package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

/**
 * Heap sort: builds a binary max-heap in place, then repeatedly swaps the root (largest element)
 * to the end of the unsorted region and sifts the new root down to restore the heap. O(n log n).
 */
public final class HeapSort extends Sort {

    public HeapSort() {
        super("Heap sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int n = bars.size();
        // Build the max-heap from the last parent down to the root.
        for (int parent = n / 2 - 1; parent >= 0; parent--) {
            siftDown(parent, n);
        }
        // Extract the maximum into its final position, then re-heapify the shrinking front.
        for (int end = n - 1; end > 0; end--) {
            swap(0, end);
            siftDown(0, end);
        }
    }

    /** Pushes the element at {@code root} down until the subtree of size {@code size} is a max-heap. */
    private void siftDown(int root, int size) throws InterruptedException {
        while (true) {
            int largest = root;
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            sleep();
            if (left < size && bars.get(left).getHeight() > bars.get(largest).getHeight()) {
                largest = left;
            }
            if (right < size && bars.get(right).getHeight() > bars.get(largest).getHeight()) {
                largest = right;
            }
            if (largest == root) {
                return;
            }
            bars.get(root).setColor(Bar.SELECT_COLOR);
            bars.get(largest).setColor(Bar.CHECK_COLOR);
            sleep();
            swap(root, largest);
            bars.get(root).setColor(Bar.DEFAULT_COLOR);
            bars.get(largest).setColor(Bar.DEFAULT_COLOR);
            root = largest;
        }
    }
}
