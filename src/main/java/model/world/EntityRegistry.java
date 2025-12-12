package model.world;

import model.Entity;

import java.util.List;

public interface EntityRegistry {
    public List<Entity> getEntityList();
    public List<Entity>[][] getEntityGrid();
    public void addEntity(Entity entity);
    public void removeEntity(Entity entity);
    public void updateEntityGrid();
}
