// Does not take in mind of collision if something has been added after choosing a path.

package Model.Ants.Movement;

import Model.Ants.Ant;
import Model.Datastructures.Position;
import Model.Algorithms.Pathfinding;
import Model.World.Tile;

import java.util.List;

/**
 * Implements a movement strategy where the ant follows a precomputed path to a goal using A* pathfinding.
 */
public class PathfindingMovement implements AntMovement{
    int currentStep = 0;
    private Position[] pathToGoal;
    int finalStep;

    public PathfindingMovement(Position start, Position goal, Tile[][] tileGrid){
        this.pathToGoal = Pathfinding.Astar(start, goal, tileGrid);
        this.finalStep = this.pathToGoal.length;
    }

    public void move(Ant ant){
        if (currentStep >= finalStep) {
            // Path complete or no path found
            ant.setMovement(new NoMovement());
            return;
        }
        ant.setPosition(pathToGoal[currentStep]);
        currentStep++;
    }
}
