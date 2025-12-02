package model.ants;

import model.ants.behavior.AntBehavior;
import model.ants.movement.AntMovement;
import model.ants.state.AntState;
import model.ants.status.Status;
import model.Being;
import model.colony.AntColony;
import model.colony.ColonyMediator;

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
}
