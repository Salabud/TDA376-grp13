package model.colony.events;

import model.entity.being.ants.Ant;
import model.entity.being.ants.Larva;

/**
 * Event fired when a larva is ready to transform into a worker ant.
 * The AntSpawner listens for this event and handles the actual transformation by:
 * 1. Creating a new WorkerAnt via the factory
 * 2. Removing the Larva from World and Colony
 * 3. Registering the WorkerAnt with World and Colony
 */
public class LarvaTransformEvent implements ColonyEvent {
    private final Larva larva;

    public LarvaTransformEvent(Larva larva) {
        this.larva = larva;
    }

    public Larva getLarva() {
        return larva;
    }

    @Override
    public Ant getSource() {
        return larva;
    }



}
