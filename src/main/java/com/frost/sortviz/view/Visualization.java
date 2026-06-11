package com.frost.sortviz.view;

import com.frost.sortviz.Model;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.util.List;

/**
 * The application window. It hosts a {@link VisualizationPanel} and repaints it on a fixed-rate
 * Swing {@link Timer} so the animation stays smooth while an algorithm reorders the shared list.
 * Must be constructed on the Event Dispatch Thread.
 */
public final class Visualization extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private static final int REPAINT_INTERVAL_MILLIS = 10;

    private final VisualizationPanel panel = new VisualizationPanel();

    public Visualization() {
        setTitle("Sorting Visualizer");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
        new Timer(REPAINT_INTERVAL_MILLIS, e -> panel.repaint()).start();
    }

    /** Binds the bars the window should render (the same list the algorithms reorder). */
    public void setModels(List<Model> models) {
        panel.setModels(models);
    }

    public void setSortName(String sortName) {
        panel.setSortName(sortName);
    }

    /** Recomputes vertical scaling after the data changed underneath the view (e.g. a shuffle). */
    public void refreshScale() {
        panel.recomputeMaxHeight();
    }

    /** Current canvas width, used to pace the animation as the original did. */
    public int canvasWidth() {
        int panelWidth = panel.getWidth();
        return panelWidth > 0 ? panelWidth : WIDTH;
    }
}
