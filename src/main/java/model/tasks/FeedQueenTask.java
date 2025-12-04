package model.tasks;

import model.ants.behavior.AntBehavior;
import model.ants.behavior.EatBehavior;
import model.ants.behavior.FeedBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.QueenAnt;
import model.ants.TaskPerformerAnt;
import model.ants.state.AntState;
import model.datastructures.Position;
import model.world.Item;
import model.world.MaterialType;

/**
 * Task for feeding the queen ant.
 */
public class FeedQueenTask extends Task {
    private QueenAnt queen;
    private Item food;
    private PathfindingMovement movementStrategy;

    /**
     * @param queen the queen to feed
     */
    public FeedQueenTask(QueenAnt queen, Item food) {
        super();
        this.queen = queen;
        this.food = food;
    }

    @Override
    public int getPriority() {
        return 1; // Hårdkodat för tillfället
    }

    @Override
    public Position getTargetLocation() {
        if(this.phase == TaskPhase.RETURNING){
            return queen.getPosition();
        }
        if(this.phase == TaskPhase.NOT_STARTED){
            return food.getPosition();
        }
        return null;
    }

    /**
     * Currently only moves to queen
     * TODO: find food first (once food is implemented), feed queen (once feeding is implemented)
     * @param ant The ant to perform the task
     */
    @Override
    public void execute(TaskPerformerAnt ant) {

        switch(phase){
            case NOT_STARTED:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        getTargetLocation(),
                        ant.getWorld().getTileGrid()
                ));
                ant.setBehavior(null);
                setPhase(TaskPhase.MOVING_TO_TARGET);
                break;

            case MOVING_TO_TARGET:
                if (ant.getPosition().isAdjacentTo(food.getPosition())){
                    ant.setState(AntState.WORKING);
                    ant.setMovement(new NoMovement());
                    setPhase(TaskPhase.RETURNING);
                    ant.attemptCarry(food);
                }
                break;

            case RETURNING:
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        queen.getPosition(),
                        ant.getWorld().getTileGrid()
                ));
                if (ant.getPosition().isAdjacentTo(queen.getPosition())) {
                    ant.setState(AntState.FEEDING);
                    ant.dropCarriedObject();
                    ant.setBehavior(new FeedBehavior(food, queen));
                    setPhase(TaskPhase.WORKING);
                }

            case WORKING:

                if (ant.getBehavior() != null && ant.getBehavior().isComplete()) {
                    ant.setState(AntState.RESTING);
                    ant.setMovement(new NoMovement());
                    ant.setBehavior(null);
                    ant.setBehavior(null);
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
        return switch (phase) {
            case NOT_STARTED -> "Waiting to feed queen";
            case MOVING_TO_TARGET -> "Moving to queen";
            case WORKING -> "Feeding queen";
            case COMPLETE -> "Finished feeding queen";
            default -> "Feed queen task";
        };
    }

}