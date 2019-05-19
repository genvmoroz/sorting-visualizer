package com.frost.vs.sorting;

import com.frost.vs.Model;

import java.awt.Color;

public class QuickSort extends Sort {

    public QuickSort() {
        super("Quick sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int startIndex = 0;
        int endIndex = models.size() - 1;
        doSort(startIndex, endIndex);
        drawGreen();
    }

    private void doSort(int start, int end) throws InterruptedException {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            sleep();
            while (i < cur && (models.get(i).getHeight() <= models.get(cur).getHeight())) {
                i++;
                sleep();
            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(j).setColor(Model.DEFAULT_COLOR);
            models.get(cur).setColor(Model.DEFAULT_COLOR);
            while (j > cur && (models.get(cur).getHeight() <= models.get(j).getHeight())) {
                j--;
                models.get(j).setColor(new Color(136, 24, 9));
                models.get(cur).setColor(new Color(136, 24, 9));
                sleep();
            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(j).setColor(Model.DEFAULT_COLOR);
            models.get(cur).setColor(Model.DEFAULT_COLOR);
            sleep();
            if (i < j) {
                swap(i, j);
                models.get(i).setColor(new Color(157, 206, 0));
                models.get(j).setColor(new Color(157, 206, 0));
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
            sleep();

        }
        models.get(i).setColor(Model.DEFAULT_COLOR);
        models.get(j).setColor(Model.DEFAULT_COLOR);
        models.get(cur).setColor(Model.DEFAULT_COLOR);
        doSort(start, cur);
        doSort(cur + 1, end);
    }
}
