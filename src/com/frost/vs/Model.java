package com.frost.vs;

import java.awt.*;
import java.util.Objects;

public class Model implements Comparable {

    private float height;
    private Point position;
    private Color color;
    public static final Color DEFAULT_COLOR = new Color(240, 244, 255);
    public static final Color SELECT_COLOR = new Color(255, 49, 0);
    public static final Color CHECK_COLOR = new Color(0, 167, 188);
    private Visualization visualization;

    Model(float height, Visualization visualization) {
        this.height = height;
        this.position = new Point(0, 0);
        color = DEFAULT_COLOR;
        this.visualization = visualization;
    }

    void setRandHeight() {
        this.height = (int) (Math.random() * 10000) + 10;
    }

    void setPosition(int x, int y) {
        position.setLocation(x, y);
    }

    void render(Graphics2D g, float scaleWidth, float scaleHeight) {
        g.setColor(color);
        g.fillRect(position.x + 1, (int) ((visualization.getHeight()) - height * scaleHeight), (int) scaleWidth - 1, (int) (height * scaleHeight));
    }

    public void setColor(Color c) {
        color = c;
    }

    public float getHeight() {
        return height;
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
}
