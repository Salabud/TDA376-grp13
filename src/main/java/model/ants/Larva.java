package model.ants;

import model.AntType;
import model.ants.status.Status;
import model.BeingType;
import model.Carryable;
import model.colony.ColonyMediator;
import model.datastructures.Position;
import model.EntityType;
import model.world.World;

import java.util.ArrayList;
import java.util.List;

/** Represents a larva in the simulation. */
public class Larva extends Ant implements Carryable {
    private float TRANSFORM_AGE = 5*60F; // In seconds
    private static final float HUNGER_THRESHOLD = 50f; // Report hunger when below this level
    private boolean hasReportedHunger = false; // Prevent spamming reports

    public Larva(World world, int colonyId, int x, int y, ColonyMediator mediator){
        this.position = new Position(x,y);
        this.mediator = mediator;
        type = EntityType.BEING;
        beingType = BeingType.ANT;
        antType = AntType.LARVA;
        this.statuses = new ArrayList<>();
    }
    public Larva(World world, int colonyId, int x, int y, int age,
                 String nickname, ColonyMediator mediator, float health, float maxHealth, float hunger,
                 float maxHunger, int movementInterval, List<Status> statuses){
        position = new Position(x,y);
        this.mediator = mediator;
    }

    @Override
    public void moveTo(Position position) {
        this.position = position;
    }

    //TODO: Implement becomeWorker
    public void becomeWorker(){
        System.out.println("becoming worker");
    }

    @Override
    public void update(){
        // Report hunger to mediator
        if (getHunger() < HUNGER_THRESHOLD && !hasReportedHunger && mediator != null) {
            System.out.println("report Larva hunger");
            mediator.reportLarvaHungry(this);
            hasReportedHunger = true;
        }
        
        // Reset the flag once hunger is restored
        if (getHunger() >= HUNGER_THRESHOLD) {
            hasReportedHunger = false;
        }
        
        if (this.getAge() > TRANSFORM_AGE){
            becomeWorker();
        }
        super.update();
    }
}
