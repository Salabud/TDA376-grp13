package model.entity.being.ants.status;

import model.colony.tasks.FeedBeingTask;
import model.colony.tasks.Task;
import model.entity.being.ants.Ant;

/**
 * Status effect representing poisoning in an ant.
 * Applies periodic health damage and increases hunger of the affected ant.
 */
public class Poisoned implements Status {

    private final float poisonDamagePerTick;
    private final float poisonHungerPerTick;
    private final float BASE_POISON_DAMAGE = 0.3F;
    private final float BASE_HUNGER_DAMAGE = 0.2F;

    public Poisoned() {
        this.poisonDamagePerTick = BASE_POISON_DAMAGE;
        this.poisonHungerPerTick = BASE_HUNGER_DAMAGE;
     }

    public void applyStatusEffect(Ant ant) {
        ant.healthTick(-poisonDamagePerTick);
        ant.hungerTick(poisonHungerPerTick);
    }  
    @Override
    public boolean allowsTask(Task task) {
        return !(task instanceof FeedBeingTask);
    }
}
