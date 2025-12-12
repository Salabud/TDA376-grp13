package model.colony.tasks;

import model.entity.being.ants.AntType;
import model.entity.being.ants.TaskPerformerAnt;
import model.entity.being.ants.behavior.BirthBehavior;
import model.entity.being.ants.movement.NoMovement;
import model.entity.being.ants.movement.PathfindingMovement;
import model.entity.being.ants.state.AntState;
import model.datastructures.Position;

/**
 * Task for the queen to give birth to a larva.
 * The queen performs BirthBehavior which broadcasts a LarvaBirthEvent
 * for the AntSpawner to handle spawning.
 */
public class BirthTask extends Task {

    /**
     * Creates a birth task.
     */
    public BirthTask() {
        super();
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public Position getTargetLocation() {
        return null;
    }
    
    @Override
    public AntType getRequiredAntType() {
        return AntType.QUEEN;
    }

    @Override
    public void execute(TaskPerformerAnt ant) {
        switch (phase) {
            case NOT_STARTED:
                ant.setState(AntState.WORKING);
                ant.setMovement(new NoMovement());
                ant.setBehavior(new BirthBehavior());
                setPhase(TaskPhase.WORKING);
                break;

            case WORKING:
                if (ant.getBehavior() == null || ant.getBehavior().isComplete()) {
                    ant.setState(AntState.RESTING);
                    ant.setBehavior(null);
                    setPhase(TaskPhase.MOVING_TO_TARGET);
                }
                break;

            case MOVING_TO_TARGET:
                //TODO: Get rid of hardcoded position
                Position birthLocation = new Position(ant.getX()+2,ant.getY());
                ant.setState(AntState.MOVING);
                ant.setMovement(new PathfindingMovement( // TODO: Change this to "closest empy tile"-movement
                        ant.getPosition(),
                        birthLocation,
                        ant.getSurroundings().getTileGrid()));
                ant.setBehavior(null);
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
            case NOT_STARTED -> "Preparing to give birth";
            case WORKING -> "Giving birth to larva";
            case COMPLETE -> "Finished giving birth";
            default -> "Birth larva task";
        };
    }
}
