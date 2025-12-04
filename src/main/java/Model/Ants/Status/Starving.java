package Model.Ants.Status;

import Model.Ants.Ant;

public class Starving implements Status {

    private float starvationDamage;

    public Starving() {
        this.starvationDamage = 0.3F;
    }

    public void applyStatus(Ant ant){
        ant.healthTick(starvationDamage);
    }
}
