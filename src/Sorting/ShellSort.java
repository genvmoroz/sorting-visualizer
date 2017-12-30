package VisualisatorSort.Sorting;

import VisualisatorSort.Model;

public class ShellSort extends Sort {

    @Override
    protected void sort() throws InterruptedException {
        int increment = models.size() / 2;
        while (increment > 0) {
            for (int i = increment; i < models.size(); i++) {
                int j = i;
                Model temp = models.get(i);
                temp.setColor(Model.SELECT_COLOR);
                sleep();
                while (j >= increment && models.get(j - increment).getHeight() > temp.getHeight()) {
                    models.get(j - increment).setColor(Model.CHECK_COLOR);
                    models.set(j, models.get(j - increment));
                    sleep();
                    models.get(j - increment).setColor(Model.DEFAULT_COLOR);
                    models.get(j).setColor(Model.DEFAULT_COLOR);
                    j = j - increment;
                    sleep();
                }
                sleep();
                models.set(j, temp);
                temp.setColor(Model.DEFAULT_COLOR);

            }
            if (increment == 2) {
                increment = 1;
            } else {
                increment *= (5.0 / 11);
            }
        }
        drawGreen();
    }
}
