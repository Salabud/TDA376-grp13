package Model.Ants;

import Model.Ants.Movement.NoMovement;
import Model.Ants.Status.Status;
import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.State.AntState;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Datastructures.Position;
import Model.EntityType;
import Model.World.World;
import java.util.List;

public class AntBuilder {
    // Entity fields
    private World world;
    private Position position;
    private float health;
    private float maxHealth;
    private float hunger;
    private float maxHunger;
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

    public WorkerAnt buildWorkerAnt() {
        WorkerAnt ant = new WorkerAnt(type, world, colonyId, position.getX(), position.getY(), mediator);
        applyEntityFields(ant);
        applyAntFields(ant);
        return ant;
    }

    public QueenAnt buildQueenAnt() {
        QueenAnt ant = new QueenAnt();
        applyEntityFields(ant);
        applyAntFields(ant);
        return ant;
    }

    public Larva buildLarva() {
        Larva larva = new Larva(world, colonyId, position.getX(), position.getY(), mediator);
        applyEntityFields(larva);
        applyAntFields(larva);
        return larva;
    }

    private void applyEntityFields(Ant ant) {
        ant.setHealth(this.health);
        ant.setMaxHealth(this.maxHealth);
        ant.setHunger(this.hunger);
        ant.setMaxHunger(this.maxHunger);
        ant.setAge(this.age);
        ant.setMovementInterval(this.movementInterval);
    }

    private void applyAntFields(Ant ant) {
        ant.statuses = this.statuses;
        ant.nickname = this.nickname;
        ant.colony = this.colony;
        ant.state = this.state;
        ant.behavior = this.behavior;
        ant.movement = this.movement;
    }
}
