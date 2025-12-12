package model.colony.events;

import model.entity.being.ants.Ant;
import model.colony.tasks.Task;

/**
 * Event fired when an ant completes a task.
 */
public class TaskCompletedEvent implements ColonyEvent {
    private final Task task;
    private final Ant source;

    public TaskCompletedEvent(Task task, Ant source) {
        this.task = task;
        this.source = source;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public Ant getSource() {
        return source;
    }
}
