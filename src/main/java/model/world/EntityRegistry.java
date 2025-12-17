package model.world;

import model.entity.Entity;

import java.util.List;

/**
 * Interface for managing a registry of entities within the world.
 */
public interface EntityRegistry {
    public List<Entity> getEntityList();
    public List<Entity>[][] getEntityGrid();
    public void addEntity(Entity entity);
    public void removeEntity(Entity entity);
    public void updateEntityGrid();
}
