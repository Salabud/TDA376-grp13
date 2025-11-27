package Model;

import Model.World.World;

/**
 * Observer interface for Model changes.
 * Follows Interface Segregation Principle - clients only depend on methods they use.
 */
public interface ModelListener {
    /**
     * Called when the model state has been updated.
     */
    void onModelUpdated();
    
    /**
     * Called when entities in the model have changed.
     */
    void onEntitiesChanged(World world);

    /**
     * Called when the tiles of the map need to be redrawn
     * Author: Ludvig
     * Since 2025-11-26
     * @param world the world that is to be rendered
     */
    void onTilesetChanged(World world);
    
    /**
     * Called when the game state changes (pause, game over, etc.)
     * @param newState The new game state
     */
    void onGameStateChanged(String newState);
}
