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
public class FeedQueenTask implements Task {
    private QueenAnt queen;
    private boolean isComplete = false;
    private PathfindingMovement movementStrategy;
    /***
     *
     * @param queen the queen to feed
     */
    public FeedQueenTask(QueenAnt queen) {
        this.queen = queen;
    }

    public boolean isAssigned(){
        return false;
    }

    public int getPriority() {
        return 1; // Hårdkodat för tillfället
    }

    public Position getTargetLocation(){
        return queen.getPosition();
    }

    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    public AntMovement getMovementStrategy() {
        return movementStrategy;
    }

    /***
     * Currently only moves to queen
     * TODO: find food first (once food is implemented), feed queen (once feeding is implemented)
     * @param ant The ant to perform the task
     */
    public void execute(TaskPerformerAnt ant) {
        /*if (!ant.getPosition().equals(queenPosition)) {
            ant.setAntState(new WalkingState());
            ant.setMovementStrategy(new MoveToLocationStrategy(queenPosition));
            ant.getMovementStrategy().move(ant);
            return;
        }

        if (!(ant.getBehavior() instanceof FeedBehavior)) {
            ant.setBehavior(new FeedBehavior());
        }
        ant.setAntState(new WorkingState());
        ant.getBehavior().perform(ant);

        if (ant.hasFedQueen()) {
            isComplete = true;
            ant.setCurrentTask(null);
            ant.setAntState(new IdleState());
        }*/

        if(!ant.getPosition().equals(queen.getPosition())){
            if(!(ant.getMovement() instanceof PathfindingMovement)){
                ant.setMovement(new PathfindingMovement(
                        ant.getPosition(),
                        getTargetLocation(),
                        ant.getWorld().getTileGrid()
                ));
            }
        }
        else {
            //ant.feedQueen();
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getDescription(){
        return null;
    }

    @Override
    public void setAssigned(boolean status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAssigned'");
    }

}