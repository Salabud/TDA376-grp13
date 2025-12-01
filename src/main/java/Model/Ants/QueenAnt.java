package Model.Ants;

import Model.Tasks.Task;

/** Represents the queen ant in the simulation. */
public class QueenAnt extends TaskPerformerAnt {
  
    @Override
    public void update() {
        if (currentTask != null) {
            currentTask.execute(this);
        }
    }

    public void layLarva(int amount){

    }
}
