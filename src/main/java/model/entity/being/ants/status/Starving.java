package model.entity.being.ants.status;

import model.ants.Ant;

public class Starving implements Status{

    private float starvationDamagePerTick;

    public Starving() {
        this.starvationDamagePerTick = 0.3F;
    }

    public void applyStatusEffect(Ant ant) {
        ant.healthTick(-starvationDamagePerTick);
    }
    
}
