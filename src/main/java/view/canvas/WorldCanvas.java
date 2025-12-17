package view.canvas;

import model.world.MaterialType;
import model.world.Tile;
import model.world.World;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.MetaDataRegistry;
import view.sprite.Sprite;
import view.sprite.SquareSprite;

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
    private final Sprite grass;
    private Color background;
    private Color sky;
    private int outlineThickness;

    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();



    public WorldCanvas() {
        cellsize = metaData.getCellsize();
        setWidth(metaData.getScreenWidth());
        setHeight(metaData.getScreenHeight());
        gc = getGraphicsContext2D();
        outlineThickness = 2;

        //Tiles
        dirt = new SquareSprite(cellsize, Color.rgb(50,41,47), gc);
        stone = new SquareSprite(cellsize, Color.DARKGREY, gc);
        food = new SquareSprite(cellsize, Color.RED, gc);
        grass = new SquareSprite(cellsize, Color.rgb(105, 165, 85), gc);




        background = Color.rgb(112,93,86);
        sky = Color.rgb(110, 150, 230);

    }
    public void render(World world) {
        //gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(background);
        gc.fillRect(0,0,getWidth(),getHeight());

        //paint sky and border
        gc.setFill(sky);
        gc.fillRect(0,0,getWidth(),cellsize*20);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, metaData.getSquareOffset(), metaData.getScreenHeight());
        gc.fillRect(metaData.getScreenHeight()+ metaData.getSquareOffset(), 0, metaData.getSquareOffset(), metaData.getScreenHeight());

        for (Tile tile : world.getTileList()){
            int posX = tile.getX()*cellsize + metaData.getSquareOffset();
            int posY = tile.getY()*cellsize;
            switch (tile.getMaterialType()) {
                case MaterialType.DIRT:
                    dirt.paint(posX,posY);
                    break;
                case MaterialType.FOOD:
                    food.paint(posX,posY);
                    break;
                case MaterialType.STONE:
                    stone.paint(posX, posY);
                    break;
                case MaterialType.GRASS:
                    grass.paint(posX, posY);
                    break;
            }
        }
        for (int x = 0; x < 100; x++){
            for (int y = 0; y < 100; y++){
                gc.setFill(Color.BLACK);
                //northern outlines
                if (world.getTileGrid()[x][y] != null && y > 0 && world.getTileGrid()[x][y-1] == null){
                    gc.fillRect(x*cellsize-1 + metaData.getSquareOffset(),y*cellsize-1, cellsize + outlineThickness, outlineThickness);
                }
                //southern outlines
                if (world.getTileGrid()[x][y] != null && y < 99  && world.getTileGrid()[x][y+1] == null){
                    gc.fillRect(x*cellsize-1 + metaData.getSquareOffset(),y*cellsize+cellsize-1, cellsize+outlineThickness, outlineThickness);
                }
                //eastern outlines
                if (world.getTileGrid()[x][y] != null && x < 99 && world.getTileGrid()[x+1][y] == null){
                    gc.fillRect(x*cellsize + cellsize-1 + metaData.getSquareOffset(),y*cellsize,  outlineThickness, cellsize);
                }
                //western outlines
                if (world.getTileGrid()[x][y] != null && x > 0 && world.getTileGrid()[x-1][y] == null){
                    gc.fillRect(x*cellsize-1 + metaData.getSquareOffset(),y*cellsize -1,outlineThickness, cellsize+outlineThickness);
                }
            }
        }



    }
}
