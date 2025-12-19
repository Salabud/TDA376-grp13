package model.world;

import model.entity.Entity;

import java.util.List;

/**
 * Interface for managing a registry of entities within the world.
 */
public interface EntityRegistry {
    List<Entity> getEntityList();
    List<Entity>[][] getEntityGrid();
    void addEntity(Entity entity);
    void removeEntity(Entity entity);
    void updateEntityGrid();
}
