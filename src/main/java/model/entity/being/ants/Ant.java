package model.entity.being.ants;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import model.entity.being.Being;
import model.entity.being.ants.behavior.AntBehavior;
import model.entity.being.ants.movement.AntMovement;
import model.entity.being.ants.movement.NoMovement;
import model.entity.being.ants.state.AntState;
import model.entity.being.ants.status.Status;
import model.colony.AntColony;
import model.colony.events.ColonyEvent;
import model.colony.events.ColonyEventListener;

/** Abstract class representing an ant in the simulation. */
public abstract class Ant extends Being {
    protected int colonyId;
    protected String nickname;
    protected AntColony colony;
    protected List<Status> statuses;
    protected AntState state;
    protected AntBehavior behavior;
    protected AntMovement movement;
    protected AntType antType;
    
    // Event listeners for observer pattern
    private final List<ColonyEventListener> eventListeners = new ArrayList<>();

    public AntMovement getMovement() {
        return movement;
    }

    /**
     * Sets the movement strategy for the ant.
     * 
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
     * 
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
     * 
     * @param state : The new state.
     */
    public void setState(AntState state) {
        this.state = state;
    }

    @Override
    public void update() {
        for (Status state : statuses) {
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
        super.update(); // "Being stuff": hunger and age ticking
    }

    public AntType getAntType() {
        return antType;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public List<Status> getStatuses() {
        return statuses;
    }
    
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
    
    // Event broadcasting methods
    
    /**
     * Adds an event listener to receive colony events from this ant.
     * @param listener the listener to add
     */
    public void addEventListener(ColonyEventListener listener) {
        if (listener != null && !eventListeners.contains(listener)) {
            eventListeners.add(listener);
        }
    }
    
    /**
     * Removes an event listener.
     * @param listener the listener to remove
     */
    public void removeEventListener(ColonyEventListener listener) {
        eventListeners.remove(listener);
    }
    
    /**
     * Returns an unmodifiable view of the event listeners.
     * @return the list of event listeners
     */
    public List<ColonyEventListener> getEventListeners() {
        return List.copyOf(eventListeners);
    }
    
    /**
     * Broadcasts an event to all registered listeners.
     * @param event the event to broadcast
     */
    public void broadcastEvent(ColonyEvent event) {
        for (ColonyEventListener listener : eventListeners) {
            listener.onColonyEvent(event);
        }
    }
    
    public AntColony getColony() {
        return colony;
    }

    /**
     * Create a JSON Object of the entity
     * 
     * @return
     */
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("nickname", nickname);
        obj.put("antType", antType);
        obj.put("colonyId", colonyId);
        // TODO implement after refactoring of Entity
        return obj;
    }

    public int getColonyId() {
        return colonyId;
    }
}
