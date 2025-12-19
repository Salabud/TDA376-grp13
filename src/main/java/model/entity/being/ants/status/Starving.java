package model.entity.being.ants.status;

import model.entity.being.ants.Ant;

/**
 * Status effect representing starvation in an ant.
 * Applies periodic health damage to the affected ant.
 */
public class Starving implements Status{

    private final float starvationDamagePerTick;
    private final float BASE_STARVATION_DAMAGE = 0.3F;

    public Starving() {
        this.starvationDamagePerTick = BASE_STARVATION_DAMAGE;
    }

    public void applyStatusEffect(Ant ant) {
        ant.healthTick(-starvationDamagePerTick);
    }
    
}
