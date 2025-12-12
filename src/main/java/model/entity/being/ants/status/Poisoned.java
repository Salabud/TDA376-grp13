package model.entity.being.ants.status;

import model.entity.being.ants.Ant;
import model.colony.tasks.FeedBeingTask;
import model.colony.tasks.Task;

public class Poisoned implements Status {

    private float poisonDamagePerTick;
    private float poisonHungerPerTick;

    public Poisoned() {
        this.poisonDamagePerTick = 0.3F;
        this.poisonHungerPerTick = 0.2F;
     }

    public void applyStatusEffect(Ant ant) {
        ant.healthTick(-poisonDamagePerTick);
        ant.hungerTick(poisonHungerPerTick);
    }  
    @Override
    public boolean allowsTask(Task task) {
        if (task instanceof FeedBeingTask) {
            return false;
        }
        return true;
    }
}
