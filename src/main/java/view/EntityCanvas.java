package view;

import model.ants.Ant;
import model.Being;
import model.Entity;
import model.EntityType;
import model.world.Item;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.sprite.CircleSprite;
import view.sprite.DiamondSprite;
import view.sprite.Sprite;

import java.util.List;

/**
 * Canvas for rendering entities in the simulation.
 */
public class EntityCanvas extends Canvas {
    private List<Entity> entities;
    private final GraphicsContext gc = getGraphicsContext2D();
    private int cellsize; //TODO: Cellsize dynamically reflect window dimensions
    private final Sprite workerAnt;
    private final Sprite larva;
    private final Sprite food;
    private final Sprite queen;
    private final Sprite dirt;
    private final Sprite itemOutline;
    private final Sprite beingOutline;


    public EntityCanvas(){
        this.cellsize = 8;
        setWidth(800);
        setHeight(800);

        // Being sprites
        this.beingOutline = new CircleSprite(cellsize+4, Color.BLACK, gc);
        this.workerAnt = new CircleSprite(cellsize, Color.rgb(250, 149, 0), gc);
        this.larva = new CircleSprite(cellsize, Color.WHITE, gc);
        this.queen = new CircleSprite(cellsize, Color.YELLOW, gc);

        // Item Sprites
        this.itemOutline = new DiamondSprite(cellsize+6, Color.BLACK, gc);
        this.food = new DiamondSprite(cellsize+2, Color.GREEN, gc);
        this.dirt = new DiamondSprite(cellsize+2, Color.rgb(50,41,47), gc);
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
        for (Entity entity : entities) {
            int posX = entity.getX()*cellsize;
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
                    }
                    break;

            }
        }
    }
}
