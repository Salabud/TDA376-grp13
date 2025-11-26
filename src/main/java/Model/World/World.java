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

    public Item breakTile(Tile tile){
        return null; //TODO
    }

    public void addTile(Tile tile){
        //TODO
    }


    public void tick(){
        for (int x=0; x<entityGrid.length; x++){
            for (int y=0; y<entityGrid[x].length; y++){
                for (Entity entity : entityGrid[x][y]){
                    entity.update();
                }
            }
        }
    }
}

