package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DiamondSprite extends Sprite{
    public DiamondSprite(double width, double height, Color color, GraphicsContext gc) {
        super(width, height, color, gc);
    }

    public DiamondSprite(double size, Color color, GraphicsContext gc) {
        super(size, color, gc);
    }

    @Override
    public void paint(int x, int y){
        double cx = x + width / 2.0;  // center X
        double cy = y + height / 2.0;  // center Y
        double rx  = width / 2.0;         // radius to a x point
        double ry  = height / 2.0;         // radius to a x point

        double[] xPoints = {
                cx,
                cx + rx,
                cx,
                cx - rx
        };

        double[] yPoints = {
                cy - ry,
                cy,
                cy + ry,
                cy
        };
        gc.setFill(color);
        gc.fillPolygon(xPoints,yPoints,4);
    }
}
