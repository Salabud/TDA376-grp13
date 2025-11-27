package Model.Tasks;

import Model.Ants.Ant;
import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.Movement.NoMovement;
import Model.Ants.Movement.PathfindingMovement;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

public class TemporaryTestTask implements Task{
    private Ant antAssigned;
    boolean isComplete = false;
    boolean isAssigned = false;
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Position getTargetLocation() {
        return new Position(100, 100);
    }

    @Override
    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    @Override
    public AntMovement getMovementStrategy() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void execute(TaskPerformerAnt ant) {
        System.out.println(ant.getMovement());
        if (!(ant.getMovement() instanceof NoMovement)){
            ant.setMovement(new PathfindingMovement(ant.getPosition(), getTargetLocation()));
        }
    }

    @Override
    public String getDescription() {
        return "Temporary task to test movement implementation";
    }

    @Override
    public boolean isAssigned() {
        return isAssigned;
    }
}
