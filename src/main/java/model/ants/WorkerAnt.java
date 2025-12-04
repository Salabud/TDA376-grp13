package model.ants;

import model.AntType;
import model.BeingType;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.tasks.EatTask;
import model.world.World;
import org.json.JSONObject;

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
    }

    @Override
    public void update() {
        // Report hunger if below temp threshold (30) no matter current task
        if (getHunger() < 30 && !(currentTask instanceof EatTask)) {
            mediator.reportHungry(this);
        }
        //System.out.println("Worker Ant Hunger:"+getHunger());
        super.update();
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
