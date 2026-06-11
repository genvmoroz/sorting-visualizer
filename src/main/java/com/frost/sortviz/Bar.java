package com.frost.sortviz;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A single bar in the visualization: a numeric value ({@link #height}) together with the
 * color it is currently drawn in. Algorithms reorder a list of {@code Bar}s and recolor
 * them to highlight comparisons and swaps; the view turns each one into a rectangle.
 */
public final class Bar {

    /** Resting color of a bar that is not currently involved in a comparison or swap. */
    public static final Color DEFAULT_COLOR = new Color(240, 244, 255);

    /** Highlights the "current" / pivot element an algorithm is working with. */
    public static final Color SELECT_COLOR = new Color(255, 49, 0);

    /** Highlights the element currently being compared against the selected one. */
    public static final Color CHECK_COLOR = new Color(0, 167, 188);

    /** Highlights a pair of bars in the instant just after they swap places. */
    public static final Color SWAP_COLOR = new Color(60, 200, 90);

    /** Colors a bar during the final sweep that confirms the list is sorted. */
    public static final Color SORTED_COLOR = Color.YELLOW;

    private float height;
    private Color color = DEFAULT_COLOR;

    public Bar(float height) {
        this.height = height;
    }

    /**
     * Draws this bar.
     *
     * @param g            the canvas to draw on
     * @param x            left pixel position of the bar
     * @param width        pixel width allotted to the bar
     * @param canvasHeight pixel height of the canvas (bars grow from the bottom up)
     * @param scaleHeight  factor that maps a value to pixels so the tallest bar fills the canvas
     */
    public void render(Graphics2D g, int x, int width, int canvasHeight, float scaleHeight) {
        int barHeight = Math.round(height * scaleHeight);
        g.setColor(color);
        g.fillRect(x + 1, canvasHeight - barHeight, Math.max(1, width - 1), barHeight);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
