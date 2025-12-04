package Model.Ants.Status;

import Model.Ants.Ant;

public class Poisoned implements Status {
    
    private float poisonDamage;
    private float poisonHungerDrain;

    public Poisoned() {
        this.poisonDamage = -0.2F;
        this.poisonHungerDrain = 0.3F;
    }

    public void applyStatus(Ant ant){
        ant.hungerTick(poisonHungerDrain);
        ant.healthTick(poisonDamage);
    }
}
