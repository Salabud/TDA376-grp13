package model.tasks;

import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.QueenAnt;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

/**
 * Task for feeding the queen ant.
 */
public class FeedQueenTask extends Task {
    private QueenAnt queen;
    private PathfindingMovement movementStrategy;
    
    /**
     * @param queen the queen to feed
     */
    public FeedQueenTask(QueenAnt queen) {
        super();
        this.queen = queen;
    }

    @Override
    public int getPriority() {
        return 1; // Hårdkodat för tillfället
    }

    @Override
    public Position getTargetLocation() {
        return queen.getPosition();
    }

    @Override
    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    @Override
    public AntMovement getMovementStrategy() {
        return movementStrategy;
    }

    /**
     * Currently only moves to queen
     * TODO: find food first (once food is implemented), feed queen (once feeding is implemented)
     * @param ant The ant to perform the task
     */
    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                setPhase(TaskPhase.MOVING_TO_TARGET);
                // Fall through
            case MOVING_TO_TARGET:
                if (!ant.getPosition().equals(queen.getPosition())) {
                    if (!(ant.getMovement() instanceof PathfindingMovement)) {
                        ant.setMovement(new PathfindingMovement(
                                ant.getPosition(),
                                getTargetLocation(),
                                ant.getWorld().getTileGrid()
                        ));
                    }
                } else {
                    setPhase(TaskPhase.WORKING);
                }
                break;
            case WORKING:
                // TODO: implement feeding behavior
                // ant.feedQueen();
                setPhase(TaskPhase.COMPLETE);
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
            case NOT_STARTED -> "Waiting to feed queen";
            case MOVING_TO_TARGET -> "Moving to queen";
            case WORKING -> "Feeding queen";
            case COMPLETE -> "Finished feeding queen";
            default -> "Feed queen task";
        };
    }
}