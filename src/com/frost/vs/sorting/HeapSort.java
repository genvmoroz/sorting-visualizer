package com.frost.vs.sorting;

import com.frost.vs.Model;

import java.util.ArrayList;

public class HeapSort extends Sort {

    private static int N;

    public HeapSort() {
        super("Heap sort");
    }

    private void heapFy(ArrayList<Model> m) throws InterruptedException {
        N = m.size() - 1;
        for (int i = N / 2; i >= 0; i--)
            maxHeap(m, i);
    }

    private void maxHeap(ArrayList<Model> m, int i) throws InterruptedException {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        sleep();
        if (left <= N && m.get(left).getHeight() > m.get(i).getHeight()) {
            models.get(i).setColor(Model.SELECT_COLOR);
            models.get(left).setColor(Model.CHECK_COLOR);
            max = left;
        }

        if (right <= N && m.get(right).getHeight() > m.get(max).getHeight()) {
            models.get(max).setColor(Model.SELECT_COLOR);
            models.get(right).setColor(Model.CHECK_COLOR);
            max = right;
        }
        if (max != i) {
            swap(i, max);
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(max).setColor(Model.DEFAULT_COLOR);
            sleep();
            maxHeap(m, max);
        }
        sleep();

    }

    @Override
    protected void sort() throws InterruptedException {

        heapFy((ArrayList<Model>) models);
        for (int i = N; i > 0; i--) {
            swap(0, i);
            N = N - 1;
            maxHeap((ArrayList<Model>) models, 0);
        }
        drawGreen();
    }
}
