package model.colony.events;

import model.entity.being.ants.Ant;
import model.entity.item.Item;

/**
 * Event fired when food is discovered by a scouting ant.
 */
public class FoodDiscoveredEvent implements ColonyEvent {
    private final Item food;
    private final Ant source;

    public FoodDiscoveredEvent(Item food, Ant source) {
        this.food = food;
        this.source = source;
    }

    public Item getFood() {
        return food;
    }

    @Override
    public Ant getSource() {
        return source;
    }
}
