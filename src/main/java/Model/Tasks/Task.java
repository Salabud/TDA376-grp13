package Model.Tasks;

import Model.Ants.Behavior.AntBehavior;
import Model.Ants.Movement.AntMovement;
import Model.Ants.TaskPerformerAnt;
import Model.Datastructures.Position;

public interface Task {
    int getPriority();
    Position getTargetLocation();
    AntBehavior getBehaviorStrategy();
    AntMovement getMovementStrategy();
    boolean isComplete();
    void execute(TaskPerformerAnt ant); //Både WorkerAnt, QueenAnt och eventuellt SoldierAnt implementerar
    String getDescription();
    boolean isAssigned();
}
