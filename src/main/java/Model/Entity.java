package Model;

import Model.Ants.Behavior.Status;

import java.util.List;

public abstract class Entity implements Updateable{
    private Position position;
    private float health;
    private float maxHealth;
    private float hunger;
    private float maxHunger;
    private int age;
    private int movementInterval;
    private EntityType type; //Final?
    private List<Status> statuses;

    public void update(){

    }
    
    public void setX(int x){
        this.x = x;
    }
    public int getX() {
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getY() {
        return this.y;
    }

    public String getType() {
        return null;
    }

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

    public List<Status> getStatuses() {
        return this.statuses;
    }
    public void applyStatuses(){

    }

    public int getMovementInterval() {
        return this.movementInterval;
    }

    public void setMovementInterval(int movementInterval) {
        this.movementInterval = movementInterval;
    }
}
