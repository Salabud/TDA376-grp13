package model.entity;

import model.world.WorldContext;
import org.json.JSONObject;

import model.datastructures.Position;

/**
 * Abstract base class for all entities in the simulation.
 * Contains common attributes and methods shared by all entities in te
 * simulation.
 */
public abstract class Entity implements Updateable {
    protected Position position;
    protected int movementInterval;
    protected EntityType type; // Final?
    protected int entityId;
    protected WorldContext surroundings;
    protected boolean removeFlag = false;

    public void update() {
    }

    public void setX(int x) {
        this.position.setX(x);
    }

    public int getX() {
        return this.position.getX();
    }

    public void setY(int y) {
        this.position.setY(y);
    }

    public int getY() {
        return this.position.getY();
    }

    public int getEntityId() {
        return this.entityId;
    }


    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public EntityType getType() {
        return this.type;
    }

    /**
     * Gets the movement interval of the entity.
     * Movement interval defines how often the entity can move.
     *
     * @return The movement interval in ticks.
     */
    public int getMovementInterval() {
        return this.movementInterval;
    }

    /**
     * Sets the movement interval of the entity.
     * Movement interval defines how often the entity can move.
     *
     * @param movementInterval The movement interval in ticks.
     */
    public void setMovementInterval(int movementInterval) {
        this.movementInterval = movementInterval;
    }


    /**
     * Create a JSON Object of the entity
     * 
     * @return
     */
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("x", position.getX());
        obj.put("y", position.getY());
        obj.put("entityId", entityId);
        obj.put("entityType", type);
        obj.put("movementInterval", movementInterval);
        return obj;
    }

    public void setWorldContext(WorldContext worldContext) {
        surroundings = worldContext;
    }
    public WorldContext getSurroundings(){
        return surroundings;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }
}
