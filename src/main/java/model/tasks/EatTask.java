package model.tasks;

import model.ants.behavior.EatBehavior;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.state.AntState;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;
import model.world.Item;

public class EatTask extends Task {
    private final Item foodItem;
    private final Position foodPosition;
    
    public EatTask(Item foodItem) {
        super();
        this.foodItem = foodItem;
        this.foodPosition = foodItem.getPosition();
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
                    ant.setBehavior(new EatBehavior(foodItem));
                    setPhase(TaskPhase.WORKING);
                }
                break;
                
            case WORKING:
                // Behavior handles eating progress - check if it's done
                if (ant.getBehavior() != null && ant.getBehavior().isComplete()) {
                    ant.setState(AntState.RESTING);
                    ant.setMovement(new NoMovement());
                    ant.setBehavior(null);
                    setPhase(TaskPhase.COMPLETE);
                }
                break;
                
            case COMPLETE:
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
            case WORKING -> "Eating...";
            case COMPLETE -> "Finished eating";
            default -> "Eating task";
        };
    }
}
