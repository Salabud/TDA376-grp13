package Model.Ants.Movement;

import Model.Ants.Ant;

/**
 * Interface for ant movement strategies, defining how an ant moves in the environment. 
 * E.g. NoMovement, PathfindingMovement, RandomMovement.
 */
public interface AntMovement {
    public void move(Ant ant);
}
