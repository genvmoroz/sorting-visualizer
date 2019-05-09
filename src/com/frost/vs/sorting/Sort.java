package com.frost.vs.sorting;

import com.frost.vs.Model;
import com.frost.vs.Visualization;

import java.awt.*;
import java.util.List;

public abstract class Sort {

    protected List<Model> models;
    protected void drawGreen() throws InterruptedException {
        models.forEach(model -> {
            try {
                sleep();
                model.setColor(Color.YELLOW);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sleep(250);
        models.forEach(model -> {
            try {
                sleep();
                model.setColor(Model.DEFAULT_COLOR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    protected void swap(int i, int j){
        try {
            Model temp = models.get(i);
            models.set(i, models.get(j));
            models.set(j, temp);
        }catch (Exception e){
            System.err.println("ERROR!");
        }
    }
    public void addModels(List<Model> models){
        this.models = models;
    }
    protected abstract void sort() throws InterruptedException;
    public void start(){
        try {
            sort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void sleep(long sleep) throws InterruptedException {
        Thread.sleep(sleep);
    }
    protected void sleep() throws InterruptedException {
        Thread.sleep(Visualization.width/(models.size() * 2));
    }
}
