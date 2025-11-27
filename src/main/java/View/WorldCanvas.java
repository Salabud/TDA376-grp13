package View;

import Model.Entity;
import Model.EntityType;
import Model.World.MaterialType;
import Model.World.Tile;
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
    private final Sprite stone;
    private final Sprite food;
    private Color background;

    public WorldCanvas() {
        cellsize = 10;
        setWidth(1000);
        setHeight(1000);
        gc = getGraphicsContext2D();
        dirt = new Sprite(cellsize, Color.SADDLEBROWN);
        stone = new Sprite(cellsize, Color.DARKGREY);
        food = new Sprite(cellsize, Color.RED);

        background = Color.SKYBLUE;

    }


    public void render(World world) {
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(background);
        gc.fillRect(0,0,1000,1000);
        for (Tile tile : world.getTiles()){
            switch (tile.getMaterialType()) {
                case MaterialType.DIRT:
                    gc.setFill(dirt.getColor());
                    gc.fillRect(tile.getPosition().getX(), tile.getPosition().getY(), dirt.getWidth(), dirt.getHeight());
                    break;
                case MaterialType.FOOD:
                    break;
                case MaterialType.STONE:
                    break;
                case MaterialType.GRASS:
                    break;
            }
        }



    }
}
