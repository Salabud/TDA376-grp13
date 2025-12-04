package model.ants.behavior;

import model.Being;
import model.ants.Ant;

/**
 * Behavior strategy for feeding another Being.
 * The ant gradually restores the target's hunger over time.
 */
public class FeedingBehavior implements AntBehavior {
    
    private final Being target;
    private int feedingProgress;
    private boolean isComplete;
    
    private static final int FEEDING_DURATION = 30; // in ticks
    private static final float TOTAL_HUNGER_RESTORED = 50f;
    private static final float HUNGER_PER_TICK = TOTAL_HUNGER_RESTORED / FEEDING_DURATION;
    
    /**
     * Create a feeding behavior for a specific target.
     * 
     * @param target the Being to feed
     */
    public FeedingBehavior(Being target) {
        this.target = target;
        this.feedingProgress = 0;
        this.isComplete = false;
    }
    
    @Override
    public void perform(Ant ant) {
        if (isComplete) return;
        
        // Restore hunger to the target
        float newHunger = Math.min(target.getHunger() + HUNGER_PER_TICK, target.getMaxHunger());
        target.setHunger(newHunger);
        
        feedingProgress++;
        
        if (feedingProgress >= FEEDING_DURATION) {
            ant.setBehavior(null);
            isComplete = true;
        }
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    /**
     * Get feeding progress as a percentage (0-100).
     */
    public int getProgressPercent() {
        return (feedingProgress * 100) / FEEDING_DURATION;
    }
    
    /**
     * Get the target being fed.
     */
    public Being getTarget() {
        return target;
    }
}
