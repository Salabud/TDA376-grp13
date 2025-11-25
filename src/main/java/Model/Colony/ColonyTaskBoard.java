package Model.Colony;

import Model.Tasks.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class ColonyTaskBoard {
    protected ArrayList<Task> taskBoard = new ArrayList<>();

    public int length() {
        return taskBoard.size();
    }


    public void addTask(Task task){
        taskBoard.add(task);
        taskBoard.sort(Comparator.comparingInt(Task::getImportance));
    }

    public void removeTask(Task task){
        taskBoard.remove(task);
        taskBoard.sort(Comparator.comparingInt(Task::getImportance));
    }

    public ArrayList<Task> getTaskBoard(){
        return taskBoard;
    }
}