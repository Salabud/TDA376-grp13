package model.world;

import model.entity.Entity;
import model.datastructures.Position;

import java.util.List;

/**
 * Record representing the context of the world, including entities and tiles.
 * Implements the context pattern for easy and limited access to world data.
 */
public record WorldContext (List<Entity> entityList,
                            List<Entity>[][] entityGrid,
                            List<Tile> tileList,
                            Tile[][] tileGrid,
                            int gridSize){
    public List<Entity> getEntitiesAt(Position pos){
        return entityGrid[pos.getX()][pos.getY()];
    }
    public Tile getTileAt(Position pos){
        return tileGrid[pos.getX()][pos.getY()];
    }
    public Tile[][] getTileGrid(){
        return tileGrid;
    }
    public boolean passable(Position pos){
        return (inBounds(pos) && tileGrid[pos.getX()][pos.getY()] == null);
    }
    public boolean inBounds(Position pos){
        return pos.getX() >= 0 && pos.getX() < gridSize && pos.getY() >= 0 && pos.getY() < gridSize;
    }

    public List<Entity>[][] getEntityGrid() {
        return entityGrid;
    }
}
