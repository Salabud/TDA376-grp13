package model.ants.behavior;

import model.Being;
import model.ants.Ant;
import model.world.Item;

/**
 * Behavior strategy for eating. 
 * The ant is stationary and consuming food, gradually restoring hunger.
 * When complete, the food item is destroyed.
 */
public class FeedBehavior implements AntBehavior {

    private final Item foodItem;
    private int eatingProgress;
    private boolean isComplete;
    private Being targetBeing;

    private static final int EATING_DURATION = 60; // in ticks
    private static final float TOTAL_HUNGER_RESTORED = 50f;
    private static final float HUNGER_PER_TICK = TOTAL_HUNGER_RESTORED / EATING_DURATION;

    /**
     * Create an eating behavior for a specific food item.
     *
     * @param foodItem the food item to consume
     */
    public FeedBehavior(Item foodItem, Being targetBeing) {
        this.foodItem = foodItem;
        this.eatingProgress = 0;
        this.isComplete = false;
        this.targetBeing = targetBeing;
    }

    @Override
    public void perform(Ant ant) {
        if (isComplete) return;

        float newHunger = Math.min(targetBeing.getHunger() + HUNGER_PER_TICK, targetBeing.getMaxHunger());
        targetBeing.setHunger(newHunger);

        eatingProgress++;
        if (eatingProgress >= EATING_DURATION) {
            if (foodItem != null) {
                ant.getWorld().removeEntity(foodItem);
            }
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
