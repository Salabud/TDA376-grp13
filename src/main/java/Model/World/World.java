package Model.World;

import java.util.ArrayList;
import java.util.List;

import Model.Ants.AntFactory;
import Model.Ants.TaskPerformerAnt;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Colony.ColonyTaskBoard;
import Model.Entity;
import Model.Tasks.TemporaryTestTask;

public class World {
    private List<Entity>[][] entityGrid;
    private List<Entity> entities;
    private Tile[][] tileGrid;
    private List<Tile> tiles;

    public World(){
        int gridSize = 100; //Temporary demo size
        this.entityGrid = new List[gridSize][gridSize];
        this.tileGrid = new Tile[gridSize][gridSize];
        this.entities = new ArrayList<>();
        this.tiles = new ArrayList<>();
        for (int x=0; x<gridSize; x++) {
            for (int y=0; y < gridSize; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }

        //Hardcoded starting world
        ColonyMediator mediator = new ColonyMediator();
        ColonyTaskBoard taskBoard = new ColonyTaskBoard();
        AntColony colony = new AntColony(mediator, taskBoard);
        
        AntFactory factory = AntFactory.getInstance();
        TaskPerformerAnt ant1 = factory.createWorkerAnt(this, colony, 0, 0, 0, mediator);
        TaskPerformerAnt ant2 = factory.createWorkerAnt(this, colony, 0, 79, 0, mediator);
        ant1.assignTask(new TemporaryTestTask());
        ant2.assignTask(new TemporaryTestTask());

        Tile tile1 = new Tile(70, 50, MaterialType.DIRT);
        tiles.add(tile1);

        for (int x = 20; x < 100; x++){
            for (int y = 50; y < 70; y++){
                Tile tile = new Tile(x,y,MaterialType.DIRT);
                tiles.add(tile);
                tileGrid[x][y] = tile;
            }
        }
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
        return null; //TODO: same as below
    }

    public void addTile(Tile tile, int x, int y, MaterialType materialType){
        tile = new Tile(x, y, materialType);
        tiles.add(tile);
        tileGrid[x][y] = tile;
    }

    public List<Entity> getEntities(){
        return this.entities;
    }

    public void tick(){
        // Iterate over entities list, not entityGrid, since entities can move
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}

