package model.ants.creation;

import model.EntityIdManager;
import model.ants.Ant;
import model.ants.Larva;
import model.ants.QueenAnt;
import model.ants.WorkerAnt;
import model.colony.AntColony;
import model.colony.events.ColonyEvent;
import model.colony.events.ColonyEventListener;
import model.colony.events.LarvaBirthEvent;
import model.colony.events.LarvaTransformEvent;
import model.datastructures.Position;
import model.world.EntityRegistry;
import model.world.World;

/**
 * Service responsible for spawning ants and registering them with the world and colony.
 * Follows Single Responsibility Principle by centralizing all spawn/registration logic.
 * 
 * <p>Listens to spawn-related events:
 * <ul>
 *   <li>{@link LarvaBirthEvent} - spawns a new larva when queen finishes birthing</li>
 *   <li>{@link LarvaTransformEvent} - transforms a larva into a worker ant</li>
 * </ul>
 */
public class AntSpawner implements ColonyEventListener {
    
    private EntityRegistry world;
    private AntColony antColony;
    private ColonyEventListener eventListener;
    
    /**
     * Sets the world where spawned ants will be added.
     * @param world : the world
     */
    public void setWorld(EntityRegistry world) {
        this.world = world;
    }
    
    /**
     * Sets the colony where spawned ants will be registered.
     * @param antColony : the ant colony
     */
    public void setAntColony(AntColony antColony) {
        this.antColony = antColony;
    }
    
    /**
     * Sets the event listener that spawned ants will subscribe to.
     * Typically this is the ColonyMediator.
     * @param listener : the event listener
     */
    public void setEventListener(ColonyEventListener listener) {
        this.eventListener = listener;
    }
    
    @Override
    public void onColonyEvent(ColonyEvent event) {
        switch (event) {
            case LarvaBirthEvent birthEvent -> handleLarvaBirth(birthEvent);
            case LarvaTransformEvent transformEvent -> handleLarvaTransform(transformEvent);
            default -> { }
        }
    }
    
    /**
     * Handles the birth of a new larva.
     * Creates the larva and registers it with world and colony.
     */
    private void handleLarvaBirth(LarvaBirthEvent event) {
        spawnLarva(event.getBirthPosition());
    }
    
    /**
     * Handles the transformation of a larva into a worker ant.
     * Removes the larva and spawns a worker in its place.
     */
    private void handleLarvaTransform(LarvaTransformEvent event) {
        Larva larva = event.getLarva();
        transformLarvaToWorker(larva);
    }
    
    /**
     * Spawns a new larva at the given position.
     * Handles ID assignment, event subscription, and registration.
     * 
     * @param position : the position to spawn the larva
     * @return the spawned larva
     */
    public Larva spawnLarva(Position position) {
        Larva larva = AntFactory.getInstance().createLarva(position);
        registerAnt(larva);
        return larva;
    }
    
    /**
     * Spawns a new worker ant at the given position.
     * Handles ID assignment, event subscription, and registration.
     * 
     * @param position : the position to spawn the worker
     * @return the spawned worker ant
     */
    public WorkerAnt spawnWorkerAnt(Position position) {
        WorkerAnt worker = AntFactory.getInstance().createWorkerAnt(position);
        registerAnt(worker);
        return worker;
    }
    
    /**
     * Spawns a new queen ant at the given position.
     * Handles ID assignment, event subscription, and registration.
     * 
     * @param position : the position to spawn the queen
     * @return the spawned queen ant
     */
    public QueenAnt spawnQueenAnt(Position position) {
        QueenAnt queen = AntFactory.getInstance().createQueenAnt(position);
        registerAnt(queen);
        return queen;
    }
    
    /**
     * Transforms a larva into a worker ant.
     * Removes the larva and spawns a worker with the same event listeners.
     * 
     * @param larva : the larva to transform
     * @return the new worker ant
     */
    public WorkerAnt transformLarvaToWorker(Larva larva) {
        // Create new worker from larva (preserves position and some state)
        WorkerAnt worker = AntFactory.getInstance().createWorkerFromLarva(larva);
        worker.setEntityId(EntityIdManager.getInstance().getNextId());
        
        // Copy event listeners from larva
        for (ColonyEventListener listener : larva.getEventListeners()) {
            worker.addEventListener(listener);
        }
        
        // Remove larva from world and colony
        world.removeEntity(larva);
        antColony.removeAnt(larva);
        
        // Add worker to world and colony
        world.addEntity(worker);
        antColony.addAnt(worker);
        
        return worker;
    }
    
    /**
     * Registers an ant with ID, event listener, world, and colony.
     * @param ant : the ant to register
     */
    private void registerAnt(Ant ant) {
        ant.setEntityId(EntityIdManager.getInstance().getNextId());
        if (eventListener != null) {
            ant.addEventListener(eventListener);
        }
        // Also listen to this spawner for transform events
        ant.addEventListener(this);
        world.addEntity(ant);
        antColony.addAnt(ant);
    }
}
