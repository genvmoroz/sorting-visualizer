package com.frost.vs;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Visualization extends JFrame {

    /*
        Ширина окна.
     */
    public static int WIDTH = 800;

    /*
        Имя текущей сортировки, выводится на экране.
     */
    static String SORT_NAME = "";

    /*
        Коллекция моделей.
     */
    public List<Model> models = new ArrayList<>();

    /*
        Значение, которое устанавливает
        максимальную высоту для моделей.
     */
    private float maxHeight = 1;

    Visualization() {
        setTitle("com.frost.vs.Visualization");
        setSize(WIDTH, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                WIDTH = componentEvent.getComponent().getWidth();
            }
        });
        new Timer(10, event -> repaint())
                .start();
    }

    /**
     * Добавляются модели в окно и устанавливается
     * максимальная высота для всех моделей.
     */
    void addModels(List<Model> models) {
        if (Objects.isNull(models)) {
            throw new IllegalArgumentException("The models can not be null");
        }

        models.stream()
                .filter(model -> model.getHeight() > maxHeight)
                .forEach(m -> maxHeight = m.getHeight());
        this.models = models;
    }

    /**
     * Сброс всех моделей,
     * путём изменения высоты на рандомное значение для каждой модели.
     */
    void reset() {
        SORT_NAME = "";
        if (models.size() == 0) {
            System.err.println("Array is empty!");
            return;
        }
        models.forEach(model ->
                model.setHeight((float) (Math.random() * getHeight()))
        );
    }

    /**
     * Обновление позиции моделей на экране
     * относительно их позиции в колекции.
     */
    private void updatePosition() {
        int scaleWidth = this.getWidth() / models.size();
        for (int i = 0; i < models.size(); i++) {
            models.get(i).setPosition(
                    new Point(i * scaleWidth, 1)
            );
        }
    }

    @Override
    public void paint(Graphics simple) {
        Image buffer = createImage(getWidth(), getHeight());
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (Objects.nonNull(models) && models.size() != 0) {
            updatePosition();
            models.forEach(model -> model.render(g, (float) (this.getWidth() + models.size()) / models.size() - 1, (this.getHeight()) / (maxHeight)));
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.PLAIN, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Array size: " + (Objects.nonNull(models) ? models.size() : 0), 30, 100);
        g.drawString(SORT_NAME, 30, 140);
        simple.drawImage(buffer, 0, 0, null);
    }
}