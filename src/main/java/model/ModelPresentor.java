package model;

import model.world.Tile;
import model.entity.Entity;
import java.util.List;

public interface ModelPresentor {
    List<Entity>[][] getEntityGrid();

    List<Entity> getEntityList();

    Tile[][] getTileGrid();

    List<Tile> getTiles();
}
