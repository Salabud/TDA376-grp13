package model.colony.tasks;

import model.ants.movement.RandomMovement;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

public class MoveRandomlyTask extends Task {
    
    public MoveRandomlyTask() {
        super();
    }
    
    @Override
    public void execute(TaskPerformerAnt ant) {
        // Create new RandomMovement when current one completes (or on first call)
        if (!(ant.getMovement() instanceof RandomMovement) || ant.getMovement().isComplete()) {
            ant.setMovement(new RandomMovement(ant, ant.getSurroundings().getTileGrid()));
            setPhase(TaskPhase.WORKING);
        }
        // Note: This task never completes - ant picks new random goals forever
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
