package model;

public class EntityIdManager {
    private static EntityIdManager INSTANCE;
    private int nextId;

    private EntityIdManager(){
        nextId = 0;
    }

    public int getNextId(){
        return nextId++;
    }

    public static EntityIdManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new EntityIdManager();
        }
        return  INSTANCE;
    }
}
