package view;

import model.entity.being.ants.Ant;
import model.entity.being.Being;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.item.Item;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.sprite.*;

import java.util.List;

/**
 * Canvas for rendering entities in the simulation.
 */
public class EntityCanvas extends Canvas {
    private List<Entity> entities;
    private final GraphicsContext gc = getGraphicsContext2D();
    private int cellsize;
    private final Sprite workerAnt;
    private final Sprite larva;
    private final Sprite food;
    private final Sprite queen;
    private final Sprite dirt;
    private final Sprite poison;
    private final Sprite itemOutline;
    private final Sprite beingOutline;
    private final Sprite selectionSprite;
    private int selectedEntityId = -1;
    private SelectWindow selectWindow;
    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();


    public EntityCanvas(){
        this.cellsize = metaData.getCellsize();
        setWidth(metaData.getScreenWidth());
        setHeight(metaData.getScreenHeight());

        // Being sprites
        this.beingOutline = new CircleSprite(cellsize+4, Color.BLACK, gc);
        this.workerAnt = new CircleSprite(cellsize, Color.rgb(250, 149, 0), gc);
        this.larva = new CircleSprite(cellsize, Color.WHITE, gc);
        this.queen = new CircleSprite(cellsize, Color.YELLOW, gc);

        // Item Sprites
        this.itemOutline = new DiamondSprite(cellsize+6, Color.BLACK, gc);
        this.food = new DiamondSprite(cellsize+2, Color.GREEN, gc);
        this.dirt = new DiamondSprite(cellsize+2, Color.rgb(50,41,47), gc);
        this.poison = new DiamondSprite(cellsize+2, Color.PURPLE, gc);

        // Other sprites
        this.selectionSprite = new SelectSprite(cellsize+8, Color.WHITE, gc);
        this.selectWindow = new SelectWindow(metaData.getScreenWidth()- metaData.getSquareOffset()-160, metaData.getScreenHeight()-200, gc);
    }

    /**
     * Updates the list of entities to render.
     * @param entities : The new list of entities.
     */
    public void updateEntities(List<Entity> entities) {
        this.entities = entities;
        render();
    }

    /**
     * Renders the entities onto the canvas.
     */
    public void render() {
        gc.clearRect(0, 0, getWidth(), getHeight());
        Entity selectedEntity = null;

        for (Entity entity : entities) {
            int posX = entity.getX()*cellsize + metaData.getSquareOffset();
            int posY = entity.getY()*cellsize;
            gc.setFill(Color.BLACK);
            switch (entity.getType()) {
                case BEING:
                    beingOutline.paint(posX-2,posY-2);
                    Being being = (Being) entity;
                    switch(being.getBeingType()){
                        case ANT:
                            Ant ant = (Ant) being;
                            switch(ant.getAntType()){
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
                    itemOutline.paint(posX-2,posY-2);
                    switch (item.getMaterialType()){
                        case DIRT -> dirt.paint(posX,posY);
                        case FOOD -> food.paint(posX,posY);
                        case POISON -> poison.paint(posX,posY);
                    }
                    break;

            }

            if (entity.getEntityId() == selectedEntityId){
                selectedEntity = entity;
                selectWindow.paint(selectedEntity);
            }
        }
        if (selectedEntity != null){
            selectionSprite.paint(selectedEntity.getX()*cellsize-5 + metaData.getSquareOffset(), selectedEntity.getY()*cellsize-5);
        }
    }

    public void setSelectedEntity(int selectedEntityId) {
        this.selectedEntityId = selectedEntityId;
    }
}
