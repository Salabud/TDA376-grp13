package model.world;

import java.util.ArrayList;
import java.util.List;

import model.ants.*;
import model.ants.creation.AntFactory;
import model.colony.AntColony;
import model.colony.HiveMind;
import model.colony.ColonyTaskBoard;
import model.datastructures.Position;
import model.Entity;
import model.EntityIdManager;

/**
 * Represents the world in which entities exist and interact.
 * Manages the grid of tiles and entities, and handles updates.
 */
public class World implements EntityRegistry, TileRegistry, Tickable{
    private List<Entity> entities; // Current entities in the world, for easy iteration
    private List<Entity>[][] entityGrid;
    private Tile[][] tileGrid;
    private List<Tile> tiles; // Current tiles in the world, for easy rendering access
    private boolean tilesChanged;

    private final int gridSize;

    private HiveMind hiveMind;
    private AntColony colony;
    private ColonyTaskBoard taskBoard;

    public World(){
        this.tilesChanged = false;
        this.gridSize = 100; //Temporary demo size
        this.entityGrid = new List[gridSize][gridSize];
        this.tileGrid = new Tile[gridSize][gridSize];
        this.entities = new ArrayList<>();
        this.tiles = new ArrayList<>();
        hiveMind = new HiveMind();
        taskBoard = new ColonyTaskBoard();
        colony = new AntColony(hiveMind, taskBoard);
        hiveMind.setAntColony(colony);
        hiveMind.setColonyTaskBoard(taskBoard);
        for (int x=0; x<gridSize; x++) {
            for (int y=0; y < gridSize; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }
    }
  
    public World withStartWorld(){
        //Hardcoded starting world
        for (int x = 0; x < 100; x++){
                Tile tile = new Tile(x,20,MaterialType.GRASS);
                tiles.add(tile);
                tileGrid[x][20] = tile;
        }
        for (int x = 0; x < 100; x++){
            for (int y = 21; y < 100; y++){
                Tile tile = new Tile(x,y,MaterialType.DIRT);
                tiles.add(tile);
                tileGrid[x][y] = tile;
            }
        }

        for (int x = 20; x < 90; x++){
            for (int y = 50; y < 70; y++){
                tiles.remove(tileGrid[x][y]);
                tileGrid[x][y] = null;
            }
        }
        for (int x = 20; x < 70; x++){
            for (int y = 30; y < 40; y++){
                tiles.remove(tileGrid[x][y]);
                tileGrid[x][y] = null;
            }
        }
        for (int x = 43; x < 47; x++){
            for (int y = 19; y < 60; y++){
                tiles.remove(tileGrid[x][y]);
                tileGrid[x][y] = null;
            }
        }

        hiveMind = new HiveMind();
        taskBoard = new ColonyTaskBoard();
        colony = new AntColony(hiveMind, taskBoard);
        hiveMind.setAntColony(colony);
        hiveMind.setColonyTaskBoard(taskBoard);

        AntFactory factory = AntFactory.getInstance();
        
        // Create and register worker ants
        WorkerAnt ant1 = factory.createWorkerAnt(new Position(30, 30));
        ant1.setEntityId(EntityIdManager.getInstance().getNextId());
        ant1.addEventListener(hiveMind);
        addEntity(ant1);
        colony.addAnt(ant1);
        
        WorkerAnt ant2 = factory.createWorkerAnt(new Position(35, 30));
        ant2.setEntityId(EntityIdManager.getInstance().getNextId());
        ant2.addEventListener(hiveMind);
        addEntity(ant2);
        colony.addAnt(ant2);
        
        // Create and register queen
        QueenAnt queen = factory.createQueenAnt(new Position(20, 60));
        queen.setEntityId(EntityIdManager.getInstance().getNextId());
        queen.addEventListener(hiveMind);
        addEntity(queen);
        colony.addAnt(queen);
        
        Item food = new Item(this, new Position(45, 24), MaterialType.FOOD);
        Item food2 = new Item(this, new Position(46,25), MaterialType.FOOD);
        Item food3 = new Item(this, new Position(45,25), MaterialType.FOOD);
        Item food4 = new Item(this, new Position(43,23), MaterialType.FOOD);
        Item food5 = new Item(this, new Position(43,25), MaterialType.FOOD);
        Item food6 = new Item(this, new Position(43,26), MaterialType.FOOD);
        Item food7 = new Item(this, new Position(43,27), MaterialType.FOOD);
        addEntity(food);
        addEntity(food2);
        addEntity(food3);
        addEntity(food4);
        addEntity(food5);
        addEntity(food6);
        addEntity(food7);
        colony.addKnownFood(food);
        colony.addKnownFood(food2);
        colony.addKnownFood(food3);
        colony.addKnownFood(food4);
        colony.addKnownFood(food5);
        colony.addKnownFood(food6);
        colony.addKnownFood(food7);
        
        // Create and register larva
        Larva larva1 = factory.createLarva(new Position(23, 35));
        larva1.setEntityId(EntityIdManager.getInstance().getNextId());
        larva1.addEventListener(hiveMind);
        addEntity(larva1);
        colony.addAnt(larva1);
        
        tilesChanged = true;
        return this;
    }

    /**
     * Adds an entity to the world at its current position.
     * @param entity : The entity to be added.
     */
    @Override
    public void addEntity(Entity entity){
        int x = entity.getX();
        int y = entity.getY();
        if (inBounds(x, y)) {
            entityGrid[x][y].add(entity);
            entities.add(entity);
        }
    }

    /**
     * Removes an entity from the world.
     * @param entity : The entity to be removed.
     */
    @Override
    public void removeEntity(Entity entity) {
        int x = entity.getX();
        int y = entity.getY();
        if (inBounds(x, y)) {
            entityGrid[x][y].remove(entity);
            entities.remove(entity);
        }
    }

    /**
     * We might not need this tbh.
     */
    public void killEntity(Entity entity){
        removeEntity(entity);
    }

    /**
     * Breaks a tile into an item and removes it from the world.
     * TODO: Might need to follow single responsibility principle here.
     * @param tile : The tile to be broken.
     */
    public void breakTile(Tile tile){
        int x = tile.getPosition().getX();
        int y = tile.getPosition().getY();
        if (inBounds(x, y) && tileGrid[x][y] == tile) {
            tileGrid[x][y] = null;
            tilesChanged = true;
            //return new Item(null, tile.getMaterialType()); TODO: Tile hanterar sin övergång till item
        }
    }

    /**
     * Creates and adds a tile to the world at specified position if empty.
     * @param tile : The tile to be added.
     * @param x : The x-coordinate of the tile.
     * @param y : The y-coordinate of the tile.
     * @param materialType : The material type of the tile.
     */
    public void addTile(Tile tile, int x, int y, MaterialType materialType){
        tile = new Tile(x, y, materialType);
        if (inBounds(x, y) && tileGrid[x][y] == null) {
          tiles.add(tile);
          tileGrid[x][y] = tile;
          tilesChanged = true;
        }
    }

    /**
     * Remove a tile from the world (without turning into an item)
     * @param position the position of the tile to be removed
     */
    @Override
    public void removeTile(Position position){
        tiles.remove(tileGrid[position.getX()][position.getY()]);
        tileGrid[position.getX()][position.getY()] = null;
        tilesChanged = true;
    }

    /**
     * Adds a tile to the world at its position.
     * @param tile : The tile to be added.
     */
    @Override
    public void addTile(Tile tile){
        tiles.add(tile);
        tileGrid[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
        tilesChanged = true;
    }

    @Override
    public List<Entity> getEntityList(){
        return entities;
    }

    public List<Entity>[][] getEntityGrid(){
        return entityGrid;
    }

    /**
     * Checks if the given coordinates are within the bounds of the world grid.
     * @param x : The x-coordinate to check.
     * @param y : The y-coordinate to check.
     * @return True if the coordinates are within bounds, false otherwise.
     */
    private boolean inBounds(int x, int y) {
        boolean withinGrid = x >= 0 && x < gridSize && y >= 0 && y < gridSize;
        return withinGrid;
    }

    /**
     * Updates all entities in the world one simulation tick.
     */
    @Override
    public void tick(){
        hiveMind.update();
        for (Entity entity : entities) {
            WorldContext worldContext = new WorldContext(entities,entityGrid,tiles,tileGrid,gridSize);
            entity.update();
            entity.setWorldContext(worldContext);
        }
    }

    /**
     * Gets the tile grid of the world.
     * @return The tile grid as Tile[][].
     */
    @Override
    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    /**
     * Gets the list of tiles in the world. 
     * @return The list of tiles as List<Tile>.
     */
    @Override
    public List<Tile> getTileList() {
        return tiles;
    }
  
    /**
     * Gets the tile at the specified coordinates.
     * @param x : The x-coordinate of the tile.
     * @param y : The y-coordinate of the tile.
     * @return The tile at the specified coordinates, or null if out of bounds.
     */
    public Tile getTile(int x, int y) {
      if (inBounds(x, y)) {
          return tileGrid[x][y];
      }
      return null;
    }
    public boolean checkTilesChanged(){
        return tilesChanged;
    }
    public void setTilesChanged(boolean bool){
        this.tilesChanged = bool;
    }

    public AntColony getAntColony() { return colony;
    }
    public HiveMind getColonyMediator(){
        return hiveMind;
    }
    public ColonyTaskBoard getTaskBoard(){
        return taskBoard;
    }
}
