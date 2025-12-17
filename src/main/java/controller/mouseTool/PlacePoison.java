
/**
 * Mouse tool for placing poison items in the world.
 * Implements singleton pattern.
 */
package controller.mouseTool;

import model.datastructures.Position;
import model.entity.item.Item;
import model.world.MaterialType;
import model.world.World;

public class PlacePoison extends MouseTool{
    /** Singleton instance. */
    private static PlacePoison INSTANCE;

    private PlacePoison(){
        draggedTriggered = true;
        pressTriggered = true;
    }

    /**
     * Places a poison item at the given position if the tile and entity grid are empty.
     * @param world the world to act upon
     * @param position the position to place poison
     */
    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();

        if (position.getX() > 1 && position.getX() < 98 && position.getY() > 1 && position.getY() < 98){
            if (world.getTileGrid()[x][y] == null && world.getEntityGrid()[x][y].isEmpty()) {
                world.addEntity(new Item(world, position, MaterialType.POISON));
            }
        }
    }

    public static PlacePoison getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlacePoison();
        }
        return INSTANCE;
    }
}
