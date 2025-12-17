package model.colony.tasks;

import model.entity.being.ants.AntType;
import model.entity.being.ants.TaskPerformerAnt;
import model.datastructures.Position;

/**
 * Abstract base class for tasks performed by ants, following the command pattern.
 * E.g. ForageTask, ExpandNestTask, FeedQueenTask etc.
 * 
 * Provides common fields and behavior shared by all tasks:
 * <p>- Task phase tracking (internal state machine)
 * <p>- Assignment status
 * <p>- Completion checking
 */
public abstract class Task {
    
    /**
     * Internal task phases for progression logic.
     * Subclasses use these to manage their own state machine.
     *<p>Note: Enums are immutable so exposing them is safe.
     */
    public enum TaskPhase {
        NOT_STARTED,
        MOVING_TO_TARGET,
        WORKING,
        RETURNING,
        COMPLETE
    }
    
    protected TaskPhase phase;
    protected boolean isAssigned;
    protected int priority;
    
    protected Task() {
        this.phase = TaskPhase.NOT_STARTED;
        this.isAssigned = false;
        this.priority = 0;
    }
    
    /**
     * Execute one tick of this task's logic.
     * Subclasses implement their specific behavior here.
     * <p>Should be implemented as a switch.
     */
    public abstract void execute(TaskPerformerAnt ant);
    
    /**
     * Priority for task selection (higher = more urgent).
     */
    public abstract int getPriority();
    
    /**
     * The target location for this task (e.g., food position, nest entrance).
     */
    public abstract Position getTargetLocation();
    
    /**
     * Human-readable description of current task state.
     */
    public abstract String getDescription();
    
    // Common implementations for shared fields
    
    public boolean isComplete() {
        return phase == TaskPhase.COMPLETE;
    }
    
    public TaskPhase getPhase() {
        return phase;
    }
    
    protected void setPhase(TaskPhase phase) {
        this.phase = phase;
    }
    
    public boolean isAssigned() {
        return isAssigned;
    }
    
    public void setAssigned(boolean status) {
        this.isAssigned = status;
    }
    
    /**
     * Returns the required ant type for this task, or null if any ant can perform it.
     * Subclasses can override to restrict which ant types can do this task.
     * 
     * @return the required AntType, or null for no restriction
     */
    public AntType getRequiredAntType() {
        return null; // Default: any ant can do this task
    }
}
