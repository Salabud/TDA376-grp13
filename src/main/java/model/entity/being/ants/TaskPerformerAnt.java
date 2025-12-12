package model.entity.being.ants;

import model.entity.Carryable;
import model.entity.being.ants.state.AntState;
import model.entity.being.ants.status.Status;
import model.colony.events.BecameIdleEvent;
import model.colony.events.TaskCompletedEvent;
import model.datastructures.Position;
import model.colony.tasks.Task;
import org.json.JSONObject;

/** Abstract class for ants that can perform tasks. E.g. WorkerAnt, QueenAnt, (TODO: SoldierAnt)*/
public abstract class TaskPerformerAnt extends Ant{
    protected Task currentTask;
    protected Task previousTask; // Task to resume after interruption
    private Carryable carriedObject = null;

    /**
     * Checks if the ant is available to take on a new task.
     * Checks: idle state, ant type requirements, and status effects.
     * Subclasses can override isTaskTypeAllowed() for type-specific restrictions.
     * 
     * @param task : The task to check, or null to check if idle.
     * @return True if the ant is available for the task, false otherwise.
     */
    public boolean isAvailableForTask(Task task) {
        // Check if ant is idle (no current task or task is complete)
        if (currentTask != null && !currentTask.isComplete()) {
            return false;
        }
        
        // If task is null, just checking if idle
        if (task == null) {
            return true;
        }
        
        // Check if task requires a specific ant type
        AntType requiredType = task.getRequiredAntType();
        if (requiredType != null && requiredType != this.antType) {
            return false;
        }
        
        // Check if any status effect prevents this task
        for (Status status : statuses) {
            if (!status.allowsTask(task)) {
                return false;
            }
        }
        
        // Subclass-specific checks (e.g., workers can't birth)
        return isTaskTypeAllowed(task);
    }
    
    /**
     * Subclass-specific task type restrictions.
     * Override in subclasses to add instanceof checks for disallowed tasks.
     * 
     * @param task the task to check
     * @return true if this ant type can perform this task type
     */
    protected boolean isTaskTypeAllowed(Task task) {
        return true; // Default: all tasks allowed
    }

    public void assignTask(Task task) {
        this.currentTask = task;
    }
    
    /**
     * Interrupt the current task with an urgent task (e.g., eating when hungry).
     * The current task is saved and will be resumed after the interrupting task completes.
     * @param urgentTask : the task that needs immediate attention
     */
    public void interruptWithTask(Task urgentTask) {
        if (currentTask != null && !currentTask.isComplete()) {
            previousTask = currentTask;
        }
        currentTask = urgentTask;
    }

    @Override
    public void update() {
        // Execute current task
        if (currentTask != null) {
            currentTask.execute(this);
            
            // Check if task just completed
            if (currentTask.isComplete()) {
                broadcastEvent(new TaskCompletedEvent(currentTask, this));
                currentTask = null;
                
                // Resume previous task if one was interrupted
                if (previousTask != null && !previousTask.isComplete()) {
                    currentTask = previousTask;
                    previousTask = null;
                }
            }
        }
        
        // If idle (no task), broadcast idle event to get a new task
        if (currentTask == null) {
            setState(AntState.IDLE);
            broadcastEvent(new BecameIdleEvent(this));
        }

        // If carrying something, place it at current position before moving
        // After super.update() moves the ant, the item will be one step behind
        // TODO: Check for Carrying status!
        if (carriedObject != null) {
            carriedObject.moveTo(new Position(getX(), getY()));
        }
        
        super.update();  // Handles super stuff: movement, behavior, hunger and age ticking
    }

    public void attemptCarry(Carryable carryable) {
        if (this.position.isAdjacentTo(carryable.getPosition())){
            this.carriedObject = carryable;
        }
    }

    public Carryable getCarriedObject() {
        return this.carriedObject;
    }
    
    /**
     * Check if this ant is currently carrying something.
     */
    public boolean isCarrying() {
        return carriedObject != null;
    }
    
    /**
     * Drop the carried object at the ant's current position.
     */
    public void dropCarriedObject() {
        if (carriedObject != null) {
            carriedObject.moveTo(new Position(getX(), getY()));
            carriedObject = null;
        }
    }

    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        return obj;
    }
}
