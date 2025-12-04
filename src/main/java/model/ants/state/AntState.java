package model.ants.state;

/**
 * Enum representing the different states an ant can be in. 
 * 
 * <p>Used to handle transitions between movement and 
 * behavior strategies while e.g. executing a task. Follows state pattern.
 */
public enum AntState {
    IDLE,
    MOVING,
    WORKING,
    EATING,
    FEEDING,
    RESTING,
    DEAD,
}
