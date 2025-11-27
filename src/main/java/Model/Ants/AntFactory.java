package Model.Ants;

import Model.Ants.Status.Status;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Datastructures.Position;
import Model.EntityType;

import Model.World.World;

import java.util.List;

public final class AntFactory {
    private static AntFactory INSTANCE;

    private AntFactory() {
    }

    public static AntFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AntFactory();
        }
        return INSTANCE;
    }

    public WorkerAnt createWorkerAnt(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        WorkerAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.WORKER_ANT)
                .mediator(mediator)
                .buildWorkerAnt();
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant; //Vi kanske inte ens behöver returnera instans?
    }
    public WorkerAnt loadWorkerAnt(World world, AntColony colony, int colonyId, int x, int y, int age,
                                      String nickname, ColonyMediator mediator, float health, float maxHealth,
                                      float hunger, float maxHunger, int movementInterval, List<Status> statuses) {
        WorkerAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.WORKER_ANT)
                .mediator(mediator)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildWorkerAnt();
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }

    public Larva createLarva(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        Larva larva = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.LARVA)
                .mediator(mediator)
                .buildLarva();
        world.addEntity(larva);
        colony.addAnt(larva);
        return larva;
    }
    public Larva loadLarva(World world, AntColony colony, int colonyId, int x, int y, int age,
                              String nickname, ColonyMediator mediator, float health, float maxHealth,
                              float hunger, float maxHunger, int movementInterval, List<Status> statuses) {
        Larva larva = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.LARVA)
                .mediator(mediator)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildLarva();
        world.addEntity(larva);
        colony.addAnt(larva);
        return larva;
    }

    public QueenAnt createQueenAnt(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        QueenAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.QUEEN)
                .mediator(mediator)
                .buildQueenAnt();
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }
    public QueenAnt loadQueenAnt(World world, AntColony colony, int colonyId, int x, int y, int age,
                                    String nickname, ColonyMediator mediator, float health, float maxHealth,
                                    float hunger, float maxHunger, int movementInterval, List<Status> statuses) {
        QueenAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.QUEEN)
                .mediator(mediator)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildQueenAnt();
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }
}

