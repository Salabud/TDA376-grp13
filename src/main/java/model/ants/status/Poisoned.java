package model.ants.status;

import model.ants.Ant;

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
}
