package Model.Ants.Movement;

import Model.Ants.Ant;

public class RandomMovement implements AntMovement {
    @Override
    public void move(Ant ant) {

        int x = ant.getX();
        int y = ant.getY();
        int randomNum = (int)(Math.random()*4);
        switch (randomNum) {
            case 0:
                //move north
                ant.getPosition().move(0, 1);
                break;
            case 1:
                //move east
                ant.getPosition().move(1, 0);
                break;
            case 2:
                //move south
                ant.getPosition().move(0, -1);
                break;
            case 3:
                //move west
                ant.getPosition().move(-1, 0);
        }

    }
}
