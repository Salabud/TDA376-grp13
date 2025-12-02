package model;

import model.ants.status.Status;

import java.util.List;

/**
 * Abstract base class for all beings (e.g. ants) in the simulation.
 * Contains common attributes and methods shared by all "living" beings in the simulation.
 */
public abstract class Being extends Entity {
    protected float health;
    protected float maxHealth;
    protected float hunger;
    protected float maxHunger;
    protected int age;
    protected float hungerDepletionRate = 0.01F;
    protected List<Status> statuses;

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHunger() {
        return this.hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public float getMaxHunger() {
        return this.maxHunger;
    }

    public void setMaxHunger(float maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHungerDepletionRate(){
        return this.hungerDepletionRate;
    }

    public void setHungerDepletionRate(float hungerDepletionRate){
        this.hungerDepletionRate = hungerDepletionRate;
    }

    public List<Status> getStatuses() {
        return this.statuses;
    }
    public void applyStatuses(){

    }

    public void hungerTick(float depletionRate){
        this.hunger = this.hunger - depletionRate;
    }

    @Override
    public void update() {
        hungerTick(hungerDepletionRate);
        System.out.println(hunger);
        super.update();
    }
}
