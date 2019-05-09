package com.frost.vs;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class Visualization extends JFrame implements ActionListener{

    public static final int HEIGHT = 500;

    public static int width = 800;
    public ArrayList<Model> models;
    public float maxHeight = 1;

    private Timer running;

    Visualization() throws HeadlessException {
        setTitle("com.frost.vs.Visualization");
        setSize(width, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        running = new Timer(10, this);
    }
    public void addModels(Collection<Model> models){
        models.forEach(m -> {
            if (m.getHeight() > maxHeight)
                maxHeight = m.getHeight();
        });
        this.models = (ArrayList<Model>)models;
    }
    public void disSort(){
        if (models.size() == 0){
            System.out.println("Array is empty!");
            return;
        }
        models.forEach(Model::setRandHeight);
    }
    public void update(){

        width = getWidth();

        int scaleWidth = this.getWidth() / models.size();
        for (int i = 0; i < models.size(); i++){
            models.get(i).setPosition(i * scaleWidth + 1, 1);
        }
    }

    public void paint(Graphics simple){
        Image buffer = createImage(getWidth(), getHeight());
        Graphics2D g = (Graphics2D)buffer.getGraphics();

        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (models != null && models.size() != 0){
            update();
            models.forEach(model -> model.render(g, (float)(this.getWidth() + models.size())/models.size() - 1, (this.getHeight()) / maxHeight));
        }
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.PLAIN, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Array size: " + ((models != null) ? models.size() : 0), 30, 100);
        simple.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
    public void stop(){
        running.stop();
    }
    public void restart(){
        running.restart();
    }
    public void start(){
        running.start();
    }

}