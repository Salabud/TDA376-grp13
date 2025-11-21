package Model.Ants;

import Model.Ants.Behavior.Status;
import Model.Carryable;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.World.World;

import java.util.List;

public class Larva extends Ant implements Carryable {

    public Larva(World world, int colonyId, int x, int y, ColonyMediator mediator){

    }
    public Larva(World world, int colonyId, int x, int y, int age,
                 String nickname, ColonyMediator mediator, float health, float maxHealth, float hunger,
                 float maxHunger, int movementInterval, List<Status> statuses){

    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void update() {

    }
    public void becomeWorker(){

    }
}
