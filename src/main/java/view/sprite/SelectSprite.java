package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * SelectSprite is a concrete implementation of Sprite that represents a selection rectangle.
 */
public class SelectSprite extends Sprite{

    public SelectSprite(double width, double height, Color color, GraphicsContext gc) {
        super(width, height, color, gc);
    }

    public SelectSprite(double size, Color color, GraphicsContext gc) {
        super(size, color, gc);
    }

    @Override
    public void paint(int x, int y) {
        gc.setFill(color);
        gc.fillRect(x,y,width,2);
        gc.fillRect(x,y,2,height);
        gc.fillRect(x,y+height,width+2,2);
        gc.fillRect(x+width,y,2,height);

    }
}
