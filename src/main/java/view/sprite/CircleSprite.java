package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * CircleSprite is a concrete implementation of Sprite that represents circular shapes.
 */
public class CircleSprite extends Sprite{

    public CircleSprite(double width, double height, Color color, GraphicsContext gc) {
        super(width, height, color, gc);
    }

    public CircleSprite(double size, Color color, GraphicsContext gc) {
        super(size, color, gc);
    }

    @Override
    public void paint(int x, int y){
        gc.setFill(color);
        gc.fillOval(x, y, width, height);
    }
}
