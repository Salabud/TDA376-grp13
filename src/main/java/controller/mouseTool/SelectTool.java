
/**
 * Mouse tool for selecting entities in the world via the game interface.
 * Implements singleton pattern.
 */
package controller.mouseTool;

import controller.GameInterfaceController;
import model.entity.Entity;
import model.datastructures.Position;
import model.world.World;

public class SelectTool extends MouseTool{
    private static SelectTool INSTANCE;
    private GameInterfaceController gameInterfaceController;
    private SelectTool(){
        clickTriggered = true;
    }

    /**
     * Selects the entity at the given position, or deselects if none is present.
     * @param world the world to act upon
     * @param position the position to select
     */
    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();
        Entity ent;
        if (!world.getEntityGrid()[x][y].isEmpty()) {
            ent = world.getEntityGrid()[x][y].getFirst();
            gameInterfaceController.selectEntity(ent.getEntityId());
        } else {
            gameInterfaceController.selectEntity(-1);
        }
    }

    /**
     * Sets the controller to be used for selection actions.
     * @param gameInterfaceController the controller to set
     */
    public void setController(GameInterfaceController gameInterfaceController){
        this.gameInterfaceController = gameInterfaceController;
    }

    public static SelectTool getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SelectTool();
        }
        return INSTANCE;
    }
}
