package model.ants.movement;

import model.ants.Ant;
import model.datastructures.Position;
import model.algorithms.Pathfinding;
import model.world.Tile;

/**
 * Implements a movement strategy where the ant follows a precomputed path to a goal using A* pathfinding.
 * 
 * Note: Does not handle dynamic obstacles atm, path is computed once at construction.
 */
public class PathfindingMovement implements AntMovement {
    private int currentStep = 0;
    private final Position[] pathToGoal;
    
    public PathfindingMovement(Position start, Position goal, Tile[][] tileGrid) {
        this.pathToGoal = Pathfinding.Astar(start, goal, tileGrid);
    }
    
    @Override
    public void move(Ant ant) {
        if (isComplete()) return;
        
        ant.setPosition(pathToGoal[currentStep]);
        currentStep++;
    }
    
    @Override
    public boolean isComplete() {
        return pathToGoal == null || currentStep >= pathToGoal.length;
    }
    
    /**
     * Get current progress through the path.
     * @return current step index
     */
    public int getCurrentStep() {
        return currentStep;
    }
    
    /**
     * Get total path length.
     * @return number of steps in path
     */
    public int getPathLength() {
        return pathToGoal != null ? pathToGoal.length : 0;
    }
}
