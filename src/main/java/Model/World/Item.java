package Model.World;

import Model.Being;
import Model.Datastructures.Position;
import Model.Entity;
import Model.EntityType;

/**
 * Represents an item in the world with a specific material type.
 * The pickupable equivalent of a broken tile.
 * TODO: Implement carryable interface
 */
public class Item extends Entity {
    private MaterialType materialType;

    public Item(int x, int y, MaterialType materialType){
        this.type = EntityType.ITEM;
        this.position = new Position(x,y);
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}
