package controller.mouseTool;

import model.Entity;
import model.datastructures.Position;
import model.world.Item;
import model.world.MaterialType;
import model.world.Tile;
import model.world.World;

public class PlaceFood extends MouseTool{
    private static PlaceFood INSTANCE;
    private PlaceFood(){
        draggedTriggered = true;
        pressTriggered = true;
    }

    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();

        if (position.getX() > 1 && position.getX() < 98 && position.getY() > 1 && position.getY() < 98){
            if (world.getTileGrid()[x][y] == null && world.getEntityGrid()[x][y].isEmpty()) world.addEntity(new Item(position, MaterialType.FOOD));
        }
    }

    public static PlaceFood getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlaceFood();
        }
        return INSTANCE;
    }
}
