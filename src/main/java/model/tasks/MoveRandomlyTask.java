package model.tasks;

import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.RandomMovement;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

public class MoveRandomlyTask extends Task {
    private AntMovement movementStrategy = new RandomMovement();
    
    public MoveRandomlyTask() {
        super();
    }
    
    @Override
    public void execute(TaskPerformerAnt ant) {
        if (!(ant.getMovement() instanceof RandomMovement)) {
            ant.setMovement(movementStrategy);
            setPhase(TaskPhase.WORKING);
        }
        // Note: This task never completes - ant moves randomly forever
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Position getTargetLocation() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Ant moves around randomly";
    }
}
