package view;

import javafx.scene.paint.Color;

/**
 * Represents a visual sprite with dimensions and color.
 */
public class Sprite {
    private double width;
    private double height;
    private Color color;

    public Sprite(double width, double height, Color color){
        this.color = color;
        this.height = height;
        this.width = width;
    }
    public Sprite(double size, Color color){
        this.color = color;
        this.height = size;
        this.width = size;
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
}

