package VisualisatorSort.Sorting;

import VisualisatorSort.Model;

public class BitonicSort extends Sort {

    private final boolean ASCENDING = true, DESCENDING = false;

    @Override
    protected void sort() throws InterruptedException {
        bitonicSort(0, models.size(), ASCENDING);
    }
    private void bitonicSort(int lo, int n, boolean dir) throws InterruptedException {
        if (n>1)
        {
            int k=n/2;
            bitonicSort(lo, k, ASCENDING);
            bitonicSort(lo+k, k, DESCENDING);
            bitonicMerge(lo, n, dir);
        }
    }
    private void bitonicMerge(int lo, int n, boolean dir) throws InterruptedException {
        if (n>1)
        {
            int k=n/2;
            for (int i=lo; i<lo+k; i++)
                compare(i, i+k, dir);
            bitonicMerge(lo, k, dir);
            bitonicMerge(lo+k, k, dir);
        }
    }
    private void compare(int i, int j, boolean dir) throws InterruptedException {
        models.get(i).setColor(Model.SELECT_COLOR);
        models.get(j).setColor(Model.CHECK_COLOR);
        sleep();
        if (dir==(models.get(i).getHeight() > models.get(j).getHeight())) {
            swap(i, j);
            sleep();
        }
        models.get(i).setColor(Model.DEFAULT_COLOR);
        models.get(j).setColor(Model.DEFAULT_COLOR);

    }
}
