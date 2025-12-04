package model.ants.status;

import model.ants.Ant;

/**
 * Interface for ant status effects, 
 * defining various conditions that can affect an ant's movement and behavior. 
 * <p>E.g. Poisoned, Carrying, Starving.
 */
public interface Status {
    public void applyStatusEffect(Ant ant);
}
