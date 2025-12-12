package model.colony.events;

import model.entity.being.ants.Ant;

/**
 * Event fired when an ant becomes idle and is available for a new task.
 */
public class BecameIdleEvent implements ColonyEvent {
    private final Ant source;

    public BecameIdleEvent(Ant source) {
        this.source = source;
    }

    @Override
    public Ant getSource() {
        return source;
    }
}
