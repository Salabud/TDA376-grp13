package model.entity.being.ants.movement;

import model.entity.being.ants.Ant;

/**
 * Interface for ant movement strategies, defining how an ant moves in the environment. 
 * E.g. NoMovement, PathfindingMovement, RandomMovement.
 * 
 * Movement strategies handle only the movement logic. The caller (Ant or Task)
 * is responsible for checking completion and deciding what happens next.
 */
public interface AntMovement {
    /**
     * Execute one step of movement.
     * @param ant the ant to move
     */
    void move(Ant ant);
    
    /**
     * Check if this movement strategy has completed its work.
     * @return true if movement is complete (e.g., reached destination)
     */
    boolean isComplete();
}
