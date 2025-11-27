package Model;

import Model.Ants.Status.Status;
import Model.World.World;
import Model.Datastructures.Position;

import java.util.List;

public abstract class Entity implements Updateable{
    protected World world;
    protected Position position;
    protected float health;
    protected float maxHealth;
    protected float hunger;
    protected float maxHunger;
    protected int age;
    protected int movementInterval;
    protected EntityType type; //Final?
    protected List<Status> statuses;

    public void update(){
        System.out.println("entity update");
    }

    public void setX(int x){
        this.position.setX(x);
    }
    public int getX() {
        return this.position.getX();
    }

    public void setY(int y){
        this.position.setY(y);
    }
    public int getY() {
        return this.position.getY();
    }

    public void setPosition(Position position){
        this.position = position;
    }
    public Position getPosition(){
        return this.position;
    }

    public EntityType getType() {
        return this.type;
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

    public World getWorld() {
        return this.world;
    }
}
