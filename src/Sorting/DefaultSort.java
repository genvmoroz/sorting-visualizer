package VisualisatorSort.Sorting;

import VisualisatorSort.Model;
import java.awt.*;


public class DefaultSort extends Sort{

    protected void sort() throws InterruptedException {
        if (models != null) {
            for (int i = 0; i < models.size(); i++) {
                models.get(i).setColor(Model.SELECT_COLOR);
                for (int j = i + 1; j < models.size(); j++) {
                    models.get(j).setColor(Model.CHECK_COLOR);
                    sleep();
                    if (models.get(i).getHeight() > models.get(j).getHeight()) {
                        Model temp = models.get(i);
                        models.set(i, models.get(j));
                        models.set(j, temp);
                        models.get(j).setColor(Color.BLACK);
                        models.get(i).setColor(Model.SELECT_COLOR);
                    }
                    models.get(j).setColor(Model.DEFAULT_COLOR);
                }
                models.get(i).setColor(Model.DEFAULT_COLOR);
            }
        }
            drawGreen();
    }
    public void start(){
        try {
            sort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
