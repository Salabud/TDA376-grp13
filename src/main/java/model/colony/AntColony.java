package model.colony;

import model.AntType;
import model.ants.Ant;
import model.ants.QueenAnt;
import model.datastructures.Position;
import model.world.Item;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ant colony, serving as a container for its ants,
 * nest, and task board.
 */
public class AntColony {
    private List<Ant> ants;
    private ColonyMediator mediator;
    private ColonyTaskBoard taskBoard;
    private List<Item> knownFood;
    private QueenAnt queen;
    
    // Birth control
    private int ticksSinceLastBirth = 0;
    private static final int BIRTH_COOLDOWN = 100;
    private static final int MIN_FOOD_FOR_BIRTH = 2;
    private static final int BASE_LARVA_COUNT = 1;
    private static final int FOOD_PER_EXTRA_LARVA = 3;
    private static final int MAX_LARVA_COUNT = 100;

    public AntColony(ColonyMediator mediator, ColonyTaskBoard taskBoard){
        ants = new ArrayList<>();
        knownFood = new ArrayList<>();
        this.mediator = mediator;
        this.taskBoard = taskBoard;
    }

    /**
     * Update the colony state each tick.
     * Checks if conditions are met to request a new birth.
     */
    public void update() {
        ticksSinceLastBirth++;

        if (shouldRequestBirth()) {
            mediator.requestBirth(queen);
            ticksSinceLastBirth = 0;
        }
    }
    
    /**
     * Check if the colony should request the queen to give birth.
     */
    private boolean shouldRequestBirth() {
        if (queen == null) {
            return false;
        }
        
        if (ticksSinceLastBirth < BIRTH_COOLDOWN) {
            return false;
        }
        
        if (knownFood.size() < MIN_FOOD_FOR_BIRTH) {
            return false;
        }

        long larvaCount = ants.stream()
                .filter(ant -> ant.getAntType() == AntType.LARVA)
                .count();
        return larvaCount < getTargetLarvaCount();
    }
    
    /**
     * Calculate target larva count based on food availability.
     * More food = colony can sustain more larvae.
     * @return the target number of larvae
     * TODO: Add more prerequisites for birth
     */
    private int getTargetLarvaCount() {
        int foodCount = knownFood.size();
        int target = BASE_LARVA_COUNT + (foodCount / FOOD_PER_EXTRA_LARVA);
        return Math.min(target, MAX_LARVA_COUNT);
    }

    public void addAnt(Ant ant) {
        this.ants.add(ant);
        
        // Track the queen
        if (ant instanceof QueenAnt queen) {
            this.queen = queen;
        }
    }
    
    public QueenAnt getQueen() {
        return queen;
    }
    
    public List<Ant> getAnts() {
        return ants;
    }

    public List<Position> getFoodPositions(){
        List<Position> positions = new ArrayList<>();
        for (Item item : knownFood) {
            positions.add(item.getPosition());
        }
        return positions;
    }
    
    public List<Item> getKnownFood(){
        return this.knownFood;
    }
    
    public void addKnownFood(Item food){
        this.knownFood.add(food);
    }
    
    public void deleteKnownFood(Item food){
        this.knownFood.removeIf(item -> item.equals(food));
    }

    //TODO
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("knownFood", getKnownFood());
        return obj;
    }
}
