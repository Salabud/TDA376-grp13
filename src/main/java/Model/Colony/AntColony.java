package Model.Colony;

import Model.Ants.Ant;

import java.util.ArrayList;
import java.util.List;

public class AntColony {
    List<Ant> ants;
    ColonyMediator mediator;

    public AntColony(ColonyMediator mediator){
        ants = new ArrayList<>();
        this.mediator = mediator;
    }
}
