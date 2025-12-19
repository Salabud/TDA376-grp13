package model.world;

import javafx.geometry.Pos;
import model.datastructures.Position;

import java.util.List;

/**
 * Interface for managing a registry of tiles within the world.
 */
public interface TileRegistry {
    Tile[][] getTileGrid();
    List<Tile> getTileList();
    void addTile(Tile tile);
    void removeTile(Position position);
}
