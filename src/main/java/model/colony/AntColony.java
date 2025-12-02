package model.colony;

import model.ants.Ant;
import model.datastructures.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ant colony, serving as a container for its ants,
 * nest, and task board.
 */
public class AntColony {
    List<Ant> ants;
    ColonyMediator mediator;
    ColonyTaskBoard taskBoard;
    List<Position> knownFoodPositions;

    public AntColony(ColonyMediator mediator, ColonyTaskBoard taskBoard){
        ants = new ArrayList<>();
        knownFoodPositions = new ArrayList<>();
        this.mediator = mediator;
        this.taskBoard = taskBoard;
    }

    public void addAnt(Ant ant) {
        this.ants.add(ant);
    }

    public List<Position> getFoodPositions(){
        return this.knownFoodPositions;
    }

    public void addFoodPosition(Position position){
        this.knownFoodPositions.add(position);
    }
    public void deleteFoodPosition(Position position){
        this.knownFoodPositions.remove(position);
    }
}
