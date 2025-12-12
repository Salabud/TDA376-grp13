package model.colony.events;

import model.entity.being.ants.Ant;
import model.entity.being.ants.QueenAnt;

/**
 * Event fired when the colony needs a new larva to be born.
 */
public class BirthRequestEvent implements ColonyEvent {
    private final QueenAnt queen;
    private final Ant source;

    public BirthRequestEvent(QueenAnt queen, Ant source) {
        this.queen = queen;
        this.source = source;
    }

    /**
     * Just to be safe, constructor when the source is the queen herself.
     */
    public BirthRequestEvent(QueenAnt queen) {
        this.queen = queen;
        this.source = queen;
    }

    public QueenAnt getQueen() {
        return queen;
    }

    @Override
    public Ant getSource() {
        return source;
    }
}
