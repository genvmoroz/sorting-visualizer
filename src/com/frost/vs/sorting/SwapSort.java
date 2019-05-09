package com.frost.vs.sorting;

import com.frost.vs.Model;

public class SwapSort extends Sort {

    public void sort() throws InterruptedException {
        int left = 0;
        int right = models.size() - 1;
        do {
            for (int i = left; i < right; i++) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i+1).setColor(Model.CHECK_COLOR);
                sleep();
                if(models.get(i).getHeight() > models.get(i + 1).getHeight()) {
                    Model temp = models.get(i);
                    models.set(i, models.get(i+1));
                    models.set(i+1, temp);
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i+1).setColor(Model.DEFAULT_COLOR);
            }
            right--;
            for (int i = right; i > left ; i--) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i-1).setColor(Model.CHECK_COLOR);
                sleep();
                if(models.get(i).getHeight() < models.get(i-1).getHeight()) {
                    Model temp = models.get(i);
                    models.set(i, models.get(i-1));
                    models.set(i-1, temp);
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i-1).setColor(Model.DEFAULT_COLOR);
            }
            left++;
        } while (left <= right);
        drawGreen();
    }
}
