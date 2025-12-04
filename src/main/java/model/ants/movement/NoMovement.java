package model.ants.movement;

import model.ants.Ant;

/**
 * Implements a movement strategy where the ant does not move.
 * This is the default/idle movement state.
 */
public class NoMovement implements AntMovement {
    
    @Override
    public void move(Ant ant) {
        // Do nothing - ant stays in place
    }
    
    @Override
    public boolean isComplete() {
        // NoMovement is always "complete" - there's nothing to do
        return true;
    }
}
