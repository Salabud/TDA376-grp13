package model.entity.being.ants.creation;

import model.entity.being.ants.Ant;
import model.entity.being.ants.Larva;
import model.entity.being.ants.QueenAnt;
import model.entity.being.ants.WorkerAnt;
import model.entity.being.ants.status.Status;
import model.entity.being.ants.behavior.AntBehavior;
import model.entity.being.ants.movement.AntMovement;
import model.entity.being.ants.state.AntState;
import model.datastructures.Position;

import java.util.List;

/**
 * Builder for creating Ant instances with optional field overrides.
 * 
 * <p>OBS: Default values for maxHealth/maxHunger are defined in the ant classes themselves.
 * The builder only overrides fields that are explicitly set.
 */
public class AntBuilder {
    private Position position;
    private Float health;
    private Float maxHealth;
    private Float hunger;
    private Float maxHunger;
    private Float age;
    private Integer movementInterval;
    private List<Status> statuses;
    private String nickname;
    private AntState state;
    private AntBehavior behavior;
    private AntMovement movement;

    public AntBuilder position(Position position) { this.position = position; return this; }
    public AntBuilder position(int x, int y) { this.position = new Position(x, y); return this; }
    public AntBuilder health(float health) { this.health = health; return this; }
    public AntBuilder maxHealth(float maxHealth) { this.maxHealth = maxHealth; return this; }
    public AntBuilder hunger(float hunger) { this.hunger = hunger; return this; }
    public AntBuilder maxHunger(float maxHunger) { this.maxHunger = maxHunger; return this; }
    public AntBuilder age(float age) { this.age = age; return this; }
    public AntBuilder movementInterval(int movementInterval) { this.movementInterval = movementInterval; return this; }
    public AntBuilder statuses(List<Status> statuses) { this.statuses = statuses; return this; }
    public AntBuilder nickname(String nickname) { this.nickname = nickname; return this; }
    public AntBuilder state(AntState state) { this.state = state; return this; }
    public AntBuilder behavior(AntBehavior behavior) { this.behavior = behavior; return this; }
    public AntBuilder movement(AntMovement movement) { this.movement = movement; return this; }

    /** Builds a WorkerAnt with any configured overrides applied. */
    public WorkerAnt buildWorkerAnt() {
        WorkerAnt ant = new WorkerAnt(position);
        applyOverrides(ant);
        return ant;
    }

    /** Builds a QueenAnt with any configured overrides applied. */
    public QueenAnt buildQueenAnt() {
        QueenAnt ant = new QueenAnt(position);
        applyOverrides(ant);
        return ant;
    }

    /** Builds a Larva with any configured overrides applied. */
    public Larva buildLarva() {
        Larva larva = new Larva(position);
        applyOverrides(larva);
        return larva;
    }

    /**
     * Applies any explicitly set fields to the ant.
     * Filds that weren't set in the builder remain at their class defaults.
     */
    private void applyOverrides(Ant ant) {
        // Being fields
        if (health != null) ant.setHealth(health);
        if (maxHealth != null) ant.setMaxHealth(maxHealth);
        if (hunger != null) ant.setHunger(hunger);
        if (maxHunger != null) ant.setMaxHunger(maxHunger);
        if (age != null) ant.setAge(age);
        if (movementInterval != null) ant.setMovementInterval(movementInterval);
        
        // Ant fields
        if (statuses != null) ant.setStatuses(statuses);
        if (nickname != null) ant.setNickname(nickname);
        if (state != null) ant.setState(state);
        if (behavior != null) ant.setBehavior(behavior);
        if (movement != null) ant.setMovement(movement);
    }
}
