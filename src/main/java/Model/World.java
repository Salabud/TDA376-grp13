package Model;

import java.util.ArrayList;
import java.util.List;

public class World {
    private Entity[][] grid;
    private List<Entity> entities;

    World(){
        int gridSize = 100;
        grid = new Entity[gridSize][gridSize];
        entities = new ArrayList<>();
    }

    public void addEntity(Entity ent){
        grid[ent.getX()][ent.getY()] = ent;
        entities.add(ent);
    }


    public void tick(){
        for (Entity entity: entities) {
            entity.update();
        }
    }
    public Entity[][] getGrid(){
        return grid;
    }
    public List<Entity> getEntities(){
        return entities;
    }
}
