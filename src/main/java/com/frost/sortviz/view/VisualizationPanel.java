package com.frost.sortviz.view;

import com.frost.sortviz.Model;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

/**
 * The Swing canvas. It paints every bar scaled to fit, plus an overlay showing the array size and
 * the name of the running algorithm. Painting happens on the Event Dispatch Thread while the
 * algorithm mutates the shared list from another thread, so the live fields are {@code volatile}
 * and the bars are iterated by index over a captured size to stay glitch-free.
 */
final class VisualizationPanel extends JPanel {

    private static final int PREFERRED_WIDTH = 800;
    private static final int PREFERRED_HEIGHT = 500;
    private static final Font OVERLAY_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

    private volatile List<Model> models = List.of();
    private volatile float maxHeight = 1f;
    private volatile String sortName = "";

    VisualizationPanel() {
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBackground(Color.BLACK);
    }

    void setModels(List<Model> models) {
        this.models = models;
        recomputeMaxHeight();
    }

    void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /** Recomputes the tallest bar so the vertical scaling stays correct after an add or shuffle. */
    void recomputeMaxHeight() {
        float max = 1f;
        for (Model model : models) {
            max = Math.max(max, model.getHeight());
        }
        maxHeight = max;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        int width = getWidth();
        int height = getHeight();

        List<Model> snapshot = models;
        int size = snapshot.size();
        if (size > 0) {
            int barWidth = Math.max(1, width / size);
            float scaleHeight = height / maxHeight;
            for (int i = 0; i < size; i++) {
                snapshot.get(i).render(g, i * barWidth, barWidth, height, scaleHeight);
            }
        }

        g.setColor(Color.YELLOW);
        g.setFont(OVERLAY_FONT);
        g.drawString("Array size: " + size, 30, 100);
        g.drawString(sortName, 30, 140);
    }
}
