package model.ants;

import model.AntType;
import model.ants.status.Status;
import model.BeingType;
import model.Carryable;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.world.World;

import java.util.List;

/** Represents a larva in the simulation. */
public class Larva extends Ant implements Carryable {
    public Larva(World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.position = new Position(x,y);
        type = EntityType.BEING;
        beingType = BeingType.ANT;
        antType = AntType.LARVA;

    }
    public Larva(World world, int colonyId, int x, int y, int age,
                 String nickname, ColonyMediator mediator, float health, float maxHealth, float hunger,
                 float maxHunger, int movementInterval, List<Status> statuses){
        position = new Position(x,y);
        type = EntityType.LARVA;
    }

    @Override
    public void move(Position position) {

    }

    public void becomeWorker(){

    }
}
