package controller.mouseTool;

import model.datastructures.Position;
import model.world.MaterialType;
import model.world.Tile;
import model.world.World;

public class PlaceDirt extends MouseTool{
    private static PlaceDirt INSTANCE;
    private PlaceDirt(){
        draggedTriggered = true;
        pressTriggered = true;
    }

    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();

        if (position.getX() > 1 && position.getX() < 98 && position.getY() > 1 && position.getY() < 98){
            if (world.getTileGrid()[x][y] == null && world.getEntityGrid()[x][y].isEmpty()) world.addTile(new Tile(position, MaterialType.DIRT));
            if (world.getTileGrid()[x-1][y] == null && world.getEntityGrid()[x-1][y].isEmpty()) world.addTile(new Tile(x-1,y,MaterialType.DIRT));
            if (world.getTileGrid()[x+1][y] == null && world.getEntityGrid()[x+1][y].isEmpty()) world.addTile(new Tile(x+1, y, MaterialType.DIRT));
            if (world.getTileGrid()[x][y-1] == null && world.getEntityGrid()[x][y-1].isEmpty()) world.addTile(new Tile(x, y-1, MaterialType.DIRT));
            if (world.getTileGrid()[x][y+1] == null && world.getEntityGrid()[x][y+1].isEmpty()) world.addTile(new Tile(x, y+1, MaterialType.DIRT));
        }
    }

    public static PlaceDirt getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlaceDirt();
        }
        return INSTANCE;
    }

}
