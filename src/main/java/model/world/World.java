package model.world;

import java.util.ArrayList;
import java.util.List;

import model.ants.*;
import model.colony.AntColony;
import model.colony.ColonyMediator;
import model.colony.ColonyTaskBoard;
import model.datastructures.Position;
import model.Entity;
import model.tasks.FeedQueenTask;
import model.tasks.TemporaryTestTask;

/**
 * Represents the world in which entities exist and interact.
 * Manages the grid of tiles and entities, and handles updates.
 */
public class World {
    private List<Entity> entities; // Current entities in the world, for easy iteration
    private List<Entity>[][] entityGrid;
    private Tile[][] tileGrid;
    private List<Tile> tiles; // Current tiles in the world, for easy rendering access
    private final int gridSize;
    private boolean tilesChanged;
    private ColonyMediator colonyMediator;
    private AntColony colony;
    private ColonyTaskBoard taskBoard;

    public World(){
        this.tilesChanged = false;
        this.gridSize = 100; //Temporary demo size
        this.entityGrid = new List[gridSize][gridSize];
        this.tileGrid = new Tile[gridSize][gridSize];
        this.entities = new ArrayList<>();
        this.tiles = new ArrayList<>();
        colonyMediator = new ColonyMediator();
        taskBoard = new ColonyTaskBoard();
        colony = new AntColony(colonyMediator, taskBoard);
        colonyMediator.setAntColony(colony);
        colonyMediator.setColonyTaskBoard(taskBoard);
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

        colonyMediator = new ColonyMediator();
        taskBoard = new ColonyTaskBoard();
        colony = new AntColony(colonyMediator, taskBoard);
        colonyMediator.setAntColony(colony);
        colonyMediator.setColonyTaskBoard(taskBoard);


        AntFactory factory = AntFactory.getInstance();
        TaskPerformerAnt ant1 = factory.createWorkerAnt(this, colony, 0, 30, 30, colonyMediator);
        TaskPerformerAnt ant2 = factory.createWorkerAnt(this, colony, 0, 60, 34, colonyMediator);
        QueenAnt queen = factory.createQueenAnt(this, colony, 0, 20, 60, colonyMediator);
        ant1.assignTask(new FeedQueenTask(queen));
        ant2.assignTask(new TemporaryTestTask());

        //Showcase entities
        //Item dirt = new Item(new Position(27, 24), MaterialType.DIRT);
        //addEntity(dirt);
        Item food = new Item(new Position(45, 24), MaterialType.FOOD);
        addEntity(food);
        Item food2 = new Item(new Position(46,25), MaterialType.FOOD);
        addEntity(food2);
        colony.addFoodPosition(new Position(46, 25));
        Larva larva1 = factory.createLarva(this, colony, 3,23,35,colonyMediator);

        tilesChanged = true;
        return this;
    }

    /**
     * Adds an entity to the world at its current position.
     * @param entity : The entity to be added.
     */
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
    public void removeTile(Position position){
        tiles.remove(tileGrid[position.getX()][position.getY()]);
        tileGrid[position.getX()][position.getY()] = null;
        tilesChanged = true;
    }

    /**
     * Adds a tile to the world at its position.
     * @param tile : The tile to be added.
     */
    public void addTile(Tile tile){
        tiles.add(tile);
        tileGrid[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
        tilesChanged = true;
    }

    public List<Entity> getEntities(){
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
    public void tick(){
        for (Entity entity : entities) {
            entity.update();
        }
    }

    /**
     * Gets the tile grid of the world.
     * @return The tile grid as Tile[][].
     */
    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    /**
     * Gets the list of tiles in the world. 
     * @return The list of tiles as List<Tile>.
     */
    public List<Tile> getTiles() {
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
    public ColonyMediator getColonyMediator(){
        return colonyMediator;
    }
    public ColonyTaskBoard getTaskBoard(){
        return taskBoard;
    }
}
