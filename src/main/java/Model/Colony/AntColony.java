package Model.Colony;

import Model.Ants.Ant;

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

    public AntColony(ColonyMediator mediator, ColonyTaskBoard taskBoard){
        ants = new ArrayList<>();
        this.mediator = mediator;
        this.taskBoard = taskBoard;
    }

    public void addAnt(Ant ant) {
        this.ants.add(ant);
    }


}
