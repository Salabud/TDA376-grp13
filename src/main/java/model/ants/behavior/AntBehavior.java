package model.ants.behavior;

import model.ants.Ant;

/**
 * Interface for ant behavior strategies, defining what actions an ant takes during a task.
 * E.g. Nursing, Digging, Foraging, Eating.
 * 
 * Behaviors handle the per-tick effects of an action, while Tasks control
 * the overall flow and phase transitions.
 */
public interface AntBehavior {
    /**
     * Perform one tick of this behavior's action.
     * 
     * @param ant the ant performing this behavior
     */
    void perform(Ant ant);
    
    /**
     * Check if this behavior has completed its work.
     * Used by tasks to know when to transition to the next phase.
     * 
     * @return true if the behavior is finished
     */
    boolean isComplete();
}
