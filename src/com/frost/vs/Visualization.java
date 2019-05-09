package com.frost.vs;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Visualization extends JFrame implements ActionListener{

    public static String SORT_NAME;

    static final int HEIGHT = 500;

    public static int width = 800;
    public ArrayList<Model> models;

    float maxHeight = 1;

    private Timer running;

    Visualization() throws HeadlessException {
        setTitle("com.frost.vs.Visualization");
        setSize(width, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        running = new Timer(10, this);
    }

    void addModels(Collection<Model> models){
        models.forEach(m -> {
            if (m.getHeight() > maxHeight)
                maxHeight = m.getHeight();
        });
        this.models = (ArrayList<Model>)models;
    }

    void disSort(){
        SORT_NAME = null;
        if (models.size() == 0){
            System.out.println("Array is empty!");
            return;
        }
        models.forEach(Model::setRandHeight);
    }

    void update(){
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
        if (Objects.nonNull(SORT_NAME)) {
            g.drawString(SORT_NAME, 30, 140);
        }
        simple.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    void start(){
        running.start();
    }
}