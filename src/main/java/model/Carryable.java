package model;

import model.datastructures.Position;

/**
 * Represents an entity that can be carried and moved.
 */
public interface Carryable {
    public void move(Position position);
}
