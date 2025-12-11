package model.world;

import javafx.geometry.Pos;
import model.datastructures.Position;

import java.util.List;

public interface TileRegistry {
    public Tile[][] getTileGrid();
    public List<Tile> getTileList();
    public void addTile(Tile tile);
    public void removeTile(Position position);
}
