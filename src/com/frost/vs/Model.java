package com.frost.vs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

public class Model implements Comparable {

    public static final Color DEFAULT_COLOR = new Color(240, 244, 255);
    public static final Color SELECT_COLOR = new Color(255, 49, 0);
    public static final Color CHECK_COLOR = new Color(0, 167, 188);

    private float height;
    private Point position;
    private Color color;
    private Visualization visualization;

    Model(float height, Visualization visualization) {
        this.height = height;
        this.visualization = visualization;
        position = new Point(0, 0);
        color = DEFAULT_COLOR;
    }

    void render(Graphics2D g, float scaleWidth, float scaleHeight) {
        g.setColor(color);
        g.fillRect(position.x + 1, (int) ((visualization.getHeight()) - height * scaleHeight), (int) scaleWidth - 1, (int) (height * scaleHeight));
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public int compareTo(Object o) {
        if (Objects.isNull(o)) {
            return 1;
        }
        if (!(o instanceof Model)) {
            return 1;
        }
        Model model = (Model) o;
        return Float.compare(getHeight(), model.getHeight());
    }

    @Override
    public String toString() {
        return "Height: " + (height) +
                "Point: " + position.toString();
    }
}
