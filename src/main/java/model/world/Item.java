package model.world;

import model.Carryable;
import model.datastructures.Position;
import model.Entity;
import model.EntityType;
import org.json.JSONObject;

/**
 * Represents an item in the world with a specific material type.
 * The pickupable equivalent of a broken tile.
 * TODO: Implement carryable interface
 */
public class Item extends Entity implements Carryable {
    private MaterialType materialType;

    public Item(Position position, MaterialType materialType){
        this.type = EntityType.ITEM;
        this.position = position;
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void move(Position position){

    }
    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        obj.put("materialType", materialType);
        return obj;
    }
}
