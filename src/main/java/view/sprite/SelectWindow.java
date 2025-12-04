package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Being;
import model.Entity;
import model.ants.Ant;
import model.world.Item;

public class SelectWindow{
    private Entity entity;
    private GraphicsContext gc;
    private int x;
    private int y;
    private int width;
    private int height;

    public SelectWindow(GraphicsContext gc){
        x = 640;
        y = 600;
        width = 160;
        height = 200;
        this.gc = gc;
        Font.loadFont(getClass().getResourceAsStream("/fonts/Daydream.otf"), 40);


    }
    public void paint(Entity entity){
        this.entity = entity;
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(x,y,width,height,15,15);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Daydream"));
        switch (entity.getType()){
            case BEING -> {
                Being being = (Being) entity;
                switch (being.getBeingType()){
                    case ANT -> {
                        Ant ant = (Ant) being;
                        gc.fillText(ant.getAntType().toString(),x+10,y+30);
                        gc.fillText("Hunger: " + (int)ant.getHunger(), x+10,y+60);
                        gc.fillText("Health: " + (int)ant.getHealth() + "/" + (int)ant.getMaxHealth(), x+10,y+90);
                        gc.fillText("Age: " + (int)ant.getAge() + " days",x+10,y+120);
                        gc.fillText("ID: " + ant.getColonyId(), x+10, y+150);
                    }
                }
            }
            case ITEM -> {
                Item item = (Item) entity;
                gc.fillText(item.getMaterialType().toString(),x+10,y+30);
            }
        }
    }
}
