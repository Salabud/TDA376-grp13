package model.ants;

import model.AntType;
import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.state.AntState;
import model.ants.status.Status;
import model.Being;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import org.json.JSONObject;

import java.util.List;

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

    @Override
    public void update() {
        //System.out.println("ant update");
        this.movement.move(this);
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
        //TODO implement after refactoring of Entity
        return obj;
    }
}
