package model.colony;

import model.entity.being.Being;
import model.entity.being.ants.Larva;
import model.entity.being.ants.QueenAnt;
import model.entity.being.ants.TaskPerformerAnt;
import model.colony.antnest.Tunnel;
import model.colony.events.*;
import model.datastructures.Position;
import model.colony.tasks.BirthTask;
import model.colony.tasks.EatTask;
import model.colony.tasks.FeedBeingTask;
import model.colony.tasks.Task;
import model.entity.item.Item;

import java.util.List;

/**
 * Mediator class for managing communication and task assignment within an ant colony between ants,
 * following the observer and mediator pattern. Think of it as a hivemind that helps coordinate the activities of individual ants.
 *
 * <p>Ants broadcast events which this mediator listens to and handles appropriately.
 * <p>Also handles colony-wide decisions like birth control based on colony state.
 */
public class HiveMind implements ColonyEventListener {
    private ColonyTaskBoard taskBoard;
    private AntColony antColony;
    @SuppressWarnings("unused") // May be needed for future tunnel/nest functionality
    
    // Birth control
    private int ticksSinceLastBirth = 0;
    private static final int BIRTH_COOLDOWN = 100;
    private static final int MIN_FOOD_FOR_BIRTH = 2;
    private static final int BASE_LARVA_COUNT = 1;
    private static final int FOOD_PER_EXTRA_LARVA = 3;
    private static final int MAX_LARVA_COUNT = 100;
    
    @Override
    public void onColonyEvent(ColonyEvent event) {
        switch (event) {
            case HungryEvent hungryEvent -> handleHungryEvent(hungryEvent);
            case FoodDiscoveredEvent foodEvent -> handleFoodDiscovered(foodEvent);
            case TaskCompletedEvent taskEvent -> handleTaskCompleted(taskEvent);
            case BecameIdleEvent idleEvent -> handleBecameIdle(idleEvent);
            case BirthRequestEvent birthEvent -> handleBirthRequest(birthEvent);
            // OBS: LarvaTransformEvent and LarvaBirthEvent are handled by AntSpawner!
            default -> { }
        }
    }
    
    private void handleHungryEvent(HungryEvent event) {
        Being hungryBeing = event.getHungryBeing();
        
        if (hungryBeing instanceof Larva larva) {
            handleLarvaHungry(larva);
        } else if (hungryBeing instanceof QueenAnt queen) {
            handleQueenHungry(queen);
        } else if (hungryBeing instanceof TaskPerformerAnt ant) {
            handleAntHungry(ant);
        }
    }
    
    private void handleFoodDiscovered(FoodDiscoveredEvent event) {
        Item food = event.getFood();
        List<Item> knownFood = antColony.getKnownFood();
        if (!knownFood.contains(food)) {
            antColony.addKnownFood(food);
        }
    }
    
    private void handleTaskCompleted(TaskCompletedEvent event) {
        taskBoard.removeTask(event.getTask());
    }
    
    private void handleBecameIdle(BecameIdleEvent event) {
        if (event.getSource() instanceof TaskPerformerAnt ant) {
            suggestBestTask(ant);
        }
    }
    
    private void handleBirthRequest(BirthRequestEvent event) {
        requestBirth(event.getQueen());
    }

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

    // Birth control stuff

    /**
     * Update method called each tick to check colony-wide conditions.
     * Currently handles birth control decisions.
     * //TODO: Also handle AntNest (chamber, tunnels) stuff
     */
    public void update() {
        ticksSinceLastBirth++;
        checkBirthNeeded();
    }
    
    /**
     * Check if the colony needs a new birth and request it if conditions are met.
     */
    private void checkBirthNeeded() {
        QueenAnt queen = antColony.getQueen();
        
        if (queen == null) {
            return;
        }
        
        if (ticksSinceLastBirth < BIRTH_COOLDOWN) {
            return;
        }
        
        if (antColony.getKnownFood().size() < MIN_FOOD_FOR_BIRTH) {
            return;
        }
        
        long larvaCount = antColony.getLarvaCount();
        if (larvaCount < getTargetLarvaCount()) {
            requestBirth(queen);
            System.out.println("HiveMind: requested birth");
            ticksSinceLastBirth = 0;
        }
    }
    
    /**
     * Calculate target larva count based on food availability.
     * More food = colony can sustain more larvae.
     * @return the target number of larvae
     */
    private int getTargetLarvaCount() {
        int foodCount = antColony.getKnownFood().size();
        int target = BASE_LARVA_COUNT + (foodCount / FOOD_PER_EXTRA_LARVA);
        return Math.min(target, MAX_LARVA_COUNT);
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
        
        BirthTask birthTask = new BirthTask();
        if (!queen.isAvailableForTask(birthTask)) {
            return;
        }

        taskBoard.addTask(birthTask);
        queen.assignTask(birthTask);
        birthTask.setAssigned(true);
    }

    /**
     * Handle a TaskPerformerAnt being hungry.
     * Finds food and interrupts with an EatTask.
     */
    private void handleAntHungry(TaskPerformerAnt ant) {
        Item food = findBestFood(ant.getPosition());
        if (food == null) {
            return;
        }
        
        ant.interruptWithTask(new EatTask(food));
        antColony.deleteKnownFood(food);
    }

    /**
     * Find the best available food item, preferring closer food.
     * @param position : the position to find food near
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
     * Handle a larva being hungry.
     * Creates a FeedBeingTask and adds it to the task board.
     * Does not create duplicate tasks for the same larva.
     */
    private void handleLarvaHungry(Larva larva) {
        for (Task task : taskBoard.getTaskBoard()) {
            if (task instanceof FeedBeingTask feedTask && feedTask.getTarget() == larva) {
                return; // Task already exists
            }
        }
        
        Item food = findBestFood(larva.getPosition());
        if (food == null) {
            return;
        }

        taskBoard.addTask(new FeedBeingTask(larva, food, 2, "larva"));
        antColony.deleteKnownFood(food);
    }

    /**
     * Handle the queen being hungry.
     * Creates a FeedBeingTask and adds it to the task board.
     * Does not create duplicate tasks for the queen.
     */
    private void handleQueenHungry(QueenAnt queen) {
        for (Task task : taskBoard.getTaskBoard()) {
            if (task instanceof FeedBeingTask feedTask && feedTask.getTarget() == queen) {
                return; // Task already exists
            }
        }
        
        Item food = findBestFood(queen.getPosition());
        if (food == null) {
            return; // No food available
        }
        
        taskBoard.addTask(new FeedBeingTask(queen, food, 1, "queen"));
        antColony.deleteKnownFood(food);
    }

    // TODO: Make these into events and handle these events properly
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