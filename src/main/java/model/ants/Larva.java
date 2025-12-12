package model.ants;

import model.AntType;
import model.BeingType;
import model.Carryable;
import model.colony.events.HungryEvent;
import model.colony.events.LarvaTransformEvent;
import model.datastructures.Position;
import model.EntityType;

import java.util.ArrayList;

/** Represents a larva in the simulation. */
public class Larva extends Ant implements Carryable {
    private static final float DEFAULT_MAX_HEALTH = 50f;
    private static final float DEFAULT_MAX_HUNGER = 50f;
    private static final float TRANSFORM_AGE = 1 * 10;
    private static final float HUNGER_THRESHOLD = 30f;
    private boolean hasReportedHunger = false;
    private boolean hasRequestedTransform = false;

    public Larva(Position position) {
        this.position = position;
        this.type = EntityType.BEING;
        this.beingType = BeingType.ANT;
        this.antType = AntType.LARVA;
        this.statuses = new ArrayList<>();
        
        // type-specific defaults
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.health = DEFAULT_MAX_HEALTH;
        this.maxHunger = DEFAULT_MAX_HUNGER;
        this.hunger = DEFAULT_MAX_HUNGER;
    }

    @Override
    public void moveTo(Position position) {
        this.position = position;
    }

    @Override
    public void update(){
        // Broadcast hunger event (hasReportedHunger prevents spamming)
        if (getHunger() < HUNGER_THRESHOLD && !hasReportedHunger) {
            broadcastEvent(new HungryEvent(this));
            hasReportedHunger = true;
        }
        
        // Reset the flag once hunger is restored
        if (getHunger() >= HUNGER_THRESHOLD && hasReportedHunger) {
            hasReportedHunger = false;
        }
        
        // Request transformation when old enough
        System.out.println(this.getAge());
        if (this.getAge() > TRANSFORM_AGE && !hasRequestedTransform) {
            broadcastEvent(new LarvaTransformEvent(this));
            hasRequestedTransform = true;
        }
        
        super.update();
    }
}
