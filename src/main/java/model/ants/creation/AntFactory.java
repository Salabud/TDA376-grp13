package model.ants.creation;

import model.ants.Larva;
import model.ants.QueenAnt;
import model.ants.WorkerAnt;
import model.ants.status.Status;
import model.colony.events.ColonyEventListener;
import model.datastructures.Position;

import java.util.List;

/**
 * Singleton Factory for creating/loading Ant instances.
 * The factory is responsible ONLY for object creation. Registration with
 * World, Colony, or Mediator handled somewhere else (e.g., via AntSpawner).
 */
public final class AntFactory {
    private static AntFactory INSTANCE;

    private AntFactory() {}

    // Singleton stuff
    public static AntFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AntFactory();
        }
        return INSTANCE;
    }

    /** Creates a new WorkerAnt at the given position with default stats. */
    public WorkerAnt createWorkerAnt(Position position) {
        // We don't need builder here since no overrides are applied!
        return new WorkerAnt(position);
    }

    /** 
     * Transforms a Larva into a WorkerAnt, preserving relevant state.
     * The new WorkerAnt inherits the larva's position, nickname, health, hunger, statuses, and event listeners.
     */
    public WorkerAnt createWorkerFromLarva(Larva larva) {
        WorkerAnt worker = new AntBuilder()
                .position(larva.getPosition())
                .nickname(larva.getNickname())
                .health(larva.getHealth())
                .hunger(larva.getHunger())
                .statuses(larva.getStatuses())
                .buildWorkerAnt();
        
        // Copy event listeners so the spawner/mediator continues to receive events
        for (ColonyEventListener listener : larva.getEventListeners()) {
            worker.addEventListener(listener);
        }
        return worker;
    }

    /** 
     * Loads a WorkerAnt from saved state. 
     * TODO: pass in AntData as ONE parameter
     * */
    public WorkerAnt loadWorkerAnt(Position position, int age, String nickname,
                                   float health, float maxHealth,
                                   float hunger, float maxHunger,
                                   int movementInterval, List<Status> statuses) {
        return new AntBuilder()
                .position(position)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildWorkerAnt();
    }

    /** Creates a new QueenAnt at the given position with default stats. */
    public QueenAnt createQueenAnt(Position position) {
        return new QueenAnt(position);
    }

    /** Loads a QueenAnt from saved state.  
    * TODO: pass in AntData as ONE parameter
    */
    public QueenAnt loadQueenAnt(Position position, int age, String nickname,
                                 float health, float maxHealth,
                                 float hunger, float maxHunger,
                                 int movementInterval, List<Status> statuses) {
        return new AntBuilder()
                .position(position)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildQueenAnt();
    }

    /** Creates a new Larva at the given position with default stats. */
    public Larva createLarva(Position position) {
        return new Larva(position);
    }

    /** Loads a Larva from saved state.
     * Loads a Larva from saved state.
     * TODO: pass in AntData as ONE parameter
     */
    public Larva loadLarva(Position position, int age, String nickname,
                           float health, float maxHealth,
                           float hunger, float maxHunger,
                           int movementInterval, List<Status> statuses) {
        return new AntBuilder()
                .position(position)
                .age(age)
                .nickname(nickname)
                .health(health)
                .maxHealth(maxHealth)
                .hunger(hunger)
                .maxHunger(maxHunger)
                .movementInterval(movementInterval)
                .statuses(statuses)
                .buildLarva();
    }
}

