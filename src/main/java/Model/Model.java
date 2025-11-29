package Model;

import Model.World.World;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Model {
    private int tickrate;
    private List<World> worlds;
    private List<ModelListener> listeners;
    private String gameState;
    private ScheduledExecutorService tickExecutor;
    private boolean isRunning;
    
    public Model() {
        this.worlds = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.tickrate = 60;
        this.gameState = "MAIN_MENU";
        this.isRunning = false;
    }

    public void update() {
        if(gameState.equals("RUNNING")){
            for (World world : worlds) world.tick();
        }

        notifyEntitiesChanged();
        notifyTilesetChanged();
    }

    public void startTicking() {
        if (isRunning) return;
        isRunning = true;
        tickExecutor = Executors.newSingleThreadScheduledExecutor();
        tickExecutor.scheduleWithFixedDelay(() -> {
            Platform.runLater(this::update);
        }, 0, tickrate, TimeUnit.MILLISECONDS);
    }

    public void stopTicking() {
        isRunning = false;
        if (tickExecutor != null && !tickExecutor.isShutdown()) {
            tickExecutor.shutdownNow();
        }
    }

    // Använd detta för att starta om tick-loopen när tickrate ändras
    private void restartTicking() {
        if (isRunning) {
            stopTicking();
            startTicking();
        }
    }
    
    public void addListener(ModelListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    
    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }
    
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
    
    protected void notifyEntitiesChanged() {
        for (ModelListener listener : listeners) {
            listener.onEntitiesChanged(worlds.getFirst()); //Refactor when we are handling multiple worlds
        }
    }
    
    public int getTickrate() {
        return tickrate;
    }
    
    public void setTickrate(int tickrate) {
        this.tickrate = tickrate;
        restartTicking(); // Applicera ny tickrate
        notifyModelUpdated();
    }
    
    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
        if ("PAUSED".equals(gameState)) {
            stopTicking();
        } else if ("RUNNING".equals(gameState)) {
            startTicking();
        }
        notifyGameStateChanged(gameState);
    }

    protected void notifyGameStateChanged(String newState) {
        this.gameState = newState;
        for (ModelListener listener : listeners) {
            listener.onGameStateChanged(newState);
        }
        notifyEntitiesChanged();

    }

    public void addWorld(World world) {
        this.worlds.add(world);
    }

    public void removeWorld(World world) {
        this.worlds.remove(world);
    }

    public boolean isRunning() {
        return isRunning;
    }
    public void newGame(){
        worlds.clear();
        worlds.add(new World());

    }
}
