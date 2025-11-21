package Model.Ants;

import Model.Colony.AntColony;
import Model.World.World;

public final class AntFactory {
    private static AntFactory INSTANCE;

    private AntFactory(){
    }

    public static AntFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AntFactory();
        }
        return INSTANCE;
    }

    public void createWorkerAnt(World world, AntColony colony, int x, int y, int age){

    }
    public void createLarva(World world, AntColony colony, int x, int y, int age){

    }
    public void createQueenAnt(World world, AntColony colony, int x, int y, int age){

    }

}

