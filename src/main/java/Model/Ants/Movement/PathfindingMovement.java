// Does not take in mind of collision if something has been added after choosing a path.

package Model.Ants.Movement;

import Model.Ants.Ant;
import Model.Datastructures.Position;
import Model.Algorithms.Pathfinding;

public class PathfindingMovement implements AntMovement{
    int currentStep = 0;
    private Position[] pathToGoal;
    int finalStep;

    public PathfindingMovement(Position start, Position goal){
        this.pathToGoal = Pathfinding.Astar(start, goal);
        this.finalStep = this.pathToGoal.length;
    }

    public void move(Ant ant){
        ant.setPosition(pathToGoal[currentStep]);
        currentStep++;
        if (currentStep == finalStep){
            notInMovement emptyMovement = new notInMovement();
            ant.setMovement(emptyMovement);
        }
    }
}
