package model.ants.behavior;

import model.ants.Ant;
import model.colony.events.LarvaBirthEvent;

/**
 * Behavior strategy for giving birth to a larva.
 * The queen ant is stationary and birthing over time.
 * When complete, a LarvaBirthEvent is broadcast for the AntSpawner to handle.
 */
public class BirthBehavior implements AntBehavior {
    
    private int birthProgress;
    private boolean isComplete;
    
    private static final int BIRTH_DURATION = 120; // 2 seconds at 60 tps
    
    public BirthBehavior() {
        this.birthProgress = 0;
        this.isComplete = false;
    }
    
    @Override
    public void perform(Ant ant) {
        if (isComplete) return;
        
        birthProgress++;
        
        if (birthProgress >= BIRTH_DURATION) {
            // Broadcast event for AntSpawner to handle spawning
            ant.broadcastEvent(new LarvaBirthEvent(ant, ant.getPosition()));
            isComplete = true;
        }
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    /**
     * Get birth progress as a percentage (0-100).
     */
    public int getProgressPercent() {
        return (birthProgress * 100) / BIRTH_DURATION;
    }
}
