package VisualisatorSort;

import MyPaint.Shape.Vector;

import java.awt.*;

public class Model implements Comparable {

    private float height;
    private Vector position;
    private Color color;
    public static final Color DEFAULT_COLOR = new Color(240, 244, 255);
    public static final Color SELECT_COLOR = new Color(255, 49, 0);
    public static final Color CHECK_COLOR = new Color(67, 188, 178);
    private Visualisator visualisator;

    Model(float height, Visualisator visualisator){
        this.height = height;
        this.position = new Vector(0, 0);
        color = DEFAULT_COLOR ;
        this.visualisator = visualisator;
    }
    Model(int x, int y, float height,  Visualisator visualisator){
        this.height = height;
        this.position = new Vector(x, y);
        color = DEFAULT_COLOR ;
        this.visualisator = visualisator;
    }
    public void setRandHeight(){
        this.height = (int)(Math.random() * 10000) + 10;
    }
    public void setPosition(int x, int y){
        position.setPosition(x, y);
    }
    public void render(Graphics2D g, float scaleWidth, float scaleHeight){
        g.setColor(color);
        g.fillRect((int)position.x + 1, (int)((visualisator.getHeight()) - height * scaleHeight), (int)scaleWidth - 1, (int)(height * scaleHeight));
    }
    public void setColor(Color c){
        color = c;
    }
    public float getHeight(){
        return height;
    }
    public Vector getPosition(){
        return position;
    }

    @Override
    public int compareTo(Object o) {
        return (((Model)o).getHeight() < height) ? 1 : -1;
    }
}
