package Model.Tasks;

import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

/**
 * Represents a task to be performed by an ant, following the command pattern.
 * E.g. ForageTask, ExpandNestTask, FeedQueenTask etc.
 */
public interface Task {
    void execute(TaskPerformerAnt ant); //Både WorkerAnt, QueenAnt och eventuellt SoldierAnt implementerar
    int getPriority();
    Position getTargetLocation();
    AntBehavior getBehaviorStrategy();
    AntMovement getMovementStrategy();
    boolean isComplete();
    String getDescription();
    boolean isAssigned();
    void setAssigned(boolean status);
}
