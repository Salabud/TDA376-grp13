package view;

import javafx.scene.paint.Color;
/*
This class is redundant at the moment
 */

public class SpriteRegistry {
    private static SpriteRegistry INSTANCE;
    double cellsize;
    public final Sprite workerAnt;
    public final Sprite Larva;
    public final Sprite Food;
    public final Sprite Queen;
    public SpriteRegistry(){
        cellsize = 10;
        workerAnt = new Sprite(cellsize*0.8, cellsize*0.8, Color.ORANGE);
        Larva = new Sprite(cellsize*0.8, cellsize*0.8, Color.WHITE);
        Food = new Sprite(cellsize*0.8, cellsize*0.8, Color.RED);
        Queen = new Sprite(cellsize*0.8, cellsize*0.8, Color.YELLOW);
    }

    public static SpriteRegistry getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SpriteRegistry();
        }
        return INSTANCE;
    }
}
