package model.ants;

import model.EntityIdManager;
import model.ants.status.Status;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;

import model.world.World;

import java.util.List;

/**
 * Singleton Factory class for creating various types of Ants using the Builder pattern.
 * This class provides methods to create (and load saved) Ant subclasses,
 * encapsulating the instantiation logic and ensuring proper initialization.
 */
public final class AntFactory {
    private static AntFactory INSTANCE;

    private AntFactory() {
    }

    /**
     * Gets the singleton instance of the AntFactory.
     * @return : The singleton instance of the AntFactory.
     */
    public static AntFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AntFactory();
        }
        return INSTANCE;
    }

    /**
     * Creates a new WorkerAnt instance and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param mediator : The colony mediator for the ant.
     * @return : The newly created WorkerAnt instance.
     */
    public WorkerAnt createWorkerAnt(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        WorkerAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.WORKER_ANT)
                .mediator(mediator)
                .maxHunger(40)
                .buildWorkerAnt();
        ant.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(ant);
        colony.addAnt(ant);

        return ant; //Vi kanske inte ens behöver returnera instans?
    }

    /**
     * Loads a saved WorkerAnt instance with the provided attributes and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param age : The age of the ant.
     * @param nickname : The nickname of the ant.
     * @param mediator : The colony mediator for the ant.
     * @param health : The current health of the ant.
     * @param maxHealth : The maximum health of the ant.
     * @param hunger : The current hunger level of the ant.
     * @param maxHunger : The maximum hunger level of the ant.
     * @param movementInterval : The movement interval of the ant.
     * @param statuses : The statuses of the ant.
     * @return : The newly created WorkerAnt instance.
     */
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
        ant.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }

    /**
     * Creates a new Larva instance and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param mediator : The colony mediator for the ant.
     * @return : The newly created Larva instance.
     */
    public Larva createLarva(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        Larva larva = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.LARVA)
                .mediator(mediator)
                .buildLarva();
        larva.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(larva);
        colony.addAnt(larva);
        return larva;
    }

    /**
     * Loads a saved Larva instance with the provided attributes and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param age : The age of the ant.
     * @param nickname : The nickname of the ant.
     * @param mediator : The colony mediator for the ant.
     * @param health : The current health of the ant.
     * @param maxHealth : The maximum health of the ant.
     * @param hunger : The current hunger level of the ant.
     * @param maxHunger : The maximum hunger level of the ant.
     * @param movementInterval : The movement interval of the ant.
     * @param statuses : The statuses of the ant.
     * @return : The newly created Larva instance.
     */
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
        larva.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(larva);
        colony.addAnt(larva);
        return larva;
    }

    /**
     * Creates a new QueenAnt instance and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param mediator : The colony mediator for the ant.
     * @return : The newly created QueenAnt instance.
     */
    public QueenAnt createQueenAnt(World world, AntColony colony, int colonyId, int x, int y, ColonyMediator mediator) {
        QueenAnt ant = new AntBuilder()
                .world(world)
                .colony(colony)
                .colonyId(colonyId)
                .position(new Position(x, y))
                .type(EntityType.QUEEN)
                .mediator(mediator)
                .buildQueenAnt();
        ant.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }

    /**
     * Loads a saved QueenAnt instance with the provided attributes and adds it to the world and colony.
     * @param world : The world the ant belongs to.
     * @param colony : The colony the ant belongs to.
     * @param colonyId : The Ant's ID within the colony.
     * @param x : The x-coordinate of the ant's position.
     * @param y : The y-coordinate of the ant's position.
     * @param age : The age of the ant.
     * @param nickname : The nickname of the ant.
     * @param mediator : The colony mediator for the ant.
     * @param health : The current health of the ant.
     * @param maxHealth : The maximum health of the ant.
     * @param hunger : The current hunger level of the ant.
     * @param maxHunger : The maximum hunger level of the ant.
     * @param movementInterval : The movement interval of the ant.
     * @param statuses : The statuses of the ant.
     * @return : The newly created QueenAnt instance.
     */
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
        ant.setEntityId(EntityIdManager.getInstance().getNextId());
        world.addEntity(ant);
        colony.addAnt(ant);
        return ant;
    }
}

