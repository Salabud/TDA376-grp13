package View;

import Model.Entity;
import Model.EntityType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class EntityCanvas extends Canvas {
    private List<Entity> entities;
    private final GraphicsContext gc = getGraphicsContext2D();
    private int cellsize;
    private final Sprite workerAnt = new Sprite(cellsize*0.8, cellsize*0.8, Color.ORANGE);
    private final Sprite larva = new Sprite(cellsize*0.8, cellsize*0.8, Color.WHITE);
    private final Sprite food = new Sprite(cellsize*0.8, cellsize*0.8, Color.RED);
    private final Sprite queen = new Sprite(cellsize*0.8, cellsize*0.8, Color.YELLOW);


    public EntityCanvas(){
        this.cellsize = 10; //Modularize
    }

    public void updateEntities(List<Entity> entities) {
        this.entities = entities;
        render();
    }

    public void render() {
        for (Entity entity : entities) {
            switch (entity.getType()) {
                case EntityType.WORKER_ANT:
                    gc.setFill(workerAnt.getColor());
                    gc.fillOval(entity.getX(), entity.getY(), workerAnt.getWidth(), workerAnt.getHeight());
                    break;
                case EntityType.LARVA:
                    gc.setFill(SpriteRegistry.getInstance().Larva.getColor());
                    gc.fillOval(entity.getX(), entity.getY(), SpriteRegistry.getInstance().Larva.getWidth(), SpriteRegistry.getInstance().Larva.getHeight());
                    break;
                case EntityType.QUEEN:
                    gc.setFill(SpriteRegistry.getInstance().Queen.getColor());
                    gc.fillOval(entity.getX(), entity.getY(), SpriteRegistry.getInstance().Queen.getWidth(), SpriteRegistry.getInstance().Queen.getHeight());
                    break;
                case EntityType.FOOD:
                    gc.setFill(SpriteRegistry.getInstance().Food.getColor());
                    gc.fillOval(entity.getX(), entity.getY(), SpriteRegistry.getInstance().Food.getWidth(), SpriteRegistry.getInstance().Food.getHeight());
                    break;

            }
        }
    }
}
