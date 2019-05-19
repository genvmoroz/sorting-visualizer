package com.frost.vs.sorting;

import com.frost.vs.Model;

public class GnomeSort extends Sort {

    public GnomeSort() {
        super("Gnome sort");
    }

    @Override
    protected void sort() throws InterruptedException {
        int i = 1;
        while (i < models.size()) {
            models.get(i).setColor(Model.SELECT_COLOR);
            if (i == 0 || models.get(i - 1).getHeight() <= models.get(i).getHeight()) {
                sleep();
                models.get(i).setColor(Model.DEFAULT_COLOR);
                i++;

            } else {
                models.get(i - 1).setColor(Model.CHECK_COLOR);
                sleep();
                Model temp = models.get(i);
                models.set(i, models.get(i - 1));
                models.set(i - 1, temp);
                models.get(i - 1).setColor(Model.DEFAULT_COLOR);
                models.get(i).setColor(Model.DEFAULT_COLOR);
                i--;
            }
        }
        drawGreen();
    }
}
