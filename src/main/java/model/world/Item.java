package model.world;

import model.Carryable;
import model.EntityIdManager;
import model.datastructures.Position;
import model.Entity;
import model.EntityType;
import org.json.JSONObject;

/**
 * Represents an item in the world with a specific material type.
 * The pickupable equivalent of a broken tile.
 */
public class Item extends Entity implements Carryable {
    private MaterialType materialType;

    public Item(Position position, MaterialType materialType){
        this.type = EntityType.ITEM;
        this.position = position;
        this.materialType = materialType;
        this.entityId = EntityIdManager.getInstance().getNextId();
    }

    public Item(Position position, MaterialType materialType, int entityId) {
        this.type = EntityType.ITEM;
        this.position = position;
        this.materialType = materialType;
        this.entityId = entityId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void moveTo(Position position){
        this.position = position;
    }
    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        obj.put("materialType", materialType);
        return obj;
    }
}
