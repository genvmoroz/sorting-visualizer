package com.frost.vs.sorting;

import com.frost.vs.Model;
import com.frost.vs.Visualization;

import java.awt.Color;
import java.util.List;

public abstract class Sort {

    public final String name;
    protected List<Model> models;

    public Sort(String name) {
        this.name = name;
    }

    protected void drawGreen() throws InterruptedException {
        models.forEach(model -> {
            try {
                sleep();
                model.setColor(Color.YELLOW);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(250);
        models.forEach(model -> {
            try {
                sleep();
                model.setColor(Model.DEFAULT_COLOR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    protected abstract void sort() throws InterruptedException;

    public void start() {
        try {
            sort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void swap(int i, int j) {
        Model temp = models.get(i);
        models.set(i, models.get(j));
        models.set(j, temp);
    }

    protected void sleep() throws InterruptedException {
        Thread.sleep(Visualization.WIDTH / (models.size() * 2));
    }
}
