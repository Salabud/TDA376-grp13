package model.colony;

import model.ants.Ant;
import model.datastructures.Position;
import model.world.Item;
import model.world.MaterialType;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents an ant colony, serving as a container for its ants,
 * nest, and task board.
 */
public class AntColony {
    List<Ant> ants;
    ColonyMediator mediator;
    ColonyTaskBoard taskBoard;
    List<Item> knownFood;

    public AntColony(ColonyMediator mediator, ColonyTaskBoard taskBoard){
        ants = new ArrayList<>();
        knownFood = new ArrayList<>();
        this.mediator = mediator;
        this.taskBoard = taskBoard;
    }

    public void addAnt(Ant ant) {
        this.ants.add(ant);
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
