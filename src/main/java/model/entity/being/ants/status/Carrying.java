package model.entity.being.ants.status;

import model.entity.being.ants.Ant;

/**
 * Status effect representing an ant carrying something.
 * Currently does not apply any effect but can be expanded to modify ant's speed or other attributes.
 */
public class Carrying implements Status {
    /**
     * Should make ant's slower when carrying something.
     */
    public Carrying() {
    }

    public void applyStatusEffect(Ant ant) {
        
    }
}