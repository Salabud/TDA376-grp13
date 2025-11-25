package Model.Tasks;

public interface Task {
    int getPriority();
    Position getTargetLocation();
    AntBehavior getBehaviorStrategy();
    AntMovement getMovementStrategy();
    boolean isComplete();
    void execute(Ant ant); //Både WorkerAnt, QueenAnt och eventuellt SoldierAnt implementerar
    String getDescription();
    boolean isAssigned();
}
