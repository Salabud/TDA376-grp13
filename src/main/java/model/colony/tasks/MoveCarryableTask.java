package model.colony.tasks;

import model.entity.Carryable;
import model.entity.being.ants.Ant;
import model.entity.being.ants.TaskPerformerAnt;
import model.entity.being.ants.behavior.AntBehavior;
import model.entity.being.ants.movement.AntMovement;
import model.entity.being.ants.movement.NoMovement;
import model.entity.being.ants.movement.PathfindingMovement;
import model.entity.being.ants.state.AntState;
import model.datastructures.Position;

/**
 * A temporary task used for testing movement implementations.
 * Implements a simple carry behaviour: go to (5,5), pick up food there,
 * walk to (10,10) and drop it.
 */
public class MoveCarryableTask extends Task{
    private Ant antAssigned;
    boolean isComplete = false;
    boolean isAssigned = false;
    int step = 1;
    Position carryablePosition;
    Position endPosition;
    Carryable carryableObject;
    public MoveCarryableTask(Carryable carryableObject, Position endPosition){
        super();
        this.carryablePosition = carryableObject.getPosition();
        this.endPosition = endPosition;
        this.carryableObject = carryableObject;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Position getTargetLocation() {
        return carryablePosition;  // Initial target is the carryable object
    }


    public Position getCarryablePosition() {
        return this.carryablePosition;
    }

    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    public AntMovement getMovementStrategy() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void execute(TaskPerformerAnt ant) {

        switch(phase){
            case NOT_STARTED:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        getTargetLocation(),
                        ant.getSurroundings().getTileGrid()
                ));
                ant.setBehavior(null);
                setPhase(TaskPhase.MOVING_TO_TARGET);
                break;

            case MOVING_TO_TARGET:
                if (ant.getPosition().isAdjacentTo(carryablePosition)){
                    ant.setState(AntState.WORKING);
                    ant.setMovement(new NoMovement());
                    setPhase(TaskPhase.RETURNING);
                    ant.attemptCarry(carryableObject);
                }
                break;

            case RETURNING:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        endPosition,
                        ant.getSurroundings().getTileGrid()
                ));
                if (ant.getPosition().isAdjacentTo(carryablePosition)){
                    setPhase(TaskPhase.COMPLETE);
                }
            case COMPLETE:
                break;
            default:
                break;
        }
    }
    @Override
    public String getDescription() {
        return "Carry food from (5,5) to (10,10)";
    }

    @Override
    public boolean isAssigned() {
        return isAssigned;
    }

    @Override
    public void setAssigned(boolean status) {
        this.isAssigned = status;
    }
}
