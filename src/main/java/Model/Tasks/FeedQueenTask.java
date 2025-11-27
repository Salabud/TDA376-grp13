package Model.Tasks;

import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.QueenAnt;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

public class FeedQueenTask implements Task {
    private QueenAnt queen;
    private boolean isComplete = false;

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
        return null;
    }
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
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getDescription(){
        return null;
    }
}