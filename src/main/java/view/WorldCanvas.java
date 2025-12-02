package view;

import model.world.MaterialType;
import model.world.Tile;
import model.world.World;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Canvas for rendering the world grid and tiles (for the most part terrain).
 */
public class WorldCanvas extends Canvas {

    private GraphicsContext gc;
    private int cellsize; //TODO: Cellsize dynamically reflect window dimensions
    private int gridStrokeSize;
    private final Sprite dirt;
    private final Sprite stone;
    private final Sprite food;
    private Color background;
    private int outlineThickness;



    public WorldCanvas() {
        cellsize = 8;
        setWidth(800);
        setHeight(800);
        gc = getGraphicsContext2D();
        outlineThickness = 2;

        //Tiles
        dirt = new Sprite(cellsize, Color.rgb(50,41,47));
        stone = new Sprite(cellsize, Color.DARKGREY);
        food = new Sprite(cellsize, Color.RED);



        background = Color.rgb(112,93,86);

    }
    public void render(World world) {
        //gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(background);
        gc.fillRect(0,0,getWidth(),getHeight());
        for (Tile tile : world.getTiles()){
            switch (tile.getMaterialType()) {
                case MaterialType.DIRT:
                    gc.setFill(dirt.getColor());
                    gc.fillRect(tile.getPosition().getX()*cellsize, tile.getPosition().getY()*cellsize, dirt.getWidth(), dirt.getHeight());
                    break;
                case MaterialType.FOOD:
                    break;
                case MaterialType.STONE:
                    break;
                case MaterialType.GRASS:
                    break;
            }
        }
        for (int x = 0; x < 100; x++){
            for (int y = 0; y < 100; y++){
                gc.setFill(Color.BLACK);
                //northern outlines
                if (world.getTileGrid()[x][y] != null && y > 0 && world.getTileGrid()[x][y-1] == null){
                    gc.fillRect(x*cellsize-1,y*cellsize-1, cellsize + outlineThickness, outlineThickness);
                }
                //southern outlines
                if (world.getTileGrid()[x][y] != null && y < 99  && world.getTileGrid()[x][y+1] == null){
                    gc.fillRect(x*cellsize-1,y*cellsize+cellsize-1, cellsize+outlineThickness, outlineThickness);
                }
                //eastern outlines
                if (world.getTileGrid()[x][y] != null && x < 99 && world.getTileGrid()[x+1][y] == null){
                    gc.fillRect(x*cellsize + cellsize-1,y*cellsize,  outlineThickness, cellsize);
                }
                //western outlines
                if (world.getTileGrid()[x][y] != null && x > 0 && world.getTileGrid()[x-1][y] == null){
                    gc.fillRect(x*cellsize-1,y*cellsize -1,outlineThickness, cellsize+outlineThickness);
                }
            }
        }



    }
}
