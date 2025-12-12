package model.colony.events;

import model.entity.being.ants.Ant;

/**
 * Base interface for all colony events.
 * Events are broadcast by ants and received by listeners (like ColonyMediator).
 */
public interface ColonyEvent {
    /**
     * Gets the ant that generated this event.
     * @return the source ant, or null if not applicable
     */
    Ant getSource();
}
