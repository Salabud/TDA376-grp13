package model.colony.events;

import model.entity.being.Being;
import model.entity.being.ants.Ant;

/**
 * Event fired when an ant or being is hungry and needs food.
 */
public class HungryEvent implements ColonyEvent {
    private final Being hungryBeing;
    private final Ant source;

    /**
     * Creates a hungry event for an ant.
     * @param ant : the hungry ant (also the source)
     */
    public HungryEvent(Ant ant) {
        this.hungryBeing = ant;
        this.source = ant;
    }

    /**
     * Creates a hungry event for a being (like Larva) with a specified source.
     * @param being : the hungry being
     * @param source : the ant broadcasting this event (can be the same as being if it's an ant)
     */
    public HungryEvent(Being being, Ant source) {
        this.hungryBeing = being;
        this.source = source;
    }

    public Being getHungryBeing() {
        return hungryBeing;
    }

    @Override
    public Ant getSource() {
        return source;
    }
}
