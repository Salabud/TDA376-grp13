package model.colony;

import model.ants.TaskPerformerAnt;
import model.colony.antnest.Tunnel;
import model.datastructures.Position;
import model.Entity;
import model.tasks.EatTask;
import model.tasks.Task;
import model.world.Item;
import model.world.MaterialType;

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
    public void getBestTask(TaskPerformerAnt ant){
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

    /**
     * Sets the colony's task board.
     * @param taskBoard : The task board to set.
     */
    public void setColonyTaskBoard(ColonyTaskBoard taskBoard){
        this.taskBoard = taskBoard;
    }

    public void setAntColony(AntColony antColony){
        this.antColony = antColony;
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

    public void reportHungry(TaskPerformerAnt ant){
        Position foodPosition = getBestFoodPosition(ant.getPosition());
        if(foodPosition == null){
            return;
        }
        
        Item foodItem = findFoodItemAt(ant, foodPosition);
        if(foodItem == null){
            // Food was already eaten or removed
            antColony.deleteFoodPosition(foodPosition);
            return;
        }
        
        ant.assignTask(new EatTask(foodItem));
        antColony.deleteFoodPosition(foodPosition);
    }
    
    /***
     * TODO: Implement a better way of picking a food
     * @return
     */
    private Position getBestFoodPosition(Position position){
        List<Position> foodPositions = antColony.getFoodPositions();
        if(!foodPositions.isEmpty()){
            return foodPositions.getFirst(); // TODO: dependant on ant's position
        }
        return null;
    }

    /**
     * Find a food Item at the given position.
     */
    private Item findFoodItemAt(TaskPerformerAnt ant, Position position) {
        List<Entity>[][] entityGrid = ant.getWorld().getEntityGrid();
        int x = position.getX();
        int y = position.getY();
        
        for (Entity entity : entityGrid[x][y]) {
            if (entity instanceof Item item && item.getMaterialType() == MaterialType.FOOD) {
                return item;
            }
        }
        return null;
    }
}