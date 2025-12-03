package model.ants.behavior;

import model.ants.Ant;
import model.world.Item;

/**
 * Behavior strategy for eating. 
 * The ant is stationary and consuming food, gradually restoring hunger.
 * When complete, the food item is destroyed.
 */
public class EatBehavior implements AntBehavior {
    
    private final Item foodItem;
    private int eatingProgress;
    private boolean isComplete;
    
    private static final int EATING_DURATION = 60; // in ticks
    private static final float TOTAL_HUNGER_RESTORED = 50f;
    private static final float HUNGER_PER_TICK = TOTAL_HUNGER_RESTORED / EATING_DURATION;
    
    /**
     * Create an eating behavior for a specific food item.
     * 
     * @param foodItem the food item to consume
     */
    public EatBehavior(Item foodItem) {
        this.foodItem = foodItem;
        this.eatingProgress = 0;
        this.isComplete = false;
    }
    
    @Override
    public void perform(Ant ant) {
        if (isComplete) return;
        
        float newHunger = Math.min(ant.getHunger() + HUNGER_PER_TICK, ant.getMaxHunger());
        ant.setHunger(newHunger);
        
        eatingProgress++;
        
        if (eatingProgress >= EATING_DURATION) {
            ant.getWorld().removeEntity(foodItem);
            isComplete = true;
        }
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    /**
     * Get eating progress as a percentage (0-100).
     */
    public int getProgressPercent() {
        return (eatingProgress * 100) / EATING_DURATION;
    }
}
