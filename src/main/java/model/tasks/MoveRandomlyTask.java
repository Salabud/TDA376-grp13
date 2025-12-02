package model.tasks;

import model.ants.Ant;
import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.RandomMovement;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

public class MoveRandomlyTask implements Task{
    private Ant antAssigned;
    boolean isComplete = false;
    boolean isAssigned = false;
    private AntMovement movementStrategy= new RandomMovement();
    @Override
    public void execute(TaskPerformerAnt ant) {
        if(!(ant.getMovement() instanceof RandomMovement)){
            ant.setMovement(movementStrategy);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Position getTargetLocation() {
        return null;
    }

    @Override
    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    @Override
    public AntMovement getMovementStrategy() {
        return movementStrategy;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String getDescription() {
        return "Ant moves around randomly";
    }

    @Override
    public boolean isAssigned() {
        return isAssigned;
    }

    @Override
    public void setAssigned(boolean status) {
    isAssigned = status;
    }

}
