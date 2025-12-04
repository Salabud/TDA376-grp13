package model.tasks;

import model.ants.Larva;
import model.ants.TaskPerformerAnt;
import model.ants.behavior.FeedingBehavior;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.state.AntState;
import model.datastructures.Position;

/**
 * Task for feeding a hungry larva.
 * A worker ant will pick up food, move to the larva, and feed it.
 */
public class FeedLarvaTask extends Task {
    private final Larva larva;

    /**
     * @param larva the larva to feed
     */
    public FeedLarvaTask(Larva larva) {
        super();
        this.larva = larva;
    }

    /**
     * Get the larva that this task is for.
     * @return the larva to be fed
     */
    public Larva getLarva() {
        return larva;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public Position getTargetLocation() {
        return larva.getPosition();
    }

    /**
     * Execute the feed larva task.
     * TODO: Currently just moves to larva and feeds. Should find food first.
     * @param ant The ant performing the task
     */
    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        getTargetLocation(),
                        ant.getWorld().getTileGrid()
                ));
                setPhase(TaskPhase.MOVING_TO_TARGET);
                break;
                
            case MOVING_TO_TARGET:
                if (ant.getPosition().isAdjacentTo(larva.getPosition())) {
                    ant.setState(AntState.WORKING);
                    ant.setMovement(new NoMovement());
                    ant.setBehavior(new FeedingBehavior(larva));
                    setPhase(TaskPhase.WORKING);
                }
                break;
                
            case WORKING:
                // FeedingBehavior handles the feeding process
                if (ant.getBehavior() == null || ant.getBehavior().isComplete()) {
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
            case NOT_STARTED -> "Waiting to feed larva";
            case MOVING_TO_TARGET -> "Moving to larva";
            case WORKING -> "Feeding larva";
            case COMPLETE -> "Finished feeding larva";
            default -> "Feed larva task";
        };
    }
}
