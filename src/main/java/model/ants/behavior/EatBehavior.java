package model.ants.behavior;

import model.ants.Ant;

/**
 * Behavior strategy for eating. 
 * The ant is stationary and consuming food, gradually restoring hunger.
 */
public class EatBehavior implements AntBehavior {
    
    /** Hunger restored per tick (spreads restoration over eating duration). */
    private static final float HUNGER_PER_TICK = 50f / 60f;  // ~0.83 per tick
    
    @Override
    public void perform(Ant ant) {
        float newHunger = Math.min(ant.getHunger() + HUNGER_PER_TICK, ant.getMaxHunger());
        ant.setHunger(newHunger);
    }
}
