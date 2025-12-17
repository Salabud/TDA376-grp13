package model;

import model.saving.SaveFileCreator;
import model.saving.SaveFileLoader;
import model.world.World;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Model class represents the core data and logic of the ant simulation.
 * It manages the worlds, entities, and the game state, and notifies listeners of changes.
 */
public class Model {
    private int startingTickrate;
    private int tickrate;
    private List<World> worlds;
    private List<ModelListener> listeners;
    private String gameState;
    private ScheduledExecutorService tickExecutor;
    private boolean isRunning;
    
    public Model() {
        this.worlds = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.startingTickrate = 60;
        this.tickrate = startingTickrate;
        this.gameState = "MAIN_MENU";
        this.isRunning = false;
    }
    public void initialise(){
        setGameState("MAIN_MENU");
    }

    /**
     * Updates the model by ticking all worlds and notifying listeners of changes.
     */
    public void update() {
        for (World world : worlds) {
            if(gameState.equals("RUNNING")){
                world.tick();
            }
            if(world.checkTilesChanged()){ //Re-render tileset only if there have been changes
                notifyTilesetChanged();
                world.setTilesChanged(false);
            }

        };
        notifyEntitiesChanged();

    }

    /**
     * Starts the ticking process of the model, updating at the set tickrate.
     */
    public void startTicking() {
        if (isRunning) return;
        isRunning = true;
        tickExecutor = Executors.newSingleThreadScheduledExecutor();
        tickExecutor.scheduleWithFixedDelay(() -> {
            Platform.runLater(this::update);
        }, 0, tickrate, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the ticking process of the model.
     */
    public void stopTicking() {
        isRunning = false;
        if (tickExecutor != null && !tickExecutor.isShutdown()) {
            tickExecutor.shutdownNow();
        }
    }

    /**
     * Restarts the ticking process to apply a new tickrate.
     */
    private void restartTicking() {
        if (isRunning) {
            stopTicking();
            startTicking();
        }
    }
    
    /**
     * Adds a listener to the model for extensibility.
     * @param listener : The listener to add.
     */
    public void addListener(ModelListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Removes a listener from the model.
     * @param listener : The listener to remove.
     */
    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all listeners that the model has been updated.
     */
    protected void notifyModelUpdated() {
        for (ModelListener listener : listeners) {
            listener.onModelUpdated();
        }
    }

    /**
     * Notifies all listeners that the tileset has changed.
     */
    protected void notifyTilesetChanged() {
        for (ModelListener listener : listeners){
            listener.onTilesetChanged(worlds.getFirst()); //refactor when we are handling multiple worlds
        }
    }

    /**
     * Notifies all listeners that the entities have changed.
     */
    protected void notifyEntitiesChanged() {
        for (ModelListener listener : listeners) {
            listener.onEntitiesChanged(worlds.getFirst()); //Refactor when we are handling multiple worlds
        }
    }
    
    public int getTickrate() {
        return tickrate;
    }
    
    /**
     * Sets the tickrate of the model.
     * @param tickrate : The new tickrate.
     */
    public void setTickrate(int tickrate) {
        this.tickrate = tickrate;
        restartTicking(); // Applicera ny tickrate
        notifyModelUpdated();
    }

    /**
     * Gets the current game state.
     * @return The current game state. E.g., "RUNNING", "PAUSED".
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * Sets the current game state and notifies listeners of the change.
     * @param gameState : The new game state.
     */
    public void setGameState(String gameState) {
        this.gameState = gameState;
        if ("PAUSED".equals(gameState)) {
            //stopTicking();
        } else if ("RUNNING".equals(gameState)) {
            //startTicking();
        } else if ("MAIN_MENU".equals(gameState)){

        }
        notifyGameStateChanged(gameState);
    }

    /**
     * Notifies all listeners that the game state has changed.
     * @param newState : The new game state.
     */
    protected void notifyGameStateChanged(String newState) {
        this.gameState = newState;
        for (ModelListener listener : listeners) {
            listener.onGameStateChanged(newState);
        }
        notifyEntitiesChanged();

    }

    /**
     * Adds a world to the model.
     * @param world : The world to add.
     */
    public void addWorld(World world) {
        this.worlds.add(world);
    }

    /**
     * Removes a world from the model.
     * @param world : The world to remove.
     */
    public void removeWorld(World world) {
        this.worlds.remove(world);
    }

    /**
     * Checks if the model is currently running.
     * @return true if the model is running, false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }
    public void newGame() throws IOException {
        removeWorld(worlds.getFirst());
        worlds.add(new World().withStartWorld());


        //Temporary loading of a starting save until world generation is implemented
        /*stopTicking();
        if (!worlds.isEmpty()) removeWorld(worlds.getFirst());
        World loadedWorld = SaveFileLoader.getInstance().load("START_WORLD");
        worlds.add(loadedWorld);
        notifyTilesetChanged();
        startTicking();*/
    }

    /**
     * Save the world into a json object
     */
    public void saveColony(){
        SaveFileCreator.getInstance().save(this, "SAVE");
    }
    public void loadColony() throws IOException {
        stopTicking();
        removeWorld(worlds.getFirst());
        World loadedWorld = SaveFileLoader.getInstance().load("SAVE");
        worlds.add(loadedWorld);
        notifyTilesetChanged();
        startTicking();
    }
    public int getStartingTickrate(){
        return startingTickrate;
    }
    public World getWorld(){
        return worlds.getFirst();
    }
}
