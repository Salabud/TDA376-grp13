package model.entity;

import model.datastructures.Position;

/**
 * Represents an entity that can be carried and moved.
 */
public interface Carryable {
    /**
     * Move the carryable entity to a new position.
     * @param position : The new position to move to.
     */
    public void moveTo(Position position);
    public Position getPosition();
}
