package controller.mouseTool;

import controller.Controller;
import model.Entity;
import model.datastructures.Position;
import model.world.World;

public class SelectTool extends MouseTool{
    private static SelectTool INSTANCE;
    private Controller controller;
    private SelectTool(){
        clickTriggered = true;
    }

    @Override
    public void execute(World world, Position position){
        int x = position.getX();
        int y = position.getY();
        System.out.println("Select click");
        Entity ent;
        if (!world.getEntityGrid()[x][y].isEmpty()) {
            ent = world.getEntityGrid()[x][y].getFirst();
            System.out.println("Clicked " + ent.getEntityId() + " " + ent);
            controller.selectEntity(ent.getEntityId());
        } else {
            controller.selectEntity(-1);
        }


    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public static SelectTool getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SelectTool();
        }
        return INSTANCE;
    }
}
