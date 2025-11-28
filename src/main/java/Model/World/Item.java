package Model.World;

import Model.Datastructures.Position;
import Model.Entity;
import Model.EntityType;

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
