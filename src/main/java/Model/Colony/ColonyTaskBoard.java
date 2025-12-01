package Model.Colony;

import Model.Tasks.Task;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the task board of an ant colony, 
 * managing the priority list of tasks to be performed by the ants.
 */
public class ColonyTaskBoard {
    protected ArrayList<Task> taskBoard = new ArrayList<>();

    public int length() {
        return taskBoard.size();
    }

    public void addTask(Task task){
        taskBoard.add(task);
        taskBoard.sort(Comparator.comparingInt(Task::getPriority));
    }

    public void removeTask(Task task){
        taskBoard.remove(task);
        taskBoard.sort(Comparator.comparingInt(Task::getPriority));
    }

    public void markTaskAsAssigned(Task task){
        task.setAssigned(true);
    }

    public ArrayList<Task> getTaskBoard(){
        return taskBoard;
    }
}