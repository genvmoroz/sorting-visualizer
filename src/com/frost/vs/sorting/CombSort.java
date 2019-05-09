package com.frost.vs.sorting;

import com.frost.vs.Model;

import java.awt.*;

public class CombSort extends Sort {

    @Override
    protected void sort() throws InterruptedException {
        int gap = models.size();
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1)
                gap = (int) (gap / 1.247330950103979);

            int i = 0;
            swapped = false;
            while (i + gap < models.size()) {
                models.get(i).setColor(Model.SELECT_COLOR);
                models.get(i + gap).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(i).getHeight() > models.get(gap + i).getHeight()) {
                    models.get(i).setColor(Color.GREEN);
                    models.get(i + gap).setColor(Color.GREEN);
                    sleep();
                    Model temp = models.get(i);
                    models.set(i, models.get(i + gap));
                    models.set(i + gap, temp);
                    swapped = true;
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
                models.get(i + gap).setColor(Model.DEFAULT_COLOR);
                i++;
            }
        }
        drawGreen();
    }
}
