package View;

import Model.Entity;
import Model.EntityType;
import Model.World.World;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WorldCanvas extends Canvas {

    private GraphicsContext gc;
    private int cellsize;
    private int gridStrokeSize;
    private final Sprite dirt;

    public WorldCanvas() {
        gc = getGraphicsContext2D();
        dirt = new Sprite(10,10, Color.SADDLEBROWN);
    }

    public void render() {
        gc.clearRect(0, 0, getWidth(), getHeight());



    }
}
