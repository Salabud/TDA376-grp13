package model.tasks;

import model.AntType;
import model.ants.TaskPerformerAnt;
import model.ants.behavior.BirthBehavior;
import model.ants.movement.NoMovement;
import model.ants.movement.PathfindingMovement;
import model.ants.state.AntState;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import model.datastructures.Position;

/**
 * Task for the queen to give birth to a larva.
 * The queen performs BirthBehavior to spawn a new larva.
 */
public class BirthTask extends Task {
    
    private final AntColony colony;
    private final ColonyMediator mediator;

    /**
     * Create a birth larva task.
     * 
     * @param colony the colony to add the new larva to
     * @param mediator the mediator for the new larva
     */
    public BirthTask(AntColony colony, ColonyMediator mediator) {
        super();
        this.colony = colony;
        this.mediator = mediator;
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
                ant.setBehavior(new BirthBehavior(colony, mediator));
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
                        ant.getWorld().getTileGrid()));
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
