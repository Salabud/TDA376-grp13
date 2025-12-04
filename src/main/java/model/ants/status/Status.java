package model.ants.status;

import model.ants.Ant;
import model.tasks.Task;

/**
 * Interface for ant status effects, 
 * defining various conditions that can affect an ant's movement and behavior. 
 * <p>E.g. Poisoned, Carrying, Starving.
 */
public interface Status {
    /**
     * Apply the status effect to the ant each tick.
     */
    void applyStatusEffect(Ant ant);
    
    /**
     * Check if this status allows the ant to perform a given task.
     * Default: all tasks are allowed.
     * 
     * @param task : the task to check
     * @return true if the task is allowed, false otherwise
     */
    default boolean allowsTask(Task task) {
        return true;
    }
}
