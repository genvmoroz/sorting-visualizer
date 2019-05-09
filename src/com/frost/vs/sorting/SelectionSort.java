package com.frost.vs.sorting;

import com.frost.vs.Model;

import java.awt.*;

public class SelectionSort extends Sort{

    public SelectionSort() {
        super("Selection sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        for (int i = 0; i < models.size(); i++) {
            Model min = models.get(i);
            min.setColor(Model.SELECT_COLOR);
            sleep();
            int min_index = i;
            for (int j = i+1; j < models.size(); j++) {
                models.get(j).setColor(Model.CHECK_COLOR);
                sleep();
                if (models.get(j).getHeight() < min.getHeight()) {
                    min = models.get(j);
                    min_index = j;
                    min.setColor(new Color(122, 0, 0));
                    sleep();
                }else
                    models.get(j).setColor(Model.DEFAULT_COLOR);
            }
            sleep();
            if (i != min_index) {
                Model tmp = models.get(i);
                models.set(i, models.get(min_index));
                models.set(min_index, tmp);

            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(min_index).setColor(Model.DEFAULT_COLOR);

        }
        drawGreen();
    }
}
