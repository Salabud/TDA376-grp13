package Model.Ants;

import Model.Colony.ColonyMediator;
import Model.Datastructures.Position;
import Model.EntityType;
import Model.Tasks.Task;
import Model.World.World;

/** Represents the queen ant in the simulation. */
public class QueenAnt extends TaskPerformerAnt {

    public QueenAnt(EntityType type, World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.type = EntityType.QUEEN;
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

    public void layLarva(int amount){

    }
}
