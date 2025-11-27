package Model.Ants;

import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.State.AntState;
import Model.Ants.Status.Status;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Entity;

import java.util.List;

public abstract class Ant extends Entity {
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

    public void setMovement(AntMovement movement) {
        this.movement = movement;
    }

    @Override
    public void update() {

    }
}
