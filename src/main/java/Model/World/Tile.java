package Model.World;

import Model.Datastructures.Position;

/**
 * Represents a static tile in the world grid with a specific material type.
 */
public class Tile {
    private Position position;
    private MaterialType materialType;

    public Tile(int x, int y, MaterialType materialType){
        this.materialType = materialType;
        this.position = new Position(x,y);
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public Position getPosition() {
        return position;
    }
}
