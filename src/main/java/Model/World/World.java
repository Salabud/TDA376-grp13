package Model.World;

import java.util.List;

import Model.Entity;

public class World {
    private List<Entity>[][] entityGrid;
    private List<Tile>[] tileGrid;
    

    World(){
        int gridSize = 100; //Temporary demo size
        this.entityGrid = new List[gridSize][gridSize];
        this.tileGrid = new List[gridSize];
    }

    public void addEntity(Entity entity){
        entityGrid[entity.getX()][entity.getY()].add(entity);
    }

    public void removeEntity(Entity entity) {
        entityGrid[entity.getX()][entity.getY()].remove(entity);
    }

    public void breakTile(Tile tile){
        // tileGrid[tile.getX()] MAKE INTO ITEM

    }


    public void tick(){
        }
    }
