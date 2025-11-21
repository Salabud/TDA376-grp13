package Model.Ants;

import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Entity;

public abstract class Ant extends Entity {
    int colonyId;
    String nickname;
    AntColony colony;
    ColonyMediator mediator;
    public void suggestTask(){
    }

}
