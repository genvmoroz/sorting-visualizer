package VisualisatorSort.Sorting;

import VisualisatorSort.Model;

public class StupidSort extends Sort {
    @Override
    protected void sort() throws InterruptedException {
        int i = 0;
        int n = models.size() - 1;

        while (i < n) {
            models.get(i).setColor(Model.SELECT_COLOR);
            models.get(i + 1).setColor(Model.SELECT_COLOR);
            sleep();
            if (models.get(i + 1).compareTo(models.get(i)) < 0) {
                Model temp = models.get(i + 1);
                models.set(i + 1, models.get(i));
                models.set(i, temp);
                i = 0;
                sleep();
            } else {
                i++;
            }
            models.get(i).setColor(Model.DEFAULT_COLOR);
            models.get(i + 1).setColor(Model.DEFAULT_COLOR);

        }
        drawGreen();
    }
}
