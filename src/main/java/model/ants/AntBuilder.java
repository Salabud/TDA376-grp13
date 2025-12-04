package model.ants;

import model.ants.movement.NoMovement;
import model.ants.status.Status;
import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.state.AntState;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.world.World;
import java.util.List;

/** 
 * Builder class for creating Ant objects with various configurations.
 * This class follows the Builder design pattern to provide a flexible and readable way
 * to construct Ant instances with different attributes.
 */
public class AntBuilder {
    // Being fields
    private World world;
    private Position position;
    private float health;
    private float maxHealth;
    private float hunger = 50;
    private float maxHunger = 100;
    private int age;
    private int movementInterval;
    private EntityType type;
    private List<Status> statuses;

    // Ant fields
    private int colonyId;
    private String nickname;
    private AntColony colony;
    private ColonyMediator mediator;
    private AntState state;
    private AntBehavior behavior;
    private AntMovement movement = new NoMovement();

    public AntBuilder world(World world) { this.world = world; return this; }
    public AntBuilder position(Position position) { this.position = position; return this; }
    public AntBuilder health(float health) { this.health = health; return this; }
    public AntBuilder maxHealth(float maxHealth) { this.maxHealth = maxHealth; return this; }
    public AntBuilder hunger(float hunger) { this.hunger = hunger; return this; }
    public AntBuilder maxHunger(float maxHunger) { this.maxHunger = maxHunger; return this; }
    public AntBuilder age(int age) { this.age = age; return this; }
    public AntBuilder movementInterval(int movementInterval) { this.movementInterval = movementInterval; return this; }
    public AntBuilder type(EntityType type) { this.type = type; return this; }
    public AntBuilder statuses(List<Status> statuses) { this.statuses = statuses; return this; }
    public AntBuilder colonyId(int colonyId) { this.colonyId = colonyId; return this; }
    public AntBuilder nickname(String nickname) { this.nickname = nickname; return this; }
    public AntBuilder colony(AntColony colony) { this.colony = colony; return this; }
    public AntBuilder mediator(ColonyMediator mediator) { this.mediator = mediator; return this; }
    public AntBuilder state(AntState state) { this.state = state; return this; }
    public AntBuilder behavior(AntBehavior behavior) { this.behavior = behavior; return this; }
    public AntBuilder movement(AntMovement movement) { this.movement = movement; return this; }

    /**
     * Builds and returns a WorkerAnt instance with the configured attributes.
     * @return : A WorkerAnt instance.
     */
    public WorkerAnt buildWorkerAnt() {
        WorkerAnt ant = new WorkerAnt(world, colonyId, position.getX(), position.getY(), mediator);
        applyBeingFields(ant);
        applyAntFields(ant);
        return ant;
    }

    /**
     * Builds and returns a QueenAnt instance with the configured attributes.
     * @return : A QueenAnt instance.
     */
    public QueenAnt buildQueenAnt() {
        QueenAnt ant = new QueenAnt(type, world, colonyId, position.getX(), position.getY(), mediator);
        applyBeingFields(ant);
        applyAntFields(ant);
        return ant;
    }

    /**
     * Builds and returns a Larva instance with the configured attributes.
     * @return : A Larva instance.
     */
    public Larva buildLarva() {
        Larva larva = new Larva(world, colonyId, position.getX(), position.getY(), mediator);
        applyBeingFields(larva);
        applyAntFields(larva);
        return larva;
    }

    /**
     * Applies the common Being fields (its super super class) to the given ant.
     * @param ant
     */
    private void applyBeingFields(Ant ant) {
        ant.setHealth(this.health);
        ant.setMaxHealth(this.maxHealth);
        ant.setHunger(this.hunger);
        ant.setMaxHunger(this.maxHunger);
        ant.setAge(this.age);
        ant.setMovementInterval(this.movementInterval);
    }

    /**
     * Applies the common ant fields (its super class) to the given ant.
     * @param ant
     */
    private void applyAntFields(Ant ant) {
        ant.statuses = this.statuses;
        ant.nickname = this.nickname;
        ant.colony = this.colony;
        ant.state = this.state;
        ant.behavior = this.behavior;
        ant.movement = this.movement;
    }
}
