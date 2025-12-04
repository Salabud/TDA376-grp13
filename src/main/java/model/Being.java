package model;

import model.ants.status.Status;
import org.json.JSONObject;

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
    protected float age;
    protected float hungerDepletionRate = 0.01F;
    protected float agingRate = 1 / 60F; // in "seconds"
    protected List<Status> statuses;
    protected BeingType beingType;

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

    public float getAge() {
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

    public void ageTick(float agingRate){
        this.age = this.age + agingRate;
    }

    @Override
    public void update() {
        hungerTick(hungerDepletionRate);
        ageTick(agingRate);
        super.update();
    }

    public BeingType getBeingType(){
        return beingType;
    }
    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        obj.put("health", health);
        obj.put("maxHealth", maxHealth);
        obj.put("hunger", hunger);
        obj.put("maxHunger", maxHunger);
        obj.put("age", age);
        //obj.put("hungerDepletionRate", hungerDepletionRate);
        obj.put("beingType", beingType);
        //TODO add statuses
        return obj;
    }
}
