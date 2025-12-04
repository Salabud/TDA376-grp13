package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareSprite extends Sprite{

    public SquareSprite(double width, double height, Color color, GraphicsContext gc) {
        super(width, height, color, gc);
    }

    public SquareSprite(double size, Color color, GraphicsContext gc) {
        super(size, color, gc);
    }
    @Override
    public void paint(int x, int y){
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }
}
