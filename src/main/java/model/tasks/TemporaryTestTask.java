package model.tasks;

import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.TaskPerformerAnt;
import model.datastructures.Position;

/**
 * A temporary task used for testing movement implementations.
 */
public class TemporaryTestTask extends Task {
    
    public TemporaryTestTask() {
        super();
    }
    
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Position getTargetLocation() {
        return new Position(99, 99);
    }

    @Override
    public AntBehavior getBehaviorStrategy() {
        return null;
    }

    @Override
    public AntMovement getMovementStrategy() {
        return null;
    }

    @Override
    public void execute(TaskPerformerAnt ant) {
        if (ant.getMovement() instanceof NoMovement) {
            ant.setMovement(new PathfindingMovement(
                ant.getPosition(), 
                getTargetLocation(), 
                ant.getWorld().getTileGrid()
            ));
            setPhase(TaskPhase.MOVING_TO_TARGET);
        }
        
        // Check if ant reached the target
        if (ant.getPosition().equals(getTargetLocation())) {
            setPhase(TaskPhase.COMPLETE);
        }
    }

    @Override
    public String getDescription() {
        return "Temporary task to test movement implementation";
    }
}
