package controller.mouseTool;

import model.datastructures.Position;
import model.world.Item;
import model.world.MaterialType;
import model.world.World;

public class PlacePoison extends MouseTool{
private static PlacePoison INSTANCE;

    private PlacePoison(){
        draggedTriggered = true;
        pressTriggered = true;
    }

    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();

        if (position.getX() > 1 && position.getX() < 98 && position.getY() > 1 && position.getY() < 98){
            if (world.getTileGrid()[x][y] == null && world.getEntityGrid()[x][y].isEmpty()) world.addEntity(new Item(position, MaterialType.POISON));
        }
    }

    public static PlacePoison getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlacePoison();
        }
        return INSTANCE;
    }
}
