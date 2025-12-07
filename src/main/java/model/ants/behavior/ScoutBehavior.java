package model.ants.behavior;

import model.Entity;
import model.ants.Ant;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import model.world.Item;
import model.world.MaterialType;
import model.world.World;

import java.util.List;

/**
 * Behavior for observing surroundings and reporting discoveries.
 * Scans a square area around the ant and reports any unknown food to the mediator.
 */
public class ScoutBehavior implements AntBehavior {

    //TODO: Introduce Scoutable interface (for items and larva)
    private static final int DEFAULT_DETECTION_SIZE = 5;
    private final int detectionSize;

    public ScoutBehavior() {
        this(DEFAULT_DETECTION_SIZE);
    }
    
    public ScoutBehavior(int detectionSize) {
        this.detectionSize = detectionSize;
    }

    @Override
    public void perform(Ant ant) {
        World world = ant.getWorld();
        ColonyMediator mediator = ant.getMediator();
        AntColony colony = ant.getColony();
        
        if (world == null || mediator == null || colony == null) {
            return;
        }
        
        int antX = ant.getX();
        int antY = ant.getY();
        List<Item> knownFood = colony.getKnownFood();
        List<Entity>[][] entityGrid = world.getEntityGrid();
        int gridSize = entityGrid.length;
        
        for (int dx = -detectionSize; dx <= detectionSize; dx++) {
            for (int dy = -detectionSize; dy <= detectionSize; dy++) {
                int checkX = antX + dx;
                int checkY = antY + dy;
                
                if (checkX < 0 || checkX >= gridSize || checkY < 0 || checkY >= gridSize) {
                    continue;
                }
                
                List<Entity> entitiesAtPosition = entityGrid[checkX][checkY];
                if (entitiesAtPosition == null) {
                    continue;
                }
                
                for (Entity entity : entitiesAtPosition) {
                    if (entity instanceof Item item && 
                        item.getMaterialType() == MaterialType.FOOD && 
                        item.isScouted() == false) {
                        if (!isKnownFood(item, knownFood)) {
                            System.out.println("reported a scouted item");
                            mediator.reportFoodDiscovered(item);
                        }
                    } //TODO: Also report corpses/larva that are not in the nursery.
                }
            }
        }
    }
    
    /**
     * Check if a food item is already in the known food list.
     */
    private boolean isKnownFood(Item food, List<Item> knownFood) {
        for (Item known : knownFood) {
            if (known.getPosition().equals(food.getPosition())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
