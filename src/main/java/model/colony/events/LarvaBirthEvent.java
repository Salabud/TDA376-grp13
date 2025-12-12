package model.colony.events;

import model.ants.Ant;
import model.datastructures.Position;

/**
 * Event fired when a queen has finished birthing a larva.
 * The AntSpawner listens to this event to spawn and register the larva.
 */
public class LarvaBirthEvent implements ColonyEvent {
    private final Ant queen;
    private final Position birthPosition;
    
    /**
     * Creates a larva birth event.
     * @param queen : the queen that gave birth
     * @param birthPosition : the position where the larva should be spawned
     */
    public LarvaBirthEvent(Ant queen, Position birthPosition) {
        this.queen = queen;
        this.birthPosition = birthPosition;
    }
    
    @Override
    public Ant getSource() {
        return queen;
    }
    
    /**
     * Gets the position where the larva should be spawned.
     * @return the birth position
     */
    public Position getBirthPosition() {
        return birthPosition;
    }
}
