package model.entity.being.ants.status;

import model.entity.being.ants.Ant;

/**
 * Status effect representing starvation in an ant.
 * Applies periodic health damage to the affected ant.
 */
public class Starving implements Status{

    private float starvationDamagePerTick;

    public Starving() {
        this.starvationDamagePerTick = 0.3F;
    }

    public void applyStatusEffect(Ant ant) {
        ant.healthTick(-starvationDamagePerTick);
    }
    
}
