package VisualisatorSort.Sorting;

import VisualisatorSort.Model;

import java.awt.*;

public class BubbleSort extends Sort {

    @Override
    protected void sort() throws InterruptedException {
        for (int i = models.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (models.get(j).getHeight() > models.get(j + 1).getHeight()) {
                    sleep();
                    models.get(j).setColor(Model.SELECT_COLOR);
                    models.get(j + 1).setColor(Model.CHECK_COLOR);
                    Model temp = models.get(j);
                    sleep();
                    models.set(j, models.get(j + 1));
                    models.get(j).setColor(Model.CHECK_COLOR);
                    models.set(j + 1, temp);
                    models.get(j + 1).setColor(Model.SELECT_COLOR);
                    sleep();
                }
                models.get(j).setColor(Model.DEFAULT_COLOR);
                models.get(j + 1).setColor(Model.DEFAULT_COLOR);
            }
        }
        drawGreen();
    }
}
