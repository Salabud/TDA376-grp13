
/**
 * Mouse tool for removing tiles in a cross pattern (shovel action).
 * Implements singleton pattern.
 */
package controller.mouseTool;

import model.datastructures.Position;
import model.world.World;

public class Shovel extends MouseTool{
    private static Shovel INSTANCE;

    public Shovel(){
        draggedTriggered = true;
        pressTriggered = true;
    }

    /**
     * Removes tiles in a cross pattern centered on the given position.
     * @param world the world to act upon
     * @param position the center position for the shovel action
     */
    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();

        if (position.getX() > 1 && position.getX() < 98 && position.getY() > 1 && position.getY() < 98){
            world.removeTile(position);
            world.removeTile(new Position(x-1,y));
            world.removeTile(new Position(x+1,y));
            world.removeTile(new Position(x, y-1));
            world.removeTile(new Position(x,y+1));
        }

    }

    public static Shovel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Shovel();
        }
        return INSTANCE;
    }
}
