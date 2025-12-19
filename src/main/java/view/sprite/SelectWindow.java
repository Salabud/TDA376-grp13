package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.entity.being.Being;
import model.entity.Entity;
import model.entity.being.ants.Ant;
import model.entity.item.Item;

/**
 * SelectWindow is responsible for displaying information about a selected entity.
 */
public class SelectWindow{
    private Entity entity;
    private final GraphicsContext gc;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public SelectWindow(int x, int y, GraphicsContext gc){
        this.x = x;
        this.y = y;
        width = 160;
        height = 200;
        this.gc = gc;
        Font.loadFont(getClass().getResourceAsStream("/fonts/PKMN RBYGSC.ttf"), 60);
    }
    public void paint(Entity entity){
        this.entity = entity;
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(x,y,width,height,15,15);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("PKMN RBYGSC", 14));
        switch (entity.getType()){
            case BEING -> {
                Being being = (Being) entity;
                switch (being.getBeingType()){
                    case ANT -> {
                        Ant ant = (Ant) being;
                        gc.fillText(ant.getAntType().toString(),x+10,y+30);
                        gc.fillText("HUNGER: " + (int)ant.getHunger(), x+10,y+60);
                        gc.fillText("HEALTH: " + (int)ant.getHealth() + "/" + (int)ant.getMaxHealth(), x+10,y+90);
                        gc.fillText("AGE: " + (int)ant.getAge() + " days",x+10,y+120);
                        gc.fillText("ID: " + ant.getColonyId(), x+10, y+150);
                    }
                }
            }
            case ITEM -> {
                Item item = (Item) entity;
                gc.fillText(item.getMaterialType().toString(),x+10,y+30);
                gc.fillText("is scouted: " + item.isScouted(),x+10,y+50);
            }
        }
    }
}
