package model.colony;

import model.entity.being.ants.AntType;
import model.entity.being.ants.Ant;
import model.entity.being.ants.QueenAnt;
import model.datastructures.Position;
import model.entity.item.Item;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ant colony, serving as a data container for its ants,
 * nest, and known resources. Does not contain business logic, 
 * coordination is handled by ColonyMediator.
 */
public class AntColony {
    private List<Ant> ants;
    private ColonyTaskBoard taskBoard; //TODO: We don't need this here
    private List<Item> knownFood;
    private QueenAnt queen;

    public AntColony(HiveMind mediator, ColonyTaskBoard taskBoard){
        ants = new ArrayList<>();
        knownFood = new ArrayList<>();
        this.taskBoard = taskBoard;
    }

    public void addAnt(Ant ant) {
        this.ants.add(ant);
        
        // Track the queen
        if (ant instanceof QueenAnt queenAnt) {
            this.queen = queenAnt;
        }
    }
    
    public void removeAnt(Ant ant) {
        this.ants.remove(ant);
        
        // Clear queen reference if removing the queen
        if (ant == queen) {
            this.queen = null;
        }
    }
    
    public QueenAnt getQueen() {
        return queen;
    }
    
    public List<Ant> getAnts() {
        return ants;
    }
    
    /**
     * Get the current count of larvae in the colony.
     * @return the number of larvae
     */
    public long getLarvaCount() {
        return ants.stream()
                .filter(ant -> ant.getAntType() == AntType.LARVA)
                .count();
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
