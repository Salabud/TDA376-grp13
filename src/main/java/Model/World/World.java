package Model.World;

import java.util.ArrayList;
import java.util.List;

import Model.Ants.Ant;
import Model.Ants.WorkerAnt;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Colony.ColonyTaskBoard;
import Model.Entity;
import Model.EntityType;

public class World {
    private List<Entity>[][] entityGrid;
    private List<Entity> entities;
    private List<Tile>[] tileGrid;
    private List<Tile> tiles;
    

    public World(){
        int gridSize = 100; //Temporary demo size
        this.entityGrid = new List[gridSize][gridSize];
        this.tileGrid = new List[gridSize];
        this.entities = new ArrayList<>();
        this.tiles = new ArrayList<>();
        for (int x=0; x<entityGrid.length; x++) {
            for (int y=0; y < entityGrid[x].length; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }

        //Hardcoded starting world
        ColonyMediator mediator = new ColonyMediator();
        ColonyTaskBoard taskBoard = new ColonyTaskBoard();
        AntColony colony = new AntColony(mediator, taskBoard);
        Ant ant1 = new WorkerAnt(EntityType.WORKER_ANT,this, 0, 50, 50, mediator);
        Tile tile1 = new Tile(70, 50, MaterialType.DIRT);
        tiles.add(tile1);
        colony.addAnt(ant1);
        addEntity(ant1);
    }

    public void addEntity(Entity entity){
        entityGrid[entity.getX()][entity.getY()].add(entity);
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entityGrid[entity.getX()][entity.getY()].remove(entity);
        entities.remove(entity);
    }

    public Item breakTile(Tile tile){
        return null; //TODO
    }

    public void addTile(Tile tile){
        //TODO
    }

    public List<Entity> getEntities(){
        return this.entities;
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

    public List<Tile>[] getTileGrid() {
        return tileGrid;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}

