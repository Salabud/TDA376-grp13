package Model.Ants;

import java.util.List;

import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.State.AntState;
import Model.Ants.Status.Status;
import Model.Being;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;

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

    public void healthTick(float healthChange){
        this.health = this.health + healthChange;
    }

    @Override
    public void update() {
        //System.out.println("ant update");
        for (Status status : statuses) {
            status.applyStatus(this);
        }
        this.movement.move(this);
        super.update();
    }
}
