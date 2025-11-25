package Model;

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
    void onEntitiesChanged();
    
    /**
     * Called when the game state changes (pause, game over, etc.)
     * @param newState The new game state
     */
    void onGameStateChanged(String newState);
}
