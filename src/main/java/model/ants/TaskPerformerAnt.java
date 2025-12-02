package model.ants;

import model.tasks.EatTask;
import model.tasks.Task;
import org.json.JSONObject;

/** Abstract class for ants that can perform tasks. E.g. WorkerAnt, QueenAnt, (TODO: SoldierAnt)*/
public class TaskPerformerAnt extends Ant{
    protected Task currentTask;

    /**
     * Checks if the ant is available to take on a new task.
     * @param task : The task to check.
     * @return True if the ant is available for the task, false otherwise.
     */
    public boolean isAvailableForTask(Task task) {
        return true; // Simplified for now, TODO: implement logic based on ant state, status, etc.
    }

    
    public void assignTask(Task task) {
        this.currentTask = task;
    }

    @Override
    public void update() {
        //System.out.println("taskPerformerAnt update");
        if(getHunger() < 30 && !(currentTask instanceof EatTask)){
            mediator.reportHungry(this);
        }
        if (currentTask != null) {
            currentTask.execute(this);
        }

        //System.out.println("ant tick");
        super.update();
    }

    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        return obj;
    }
}
