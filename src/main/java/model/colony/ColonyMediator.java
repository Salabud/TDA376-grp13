package model.colony;

import model.ants.Larva;
import model.ants.QueenAnt;
import model.ants.TaskPerformerAnt;
import model.colony.antnest.Tunnel;
import model.datastructures.Position;
import model.tasks.BirthTask;
import model.tasks.EatTask;
import model.tasks.FeedBeingTask;
import model.tasks.Task;
import model.world.Item;

import java.util.List;

/**
 * Mediator class for managing communication and task assignment within an ant colony between ants,
 * following the mediator pattern. Think of it as a hivemind that helps coordinate the activities of individual ants.
 *
 * <p>Ants can report discoveries to the mediator, which then creates appropriate tasks.
 */
public class ColonyMediator {
    private ColonyTaskBoard taskBoard;
    private AntColony antColony;

    /**
     * Suggests the most appropriate available task for the given ant.
     * If the ant is suitable for the task, it gets assigned the task.
     * @param ant : The ant requesting a task.
     */
    public void suggestBestTask(TaskPerformerAnt ant){
        for(Task task : taskBoard.getTaskBoard()){
            // Only consider unassigned tasks
            if (!task.isAssigned() && ant.isAvailableForTask(task)){
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
        task.setAssigned(true);
    }
    
    /**
     * Called when a task is completed. Removes the task from the taskboard.
     * @param task : The completed task.
     */
    public void reportTaskCompleted(Task task){
        taskBoard.removeTask(task);
    }

    /**
     * Adds a new task to the colony's task board.
     * @param task : The task to be added.
     */
    public void addTask(Task task){
        taskBoard.addTask(task);
    }

    public void setColonyTaskBoard(ColonyTaskBoard taskBoard){
        this.taskBoard = taskBoard;
    }
    public void setAntColony(AntColony antColony){
        this.antColony = antColony;
    }

    // Discovery & Reporting ===========================================
    
    /**
     * Report that food was discovered by a scouting ant.
     * Adds the food to the colony's known food list if not already known.
     * @param food : The food item discovered
     */
    public void reportFoodDiscovered(Item food) {
        List<Item> knownFood = antColony.getKnownFood();
        if (!knownFood.contains(food)) {
            antColony.addKnownFood(food);
        }
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

    public void reportHungry(TaskPerformerAnt ant){
        Item food = findBestFood(ant.getPosition());
        if (food == null) {
            return;
        }
        
        ant.interruptWithTask(new EatTask(food));
        antColony.deleteKnownFood(food);
    }

    /**
     * Find the best available food item, preferring closer food.
     * @param position the position to find food near
     * @return the best food Item, or null if none available
     */
    private Item findBestFood(Position position) {
        List<Item> knownFood = antColony.getKnownFood();
        if (knownFood.isEmpty()) {
            return null;
        }
        // TODO: Sort by distance to position
        return knownFood.getFirst();
    }

    /**
     * Report that a larva is hungry and needs to be fed.
     * Creates a FeedBeingTask and adds it to the task board.
     * Does not create duplicate tasks for the same larva.
     * @param larva : The hungry larva
     * @return true if a task was created, false otherwise
     */
    public boolean reportLarvaHungry(Larva larva) {
        for (Task task : taskBoard.getTaskBoard()) {
            if (task instanceof FeedBeingTask feedTask && feedTask.getTarget() == larva) {
                return true; // Task already exists
            }
        }
        
        Item food = findBestFood(larva.getPosition());
        if (food == null) {
            return false; // No food available
        }

        taskBoard.addTask(new FeedBeingTask(larva, food, 2, "larva"));
        antColony.deleteKnownFood(food);
        return true;
    }

    /**
     * Report that the queen is hungry and needs to be fed.
     * Creates a FeedBeingTask and adds it to the task board.
     * Does not create duplicate tasks for the queen.
     * @param queen : The hungry queen
     * @return true if a task was created, false otherwise
     */
    public boolean reportQueenHungry(QueenAnt queen) {
        for (Task task : taskBoard.getTaskBoard()) {
            if (task instanceof FeedBeingTask feedTask && feedTask.getTarget() == queen) {
                return true; // Task already exists
            }
        }
        
        Item food = findBestFood(queen.getPosition());
        if (food == null) {
            return false; // No food available
        }
        
        taskBoard.addTask(new FeedBeingTask(queen, food, 1, "queen"));
        antColony.deleteKnownFood(food);
        return true;
    }

    /**
     * Request the queen to give birth to a new larva.
     * Creates a BirthTask if no birth task is already pending.
     * @param queen : The queen to give birth
     */
    public void requestBirth(QueenAnt queen) {
        // Check if a BirthTask already exists
        for (Task task : taskBoard.getTaskBoard()) {
            if (task instanceof BirthTask) {
                return;
            }
        }
        
        BirthTask birthTask = new BirthTask(antColony, this);
        if (!queen.isAvailableForTask(birthTask)) {
            return;
        }

        taskBoard.addTask(birthTask);
        queen.assignTask(birthTask);
        birthTask.setAssigned(true);
    }
}