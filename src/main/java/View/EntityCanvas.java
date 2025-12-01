package View;

import Model.Entity;
import Model.EntityType;
import Model.World.Item;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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


    public EntityCanvas(){
        this.cellsize = 8;
        setWidth(800);
        setHeight(800);
        this.workerAnt = new Sprite(cellsize, Color.ORANGE);
        this.larva = new Sprite(cellsize, Color.WHITE);
        this.food = new Sprite(cellsize, Color.GREEN);
        this.queen = new Sprite(cellsize, Color.YELLOW);
        this.dirt = new Sprite(cellsize, Color.rgb(50,41,47));
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
            switch (entity.getType()) {
                case EntityType.WORKER_ANT:
                    gc.setFill(workerAnt.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, workerAnt.getWidth(), workerAnt.getHeight());
                    break;
                case EntityType.LARVA:
                    gc.setFill(larva.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, larva.getWidth(), larva.getHeight());
                    break;
                case EntityType.QUEEN:
                    break;
                case EntityType.ITEM:
                    Item item = (Item) entity;
                    switch(item.getMaterialType()){
                        case FOOD -> {
                            gc.setFill(food.getColor());
                            gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, food.getWidth(), food.getHeight());
                        }
                        case DIRT -> {
                            gc.setFill(dirt.getColor());
                            gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, dirt.getWidth(), dirt.getHeight());
                        }
                    }
                    break;

            }
        }
    }
}
