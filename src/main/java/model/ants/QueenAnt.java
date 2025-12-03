package model.ants;

import model.AntType;
import model.BeingType;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.world.World;

/** Represents the queen ant in the simulation. */
public class QueenAnt extends TaskPerformerAnt {

    public QueenAnt(EntityType type, World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.type = EntityType.BEING;
        this.beingType = BeingType.ANT;
        this.antType = AntType.QUEEN;
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
