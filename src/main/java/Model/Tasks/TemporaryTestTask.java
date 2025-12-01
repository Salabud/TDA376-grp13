package Model.Tasks;

import Model.Ants.Ant;
import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.Movement.NoMovement;
import Model.Ants.Movement.PathfindingMovement;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

/**
 * A temporary task used for testing movement implementations.
 */
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
        return new Position(99, 99);  // Within 100x100 grid bounds (0-99)
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
        if (ant.getMovement() instanceof NoMovement) {
            ant.setMovement(new PathfindingMovement(
                ant.getPosition(), 
                getTargetLocation(), 
                ant.getWorld().getTileGrid()
            ));
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

    @Override
    public void setAssigned(boolean status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAssigned'");
    }
}
