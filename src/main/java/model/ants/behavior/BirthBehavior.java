package model.ants.behavior;

import model.ants.Ant;
import model.ants.AntFactory;
import model.colony.AntColony;
import model.colony.ColonyMediator;

/**
 * Behavior strategy for giving birth to a larva.
 * The queen ant is stationary and birthing over time.
 * When complete, a new larva is spawned at the queen's position.
 */
public class BirthBehavior implements AntBehavior {
    
    private final AntColony colony;
    private final ColonyMediator mediator;
    private int birthProgress;
    private boolean isComplete;
    
    private static final int BIRTH_DURATION = 120; // 2 seconds at 60 tps
    
    /**
     * Create a birth behavior with the colony context.
     * 
     * @param colony : the colony to add the larva to
     * @param mediator : the mediator for the new larva
     */
    public BirthBehavior(AntColony colony, ColonyMediator mediator) {
        this.colony = colony;
        this.mediator = mediator;
        this.birthProgress = 0;
        this.isComplete = false;
    }
    
    @Override
    public void perform(Ant ant) {
        if (isComplete) return;
        
        birthProgress++;
        
        if (birthProgress >= BIRTH_DURATION) {
            AntFactory.getInstance().createLarva(
                    ant.getWorld(),
                    colony,
                    ant.getColonyId(),
                    ant.getX(),
                    ant.getY(),
                    mediator
            );
            
            isComplete = true;
        }
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    /**
     * Get birth progress as a percentage (0-100).
     */
    public int getProgressPercent() {
        return (birthProgress * 100) / BIRTH_DURATION;
    }
}
