package Model.Ants.Status;

import Model.Ants.Ant;

/**
 * Interface for ant status effects, defining various conditions that can affect an ant's movement and behavior. E.g. Poisoned, Carrying, Starving.
 */
public interface Status {
    public void applyStatus(Ant ant);
}
