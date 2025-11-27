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
    private int cellsize; //TODO: Cellsize dynamically reflect window dimensions
    private final Sprite workerAnt;
    private final Sprite larva;
    private final Sprite food;
    private final Sprite queen;


    public EntityCanvas(){
        this.cellsize = 8;
        setWidth(800);
        setHeight(800);
        this.workerAnt = new Sprite(cellsize, Color.ORANGE);
        this.larva = new Sprite(cellsize, Color.WHITE);
        this.food = new Sprite(cellsize, Color.RED);
        this.queen = new Sprite(cellsize, Color.YELLOW);
    }

    public void updateEntities(List<Entity> entities) {
        this.entities = entities;
        render();
    }

    public void render() {
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Entity entity : entities) {
            switch (entity.getType()) {
                case EntityType.WORKER_ANT:
                    gc.setFill(workerAnt.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, workerAnt.getWidth(), workerAnt.getHeight());
                    break;
                case EntityType.LARVA:
                    gc.setFill(SpriteRegistry.getInstance().Larva.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, SpriteRegistry.getInstance().Larva.getWidth(), SpriteRegistry.getInstance().Larva.getHeight());
                    break;
                case EntityType.QUEEN:
                    gc.setFill(SpriteRegistry.getInstance().Queen.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, SpriteRegistry.getInstance().Queen.getWidth(), SpriteRegistry.getInstance().Queen.getHeight());
                    break;
                case EntityType.ITEM:
                    gc.setFill(SpriteRegistry.getInstance().Food.getColor());
                    gc.fillOval(entity.getX()*cellsize, entity.getY()*cellsize, SpriteRegistry.getInstance().Food.getWidth(), SpriteRegistry.getInstance().Food.getHeight());
                    break;

            }
        }
    }
}
