package Model.Ants;

import Model.Entity;
import Model.World.World;

import java.util.Random;

public class Ant implements Entity {
    private int x;
    private int y;
    private Random ran;
    private World world;

    public Ant(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        ran = new Random();

    }

    @Override
    public void update() {
        int xDelta = ran.nextInt(3)-1;
        int yDelta = ran.nextInt(3)-1;
        int newX = x + (xDelta);
        int newY = y + (yDelta);
        if (newX < 0) newX = 0;
        if (newX > world.getGrid().length-1) newX = world.getGrid().length-1;
        if (newY < 0) newY = 0;
        if (newY > world.getGrid().length-1) newY = world.getGrid().length-1;


        if (world.getGrid()[newX][newY] == null){
            world.getGrid()[x][y] = null; //Remove this ant from its current position in the grid
            x = newX;
            y = newY;
            world.getGrid()[newX][newY] = this; //Assign this ant to the new position in the grid
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getType() {
        return "Ant";
    }
}
