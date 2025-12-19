package model.entity.item;

import model.world.MaterialType;
import model.world.World;
import org.json.JSONObject;

import model.entity.Carryable;
import model.entity.Entity;
import model.entity.EntityIdManager;
import model.entity.EntityType;
import model.datastructures.Position;

/**
 * Represents an item in the world with a specific material type.
 * The pickupable equivalent of a broken tile.
 */
public class Item extends Entity implements Carryable {
    private final MaterialType materialType;
    private boolean scouted;

    public Item(World world, Position position, MaterialType materialType) {
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
        this.position = position;
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
