package model.ants;

import model.AntType;
import model.BeingType;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.ants.movement.RandomMovement;
import model.ants.state.AntState;
import model.tasks.BirthTask;
import model.tasks.EatTask;
import model.tasks.Task;
import model.world.World;
import org.json.JSONObject;

import java.util.ArrayList;

/** Represents a worker ant in the simulation. */
public class WorkerAnt extends TaskPerformerAnt {

    public WorkerAnt(World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.type = EntityType.BEING;
        this.beingType = BeingType.ANT;
        this.antType = AntType.WORKER_ANT;
        this.world = world;
        this.colonyId = colonyId;
        this.position = new Position(x,y);
        this.mediator = mediator;
        this.statuses = new ArrayList<>();
    }

    @Override
    public void update() {
        if (getHunger() < 30 && getState() != AntState.FEEDING && !(currentTask instanceof EatTask)) {
            mediator.reportHungry(this);
        }
        if (currentTask == null && this.state == AntState.IDLE && !(this.movement instanceof RandomMovement)) {
            this.movement = new RandomMovement(this, world.getTileGrid());
        }
        super.update();
    }

    @Override
    protected boolean isTaskTypeAllowed(Task task) {
        // Workers cannot give birth
        if (task instanceof BirthTask) {
            return false;
        }
        return true;
    }

    /**
     * Create a JSON Object of the entity
     * @return
     */
    @Override
    public JSONObject toJSON(){
        JSONObject obj = super.toJSON();
        //TODO
        return obj;
    }
}
