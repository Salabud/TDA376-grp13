package model.ants;

import model.AntType;
import model.Carryable;
import model.ants.state.AntState;
import model.ants.status.Status;
import model.datastructures.Position;
import model.tasks.Task;
import org.json.JSONObject;

/** Abstract class for ants that can perform tasks. E.g. WorkerAnt, QueenAnt, (TODO: SoldierAnt)*/
public class TaskPerformerAnt extends Ant{
    protected Task currentTask;
    // "Inventory" that can hold 1 object.
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

    @Override
    public void update() {
        // Execute current task
        if (currentTask != null) {
            currentTask.execute(this);
            
            // Check if task just completed
            if (currentTask.isComplete()) {
                mediator.reportTaskCompleted(currentTask);
                currentTask = null;
            }
        }
        
        // If idle (no task), request a new task from mediator
        if (currentTask == null) {
            setState(AntState.IDLE);
            mediator.suggestBestTask(this);
        }

        // If carrying something, place it at current position before moving
        // After super.update() moves the ant, the item will be one step behind
        // TODO: Check for Carrying status!
        if (carriedObject != null) {
            carriedObject.moveTo(new Position(getX(), getY()));
        }
        
        super.update();  // Handles movement, behavior, and hunger tick
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
