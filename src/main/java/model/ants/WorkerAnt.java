package model.ants;

import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.world.World;

/** Represents a worker ant in the simulation. */
public class WorkerAnt extends TaskPerformerAnt {

    public WorkerAnt(EntityType type, World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.type = EntityType.WORKER_ANT;
        this.world = world;
        this.colonyId = colonyId;
        this.position = new Position(x,y);
        this.mediator = mediator;
    }

    @Override
    public void update() {
        //System.out.println("workerAnt update");
        super.update();
    }
}
