package Model.Colony;

import Model.Ants.Ant;
import Model.Tasks.Task;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ColonyMediator {
    private ColonyTaskBoard taskBoard;

    private Task getBestTask(Ant ant){
        for(Task task : taskBoard.getTaskBoard()){
            if (ant.isAvailableForTask(task)){
                return task;
            }
        }
        // If no tasks are available
        return null;
    }

    public Task assignTask(Ant ant){
        // !! Can return null if no tasks are good/available!!

        Task bestTask = getBestTask(ant);
        if(bestTask != null){
            taskBoard.removeTask(bestTask);

        }
        return bestTask;
    }

    public void addTask(Task task){
        taskBoard.addTask(task);
    }

    public void setColonyTaskBoard(ColonyTaskBoard taskBoard){
        this.taskBoard = taskBoard;
    }
}
