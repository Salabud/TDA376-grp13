package model.entity.being.ants;

import java.util.ArrayList;

import model.colony.events.HungryEvent;
import model.colony.tasks.EatTask;
import model.colony.tasks.FeedBeingTask;
import model.colony.tasks.MoveCarryableTask;
import model.colony.tasks.Task;
import model.datastructures.Position;
import model.entity.EntityType;
import model.entity.being.BeingType;

/** Represents the queen ant in the simulation. */
public class QueenAnt extends TaskPerformerAnt {
    private static final float DEFAULT_MAX_HEALTH = 200f;
    private static final float DEFAULT_MAX_HUNGER = 150f;
    private static final float HUNGER_THRESHOLD = 50f;
    private boolean hasReportedHunger = false;

    public QueenAnt(Position position) {
        this.type = EntityType.BEING;
        this.beingType = BeingType.ANT;
        this.antType = AntType.QUEEN;
        this.position = position;
        this.statuses = new ArrayList<>();
        
        // type-specific defaults
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.health = DEFAULT_MAX_HEALTH;
        this.maxHunger = DEFAULT_MAX_HUNGER;
        this.hunger = DEFAULT_MAX_HUNGER;
    }

    @Override
    public void update() {

        boolean isHungry = getHunger() < HUNGER_THRESHOLD;
        // Broadcast hunger event (hasReportedHunger prevents spamming)
        if (isHungry && !hasReportedHunger) {
            broadcastEvent(new HungryEvent(this));
            hasReportedHunger = true;
        }
        
        // Reset the flag once hunger is restored
        if (getHunger() >= HUNGER_THRESHOLD) {
            hasReportedHunger = false;
        }
        
        super.update();
    }

    @Override
    protected boolean isTaskTypeAllowed(Task task) {
        if (task instanceof FeedBeingTask) {
            return false;
        }
        // Queen cant eat on her own (she gets fed)
        if (task instanceof EatTask) {
            return false;
        }
        return !(task instanceof MoveCarryableTask);
    }

    public void layLarva(int amount){

    }
}
