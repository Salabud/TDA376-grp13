package model.world;

import org.json.JSONObject;

import model.Carryable;
import model.Entity;
import model.EntityIdManager;
import model.EntityType;
import model.datastructures.Position;

/**
 * Represents an item in the world with a specific material type.
 * The pickupable equivalent of a broken tile.
 */
public class Item extends Entity implements Carryable {
    private MaterialType materialType;
    private boolean scouted;

    public Item(Position position, MaterialType materialType) {
        this.type = EntityType.ITEM;
        this.position = position;
        this.materialType = materialType;
        this.scouted = false;
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

    public void moveTo(Position position) {
        this.removePositionFromEntityGrid();
        this.position = position;
        this.addPositionToEntityGrid();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("materialType", materialType);
        return obj;
    }
    public boolean isScouted() {
        return scouted;
    }
    public boolean setScouted(boolean scouted) {
        return this.scouted = scouted;
    }
}
