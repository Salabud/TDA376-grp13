package model.ants;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import model.AntType;
import model.Being;
import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.movement.NoMovement;
import model.ants.state.AntState;
import model.ants.status.Status;
import model.colony.AntColony;
import model.colony.ColonyMediator;

/** Abstract class representing an ant in the simulation. */
public abstract class Ant extends Being {
    protected int colonyId;
    protected String nickname;
    protected AntColony colony;
    protected ColonyMediator mediator;
    protected List<Status> statuses;
    protected AntState state;
    protected AntBehavior behavior;
    protected AntMovement movement;
    protected AntType antType;


    public AntMovement getMovement() {
        return movement;
    }

    /**
     * Sets the movement strategy for the ant.
     * @param movement : The movement strategy to set.
     */
    public void setMovement(AntMovement movement) {
        this.movement = movement;
    }

    public void healthTick(float healthChange) {
        this.health += healthChange;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        if (this.health < 0) {
            this.health = 0;
        }
    }
    
    public AntBehavior getBehavior() {
        return behavior;
    }
    
    /**
     * Sets the behavior strategy for the ant.
     * @param behavior : The behavior strategy to set, or null to clear.
     */
    public void setBehavior(AntBehavior behavior) {
        this.behavior = behavior;
    }
    
    public AntState getState() {
        return state;
    }
    
    /**
     * Sets the current state of the ant.
     * @param state : The new state.
     */
    public void setState(AntState state) {
        this.state = state;
    }

    @Override
    public void update() {
        for(Status state : statuses) {
            state.applyStatusEffect(this);
        }
        // Execute movement strategy (if any)
        if (movement != null) {
            movement.move(this);
            
            // If movement just completed, switch to NoMovement
            if (movement.isComplete() && !(movement instanceof NoMovement)) {
                movement = new NoMovement();
            }
        }
        
        // Execute behavior strategy (if any)
        if (behavior != null) {
            behavior.perform(this);
        }
        
        super.update();
    }

    public AntType getAntType(){
        return antType;
    }

    /**
     * Create a JSON Object of the entity
     * @return
     */
    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        obj.put("nickname", nickname);
        obj.put("antType", antType);
        obj.put("colonyId", colonyId);
        //TODO implement after refactoring of Entity
        return obj;
    }

    public int getColonyId() {
        return colonyId;
    }
}
