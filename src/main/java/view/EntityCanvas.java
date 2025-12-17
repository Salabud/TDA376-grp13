package view;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Being;
import model.Entity;
import model.EntityType;
import model.ModelPresentor;
import model.ants.Ant;
import model.world.Item;
import view.sprite.DiamondSprite;
import view.sprite.ImageSprite;
import view.sprite.SelectSprite;
import view.sprite.SelectWindow;
import view.sprite.Sprite;

/**
 * Canvas for rendering entities in the simulation.
 */
public class EntityCanvas extends Canvas {
    private final GraphicsContext gc = getGraphicsContext2D();
    private int cellsize;
    private final ImageSprite workerAnt;
    private final ImageSprite larva;
    private final ImageSprite food;
    private final ImageSprite queen;
    private final ImageSprite placeFood;
    private final ImageSprite placePoison;
    private final ImageSprite placeDirt;
    private final ImageSprite selectShovel;
    private final Sprite dirt;
    private final Sprite poison;
    private final Sprite selectionSprite;
    private int selectedEntityId = -1;
    private SelectWindow selectWindow;
    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();

    public EntityCanvas() {
        gc.setImageSmoothing(false);
        this.cellsize = (int) (metaData.getCellSize() * metaData.getZoom());
        setWidth(metaData.getResolutionY());
        setHeight(metaData.getResolutionX());

        // Being sprites
        this.workerAnt = new ImageSprite(1.5, gc,
                new Image(getClass().getResourceAsStream("/sprites/ant_orange.png"), 32, 32, false, false));
        this.larva = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/larva.png"), 32, 32, false, false));
        this.queen = new ImageSprite(2, gc,
                new Image(getClass().getResourceAsStream("/sprites/queen_orange.png"), 32, 32, false, false));

        // Item Sprites
        // this.itemOutline = new DiamondSprite(cellsize+6, Color.BLACK, gc);
        this.food = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/leaf.png"), 32, 32, false, false));
        this.dirt = new DiamondSprite(cellsize + 2, Color.rgb(50, 41, 47), gc);
        this.poison = new DiamondSprite(cellsize + 2, Color.PURPLE, gc);

        // Other sprites
        this.selectionSprite = new SelectSprite(1.4, Color.WHITE, gc);
        this.selectWindow = new SelectWindow(metaData.getResolutionY() - metaData.getSquareOffset() - 160,
                metaData.getResolutionX() - 200, gc);

        // Select sprites
        this.placeFood = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/apple.png"), 32, 32, false, false));
        this.placePoison = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/Poison.png"), 32, 32, false, false));
        this.placeDirt = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/Dirt.png"), 32, 32, false, false));
        this.selectShovel = new ImageSprite(1, gc,
                new Image(getClass().getResourceAsStream("/sprites/shovel.png"), 32, 32, false, false));

    }

    /**
     * Renders the entities onto the canvas.
     */
    public void render(ModelPresentor modelPresentor) {
        List<Entity> entities = modelPresentor.getEntityList();
        gc.clearRect(0, 0, getWidth(), getHeight());
        Entity selectedEntity = null;

        for (Entity entity : entities) {
            int posX = (int) ((entity.getX() * cellsize + metaData.getSquareOffset()) + metaData.getCameraX());
            int posY = (int) ((entity.getY() * cellsize + metaData.getSquareOffset()) + metaData.getCameraY());
            switch (entity.getType()) {
                case BEING:
                    Being being = (Being) entity;
                    switch (being.getBeingType()) {
                        case ANT:
                            Ant ant = (Ant) being;
                            switch (ant.getAntType()) {
                                case WORKER_ANT:
                                    workerAnt.paint(posX, posY);
                                    break;
                                case LARVA:
                                    larva.paint(posX, posY);
                                    break;
                                case QUEEN:
                                    queen.paint(posX, posY);
                                    break;
                            }
                    }
                    break;
                case EntityType.ITEM:
                    Item item = (Item) entity;
                    switch (item.getMaterialType()) {
                        case DIRT -> dirt.paint(posX, posY);
                        case FOOD -> food.paint(posX, posY);
                        case POISON -> poison.paint(posX, posY);
                    }
                    break;

            }

            if (entity.getEntityId() == selectedEntityId) {
                selectedEntity = entity;
                selectWindow.paint(selectedEntity);
            }
        }
        if (selectedEntity != null) {
            selectionSprite.paint((int) (selectedEntity.getX() * cellsize + metaData.getCameraX()),
                    (int) (selectedEntity.getY() * cellsize + metaData.getCameraY()));
        }
    }

    public void setSelectedEntity(int selectedEntityId) {
        this.selectedEntityId = selectedEntityId;
    }

    public void updateCellsize() {
        this.cellsize = (int) metaData.getCellSize();
    }
}
