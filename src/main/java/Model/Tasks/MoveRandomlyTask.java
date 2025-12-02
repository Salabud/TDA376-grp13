package Model.Tasks;

import Model.Ants.Ant;
import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.Movement.RandomMovement;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

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
        movementStrategy.move(ant);
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
