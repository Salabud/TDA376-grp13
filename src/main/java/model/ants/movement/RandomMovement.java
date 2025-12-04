package model.ants.movement;

import model.ants.Ant;
import model.datastructures.Position;

/**
 * Implements a movement strategy where the ant moves in a random direction each update.
 * This movement never completes, the ant wanders indefinitely.
 */
public class RandomMovement implements AntMovement {
    
    @Override
    public void move(Ant ant) {
        int randomNum = (int)(Math.random() * 4);
        switch (randomNum) {
            case 0 -> ant.setPosition(new Position(ant.getX(), ant.getY() + 1));  // north
            case 1 -> ant.setPosition(new Position(ant.getX() + 1, ant.getY()));  // east
            case 2 -> ant.setPosition(new Position(ant.getX(), ant.getY() - 1));  // south
            case 3 -> ant.setPosition(new Position(ant.getX() - 1, ant.getY()));  // west
        }
    }
    
    @Override
    public boolean isComplete() {
        // Random movement never completes, ant wanders forever
        return false;
    }
}
