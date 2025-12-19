package view.canvas;

import model.ModelPresentor;
import model.world.MaterialType;
import model.world.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.MetaDataRegistry;
import view.sprite.Sprite;
import view.sprite.SquareSprite;

import java.util.List;

/**
 * Canvas for rendering the world grid and tiles (for the most part terrain).
 */
public class WorldCanvas extends Canvas {

    private final GraphicsContext gc;
    private int cellsize; //TODO: Cellsize dynamically reflect window dimensions
    private int gridStrokeSize;
    private final Sprite dirt;
    private final Sprite stone;
    private final Sprite food;
    private final Sprite grass;
    private final Color background;
    private final Color sky;
    private final int outlineThickness;

    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();



    public WorldCanvas() {
        cellsize = (int)(metaData.getCellSize()* metaData.getZoom());
        setWidth(metaData.getResolutionY());
        setHeight(metaData.getResolutionX());
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
    public void render(ModelPresentor modelPresentor) {
        List<Tile> tiles = modelPresentor.getTiles();
        Tile[][] tileGrid = modelPresentor.getTileGrid();

        //gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(background);
        gc.fillRect(0,0,getWidth(),getHeight());

        //paint sky and border
        gc.setFill(sky);
        gc.fillRect(metaData.getCameraX(), metaData.getCameraY(), cellsize* metaData.getGridSize(),cellsize*20);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, metaData.getSquareOffset(), metaData.getResolutionX());
        gc.fillRect(metaData.getResolutionX()+ metaData.getSquareOffset(), 0, metaData.getSquareOffset(), metaData.getResolutionX());

        for (Tile tile : tiles){
            int posX = (int) ((tile.getX()*cellsize + metaData.getSquareOffset()) + metaData.getCameraX());
            int posY = (int) ((tile.getY()*cellsize + metaData.getSquareOffset()) + metaData.getCameraY());
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
                if (tileGrid[x][y] != null && y > 0 && tileGrid[x][y-1] == null){
                    gc.fillRect(outlinePos(x) + metaData.getCameraX(),outlinePos(y) + metaData.getCameraY(), cellsize + outlineThickness, outlineThickness);
                }
                //southern outlines
                if (tileGrid[x][y] != null && y < 99  && tileGrid[x][y+1] == null){
                    gc.fillRect(outlinePos(x) + metaData.getCameraX(),outlinePos(y)+cellsize + metaData.getCameraY(), cellsize+outlineThickness, outlineThickness);
                }
                //eastern outlines
                if (tileGrid[x][y] != null && x < 99 && tileGrid[x+1][y] == null){
                    gc.fillRect(outlinePos(x)+cellsize + metaData.getCameraX(),outlinePos(y) + metaData.getCameraY(),  outlineThickness, cellsize);
                }
                //western outlines
                if (tileGrid[x][y] != null && x > 0 && tileGrid[x-1][y] == null){
                    gc.fillRect(outlinePos(x) + metaData.getCameraX(),outlinePos(y) + metaData.getCameraY(),outlineThickness, cellsize+outlineThickness);
                }
            }
        }
    }

    /**
     * Helper function to calculate where to draw outline strokes adjusted for zoom
     * @param val the x or y value of the stroke
     * @return the adjusted x or y value
     */
    public int outlinePos(int val){
        return val*cellsize-1 + metaData.getSquareOffset();
    }

    public void updateCellsize() {
        this.cellsize = (int) metaData.getCellSize();
    }
}
