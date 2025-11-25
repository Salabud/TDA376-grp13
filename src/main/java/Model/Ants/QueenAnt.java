package Model.Ants;

import Model.TaskPerformer;
import Model.Tasks.Task;

public class QueenAnt extends Ant implements TaskPerformer {
    Task currentTask;
    @Override
    public void update() {
        if (currentTask != null) {
            currentTask.execute(this);
        }
    }
    public void layLarva(int amount){

    }
}
