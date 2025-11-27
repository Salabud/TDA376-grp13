package Model;

import Model.World.World;
import View.EntityCanvas;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class - the Subject in the Observer pattern.
 * Follows Single Responsibility Principle - manages game state only.
 * Follows Open/Closed Principle - extensible through listeners.
 */
public class Model extends Application {
    private int tickrate;
    private List<World> worlds;
    private List<ModelListener> listeners;
    private String gameState;
    
    public Model() {
        this.worlds = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.tickrate = 60; // Default tickrate
        this.gameState = "RUNNING";
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Game initialization logic here
    }
    
    /**
     * Add a listener to observe model changes.
     * Follows Dependency Inversion - depends on abstraction (ModelListener).
     */
    public void addListener(ModelListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    
    /**
     * Remove a listener from observing model changes.
     */
    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Notify all listeners of a model update.
     */
    protected void notifyModelUpdated() {
        for (ModelListener listener : listeners) {
            listener.onModelUpdated();
        }
    }

    protected void notifyTilesetChanged() {
        for (ModelListener listener : listeners){
            listener.onTilesetChanged(worlds.getFirst()); //refactor when we are handling multiple worlds
        }
    }
    
    /**
     * Notify all listeners of an entity change.
     */
    protected void notifyEntitiesChanged() {
        for (ModelListener listener : listeners) {
            listener.onEntitiesChanged(worlds.getFirst()); //refactor when we are handling multiple worlds
        }
    }
    
    /**
     * Notify all listeners of a game state change.
     */
    protected void notifyGameStateChanged(String newState) {
        this.gameState = newState;
        for (ModelListener listener : listeners) {
            listener.onGameStateChanged(newState);
        }
    }
    
    // Getters and business logic methods
    public int getTickrate() {
        return tickrate;
    }
    
    public void setTickrate(int tickrate) {
        this.tickrate = tickrate;
        notifyModelUpdated();
    }
    
    public String getGameState() {
        return gameState;
    }
    
    /**
     * Main game loop update method.
     * Called each tick to update game state.
     */
    public void update() {
        // Update game logic
        for (World world: worlds){
            world.tick();
        }
        notifyEntitiesChanged();
        notifyTilesetChanged();
    }

    public void addWorld(World world) {
        this.worlds.add(world);
    }
    public void removeWorld(World world) {
        this.worlds.remove(world);
    }
}
