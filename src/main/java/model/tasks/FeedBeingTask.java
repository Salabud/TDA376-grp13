package model.tasks;

import model.Being;
import model.ants.TaskPerformerAnt;
import model.ants.behavior.FeedBehavior;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.state.AntState;
import model.datastructures.Position;
import model.world.Item;

/**
 * Task for feeding any Being by fetching food and bringing it to the target.
 */
public class FeedBeingTask extends Task {
    private final Being target;
    private final Item food;
    private final int priority;
    private final String targetName;

    /**
     * Create a feeding task that fetches food and brings it to the target.
     * 
     * @param target : the Being to feed
     * @param food : the food item to fetch and use
     * @param priority : the task priority
     * @param targetName : descriptive name for the target (e.g., "queen", "larva")
     */
    public FeedBeingTask(Being target, Item food, int priority, String targetName) {
        super();
        this.target = target;
        this.food = food;
        this.priority = priority;
        this.targetName = targetName;
    }

    /**
     * Get the Being that this task is for.
     * @return the target to be fed
     */
    public Being getTarget() {
        return target;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Position getTargetLocation() {
        return switch (phase) {
            case NOT_STARTED, MOVING_TO_TARGET -> food.getPosition();
            case RETURNING, WORKING -> target.getPosition();
            default -> null;
        };
    }

    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        food.getPosition(),
                        ant.getWorld().getTileGrid()
                ));
                ant.setBehavior(null);
                setPhase(TaskPhase.MOVING_TO_TARGET);
                break;

            case MOVING_TO_TARGET:
                if (ant.getPosition().isAdjacentTo(food.getPosition())) {
                    ant.setState(AntState.WORKING);
                    ant.setMovement(new NoMovement());
                    ant.attemptCarry(food);
                    setPhase(TaskPhase.RETURNING);
                }
                break;

            case RETURNING:
                if (!(ant.getMovement() instanceof PathfindingMovement)) {
                    ant.setState(AntState.MOVING);
                    ant.setMovement(new PathfindingMovement(
                            ant.getPosition(),
                            target.getPosition(),
                            ant.getWorld().getTileGrid()
                    ));
                }
                if (ant.getPosition().isAdjacentTo(target.getPosition())) {
                    ant.setState(AntState.FEEDING);
                    ant.dropCarriedObject();
                    ant.setBehavior(new FeedBehavior(food, target));
                    setPhase(TaskPhase.WORKING);
                }
                break;

            case WORKING:
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
    public String getDescription() {
        return switch (phase) {
            case NOT_STARTED -> "Waiting to feed " + targetName;
            case MOVING_TO_TARGET -> "Moving to food";
            case RETURNING -> "Bringing food to " + targetName;
            case WORKING -> "Feeding " + targetName;
            case COMPLETE -> "Finished feeding " + targetName;
            default -> "Feed " + targetName + " task";
        };
    }
}
