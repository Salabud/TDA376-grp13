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
    private static final int EATING_DURATION = 600; // temp value to debugging
    
    public EatTask(Position foodPosition) {
        super();  // Initializes phase = NOT_STARTED, isAssigned = false
        this.foodPosition = foodPosition;
        this.eatingProgress = 0;
    }
    
    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                    ant.getPosition(),
                    foodPosition,
                    ant.getWorld().getTileGrid()
                ));
                ant.setBehavior(null);
                setPhase(TaskPhase.MOVING_TO_TARGET);
                break;
                
            case MOVING_TO_TARGET:
                if (ant.getPosition().isAdjacentTo(foodPosition)) {
                    ant.setState(AntState.EATING);
                    ant.setMovement(new NoMovement());
                    ant.setBehavior(new EatBehavior());
                    setPhase(TaskPhase.WORKING);
                }
                break;
                
            case WORKING:
                // Track eating progress (behavior handles hunger restoration)
                eatingProgress++;
                if (eatingProgress >= EATING_DURATION) {
                    ant.setState(AntState.RESTING);
                    ant.setMovement(new NoMovement());
                    ant.setBehavior(null);
                    setPhase(TaskPhase.COMPLETE);
                }
                break;
                
            case COMPLETE:
                // Task done, nothing to configure
                break;
                
            default:
                break;
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
