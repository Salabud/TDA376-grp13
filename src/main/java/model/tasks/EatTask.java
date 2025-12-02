package model.tasks;

import model.ants.behavior.AntBehavior;
import model.ants.behavior.EatBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.state.AntState;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

public class EatTask extends Task {
    private Position foodPosition;
    private int eatingProgress;
    
    /** Ticks required to finish eating. */
    private static final int EATING_DURATION = 60;
    
    public EatTask(Position foodPosition) {
        super();  // Initializes phase = NOT_STARTED, isAssigned = false
        this.foodPosition = foodPosition;
        this.eatingProgress = 0;
    }
    
    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                setPhase(TaskPhase.MOVING_TO_TARGET);
                ant.setState(AntState.MOVING);
            case MOVING_TO_TARGET:
                handleMovingPhase(ant);
                break;
            case WORKING:
                handleEatingPhase(ant);
                break;
            case COMPLETE:
                break;
            default:
                break;
        }
    }
    
    private void handleMovingPhase(TaskPerformerAnt ant) {
        if (ant.getState() != AntState.MOVING) {
            ant.setState(AntState.MOVING);
        }
        
        if (ant.getMovement() instanceof NoMovement) {
            ant.setMovement(new PathfindingMovement(
                ant.getPosition(),
                getTargetLocation(),
                ant.getWorld().getTileGrid()
            ));
        }
        
        if (ant.getPosition().isAdjacentTo(foodPosition)) {
            setPhase(TaskPhase.WORKING);
            ant.setState(AntState.EATING);
            ant.setMovement(new NoMovement());
            ant.setBehavior(new EatBehavior());
        }
    }
    
    private void handleEatingPhase(TaskPerformerAnt ant) {
        // Let behavior handle the per-tick eating effect
        if (ant.getBehavior() != null) {
            ant.getBehavior().perform(ant);
        }
        
        eatingProgress++;
        
        if (eatingProgress >= EATING_DURATION) {
            ant.setBehavior(null);
            ant.setState(AntState.RESTING);
            setPhase(TaskPhase.COMPLETE);
        }
    }
    
    @Override
    public int getPriority() {
        return 10;  // High priority, eating is important!
    }

    @Override
    public Position getTargetLocation() {
        return foodPosition;
    }

    @Override
    public AntBehavior getBehaviorStrategy() {
        return new EatBehavior();
    }

    @Override
    public AntMovement getMovementStrategy() {
        return null;
    }

    @Override
    public String getDescription() {
        return switch (phase) {
            case NOT_STARTED -> "Waiting to start";
            case MOVING_TO_TARGET -> "Moving to food";
            case WORKING -> "Eating (" + (eatingProgress * 100 / EATING_DURATION) + "%)";
            case COMPLETE -> "Finished eating";
            default -> "Eating task";
        };
    }
}
