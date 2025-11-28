package Model.World;

import java.util.ArrayList;
import java.util.List;

import Model.Ants.AntFactory;
import Model.Ants.Larva;
import Model.Ants.TaskPerformerAnt;
import Model.Colony.AntColony;
import Model.Colony.ColonyMediator;
import Model.Colony.ColonyTaskBoard;
import Model.Entity;
import Model.Tasks.TemporaryTestTask;

public class World {
    private List<Entity> entities;
    private List<Entity>[][] entityGrid;
    private Tile[][] tileGrid;
    private List<Tile> tiles;
    private final int gridSize;    

    public World(){
        this.gridSize = 100; //Temporary demo size
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

        Tile tile1 = new Tile(24, 29, MaterialType.DIRT);
        addTile(tile1);

        //Showcase entities
        Item dirt = new Item(20,20, MaterialType.DIRT);
        addEntity(dirt);
        Item food = new Item(22,20,MaterialType.FOOD);
        addEntity(food);
        Larva larva1 = factory.createLarva(this, colony, 3,24,20,mediator);


        for (int x = 20; x < 100; x++){
            for (int y = 50; y < 70; y++){
                Tile tile = new Tile(x,y,MaterialType.DIRT);
                tiles.add(tile);
                tileGrid[x][y] = tile;
            }
        }
        for (int x = 20; x < 70; x++){
            for (int y = 30; y < 40; y++){
                Tile tile = new Tile(x,y,MaterialType.DIRT);
                tiles.add(tile);
                tileGrid[x][y] = tile;
            }
        }
    }

    public void addEntity(Entity entity){
        int x = entity.getX();
        int y = entity.getY();
        if (inBounds(x, y)) {
            entityGrid[x][y].add(entity);
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        int x = entity.getX();
        int y = entity.getY();
        if (inBounds(x, y)) {
            entityGrid[x][y].remove(entity);
            entities.remove(entity);
        }
    }

    public Item killEntity(Entity entity){
        removeEntity(entity);
        return new Item(null, MaterialType.CORPSE);
    }

    public void breakTile(Tile tile){
        int x = tile.getPosition().getX();
        int y = tile.getPosition().getY();
        if (inBounds(x, y) && tileGrid[x][y] == tile) {
            tileGrid[x][y] = null;
            //return new Item(null, tile.getMaterialType()); TODO: Tile hanterar sin övergång till item
        }
    }

    public void addTile(Tile tile, int x, int y, MaterialType materialType){
        tile = new Tile(x, y, materialType);
        if (inBounds(x, y) && tileGrid[x][y] == null) {
          tiles.add(tile);
          tileGrid[x][y] = tile;
        }
    }
    public void addTile(Tile tile){
        tiles.add(tile);
        tileGrid[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    private boolean inBounds(int x, int y) {
        boolean withinGrid = x >= 0 && x < gridSize && y >= 0 && y < gridSize;
        return withinGrid;
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
  
    public Tile getTile(int x, int y) {
      if (inBounds(x, y)) {
          return tileGrid[x][y];
      }
      return null;
    }
}
