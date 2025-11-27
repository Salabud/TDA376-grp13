package Model.Ants;

import java.util.List;

import Model.Colony.ColonyMediator;
import Model.Datastructures.Position;
import Model.EntityType;
import Model.TaskPerformer;
import Model.Ants.Status.Status;
import Model.Tasks.Task;
import Model.World.World;

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
        if (currentTask != null) {
            currentTask.execute(this);
        }
    }
}
