package Model.Ants;

import Model.Ants.Behavior.Status;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.World.World;

import java.util.List;

public final class AntFactory {
    private static AntFactory INSTANCE;

    private AntFactory(){
    }

    public static AntFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AntFactory();
        }
        return INSTANCE;
    }

    public void createWorkerAnt(World world, AntColony colony, int x, int y, int age){

    }


    public void createLarva(World world, int colonyId, int x, int y, ColonyMediator mediator){
        Larva larva = new Larva(world, colonyId, x, y, mediator);
        world.addEntity(larva);
    }
    public void createLarva(World world, int colonyId, int x, int y, int age,
                            String nickname, ColonyMediator mediator, float health, float maxHealth, float hunger,
                            float maxHunger, int movementInterval, List<Status> statuses){
        Larva larva = new Larva(world, colonyId, x, y, age, nickname, mediator, health,
                                maxHealth, hunger, maxHunger, movementInterval, statuses);
        world.addEntity(larva);
    }

    public void createQueenAnt(World world, AntColony colony, int x, int y, int age){

    }

    // x, y, health, maxHealth, hunger, maxHunger, age, movementInterval, statuses
    // colonyId, nickname, mediator
    //

}

