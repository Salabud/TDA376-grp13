package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a visual sprite with dimensions and color.
 */
public abstract class Sprite {
    protected double width;
    protected double height;
    protected Color color;
    protected GraphicsContext gc;

    public Sprite(double width, double height, Color color, GraphicsContext gc){
        this.color = color;
        this.height = height;
        this.width = width;
        this.gc = gc;
    }
    public Sprite(double size, Color color, GraphicsContext gc){
        this.color = color;
        this.height = size;
        this.width = size;
        this.gc = gc;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(int x, int y){

    }

}

