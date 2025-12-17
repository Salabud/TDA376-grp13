package model.entity.being.ants.behavior;

import model.entity.being.ants.Ant;
import model.world.Tile;

/**
 * Behavior representing the digging action of an ant.
 * The ant will dig a specified tile over a set duration.
 */
public class DigBehavior implements AntBehavior{
    private boolean isComplete;
    private int diggingProgress;
    private Tile tileToDig;

    private static final int DIGGING_DURATION = 60;

    public DigBehavior(Tile  tileToDig) {
        isComplete = false;
        diggingProgress = 0;
        this.tileToDig = tileToDig;
    }

    @Override
    public void perform(Ant ant) {
        if (isComplete) return;

        diggingProgress++;
        System.out.println("Eating: " + diggingProgress);
        if (diggingProgress >= DIGGING_DURATION) {
            //ant.getWorld().removeTile(tileToDig.getPosition()); //TODO Use a listener here instead
            //ant.getWorld().addEntity(tileToDig.toItem());
            ant.setBehavior(null);
            isComplete = true;
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Get eating progress as a percentage (0-100).
     */
    public int getProgressPercent() {
        return (diggingProgress * 100) / DIGGING_DURATION;
    }
}
