package Model.Colony;

import Model.Ants.Ant;
import Model.Ants.TaskPerformerAnt;
import Model.Colony.AntNest.Tunnel;
import Model.Datastructures.Position;
import Model.Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mediator class for managing communication and task assignment within an ant colony between ants,
 * following the mediator pattern. Think of it as a hivemind that helps coordinate the activities of individual ants.
 *
 * <p>Ants can report discoveries to the mediator, which then creates appropriate tasks.
 */
public class ColonyMediator {
    private ColonyTaskBoard taskBoard;

    /**
     * Suggests the most appropriate available task for the given ant.
     * If the ant is suitable for the task, it gets assigned the task.
     * @param ant : The ant requesting a task.
     */
    public void getBestTask(TaskPerformerAnt ant){
        for(Task task : taskBoard.getTaskBoard()){ //TODO: iterate through non-assigned tasks only
            if (ant.isAvailableForTask(task)){
                assignTask(ant, task);
                break;
            }
        }
    }

    /**
     * Assigns the best available task to the given ant.
     * @param ant : The ant to assign the task to.
     * @param task : The task to be assigned.
     */
    private void assignTask(TaskPerformerAnt ant, Task task){
        ant.assignTask(task);
        taskBoard.removeTask(task); // TODO: flag task as assigned instead of removing
    }

    /**
     * Adds a new task to the colony's task board.
     * @param task : The task to be added.
     */
    public void addTask(Task task){
        taskBoard.addTask(task);
    }

    /**
     * Sets the colony's task board.
     * @param taskBoard : The task board to set.
     */
    public void setColonyTaskBoard(ColonyTaskBoard taskBoard){
        this.taskBoard = taskBoard;
    }

    // Discovery Reporting ===========================================
    
    /**
     * Report that food was discovered at a location.
     * Creates a forage task for other ants to collect it.
     * @param location : The position where food was found
     * @param quantity : The amount of food discovered
     */
    public void reportFoodDiscovery(Position location, int quantity) {
        // TODO: Create and add ForageFoodTask
        // taskBoard.addTask(new ForageFoodTask(location, quantity));
    }
    
    /**
     * Report that a tunnel is blocked and needs clearing.
     * @param tunnel : The blocked tunnel
     */
    public void reportBlockedTunnel(Tunnel tunnel) {
        // TODO: Create and add ClearTunnelTask
        // taskBoard.addTask(new ClearTunnelTask(tunnel));
    }
    
    /**
     * Report that a new tunnel needs to be dug.
     * @param tunnel : The tunnel that needs digging
     */
    public void reportTunnelNeedsDigging(Tunnel tunnel) {
        // TODO: Create and add DigTunnelTask
        // taskBoard.addTask(new DigTunnelTask(tunnel));
    }
}