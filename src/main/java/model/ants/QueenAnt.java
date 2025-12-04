package model.ants;

import model.AntType;
import model.BeingType;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.tasks.Task;
import model.world.World;

/** Represents the queen ant in the simulation. */
public class QueenAnt extends TaskPerformerAnt {
    private static final float HUNGER_THRESHOLD = 50f; // Report hunger when below this level
    private boolean hasReportedHunger = false; // Prevent spamming reports

    public QueenAnt(EntityType type, World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.type = EntityType.BEING;
        this.beingType = BeingType.ANT;
        this.antType = AntType.QUEEN;
        this.world = world;
        this.colonyId = colonyId;
        this.position = new Position(x,y);
        this.mediator = mediator;
    }

    @Override
    public void update() {
        // Report hunger to mediator when below threshold
        if (getHunger() < HUNGER_THRESHOLD && !hasReportedHunger && mediator != null) {
            mediator.reportQueenHungry(this);
            hasReportedHunger = true;
        }
        
        // Reset the flag once hunger is restored
        if (getHunger() >= HUNGER_THRESHOLD) {
            hasReportedHunger = false;
        }
        
        super.update();
    }

    @Override
    public boolean isAvailableForTask(Task task) {
        return false; //Hardcoded for now until we add "BirthLarvaTask"
    }

    public void layLarva(int amount){

    }
}
