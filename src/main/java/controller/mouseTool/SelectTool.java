package controller.mouseTool;

import controller.GameInterfaceController;
import model.Entity;
import model.datastructures.Position;
import model.world.World;

public class SelectTool extends MouseTool{
    private static SelectTool INSTANCE;
    private GameInterfaceController gameInterfaceController;
    private SelectTool(){
        clickTriggered = true;
    }

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
