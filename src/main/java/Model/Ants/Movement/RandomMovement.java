package Model.Ants.Movement;

import Model.Ants.Ant;
import Model.Datastructures.Position;

/**
 * Implements a movement strategy where the ant moves in a random direction each update.
 */
public class RandomMovement implements AntMovement {
    @Override
    public void move(Ant ant) {

        int x = ant.getX();
        int y = ant.getY();
        int randomNum = (int)(Math.random()*4);
        switch (randomNum) {
            case 0:
                //move north
                ant.setPosition(new Position(ant.getX(), ant.getY()+1));
                break;
            case 1:
                //move east
                ant.setPosition(new Position(ant.getX()+1, ant.getY()));
                break;
            case 2:
                //move south
                ant.setPosition(new Position(ant.getX(), ant.getY()-1));
                break;
            case 3:
                //move west
                ant.setPosition(new Position(ant.getX()-1, ant.getY()));
        }

    }
}
