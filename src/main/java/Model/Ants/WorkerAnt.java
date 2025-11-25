package Model.Ants;

import java.util.List;

import Model.TaskPerformer;
import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Behavior.AntState;
import Model.Ants.Behavior.Status;
import Model.Tasks.Task;

public class WorkerAnt extends Ant implements TaskPerformer {
    private Task currentTask;
    private List<Status> statuses;
    private AntBehavior behavior;
    private AntState antState;

    public boolean isAvailableForTask() {

    }

    public void assignTask(Task task) {
        this.currentTask = task;
    }

    @Override
    public void update() {
        if (currentTask != null) {
            currentTask.execute(this);
        }
    }
}
