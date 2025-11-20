package View;

import Model.Entity;
import Model.World;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    World world;
    int cellSize = 10;
    int gridStrokeSize = 1;

    public GameCanvas(World world,double width, double height) {
        super(width, height);
        this.world = world;
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();

        // Clear the screen
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw grid lines
        gc.setStroke(Color.SADDLEBROWN.deriveColor(0,1,0.8,1));
        gc.setLineWidth(gridStrokeSize);

        for (double x = 0; x < getWidth(); x += cellSize) {
            gc.strokeLine(x, 0, x, getHeight()); // vertical lines
        }

        for (double y = 0; y < getHeight(); y += cellSize) {
            gc.strokeLine(0, y, getWidth(), y); // horizontal lines
        }


        // Draw your objects here
        for (Entity entity : world.getEntities()){
            double cellX = entity.getX()*10;
            double cellY = entity.getY()*10;
            if (entity.getType().equals("Ant")){
                gc.setFill(Color.ORANGE);
                double circleSize = (cellSize*0.8);
                double offset = (cellSize-circleSize) / 2;
                //System.out.println("cellX: " + cellX + ", cellY: " + cellY + ", circleSize: " + circleSize + ", offset: " + offset);
                gc.fillOval(cellX+offset,cellY+offset,circleSize,circleSize);
            } else if (entity.getType().equals("Wall")) {
                gc.setFill(Color.DARKSLATEGRAY);
                double padding = gridStrokeSize; // or slightly more if you like
                double rectSize = cellSize - 2 * padding;
                gc.fillRect(cellX + 0.5, cellY + 0.5, cellSize-1, cellSize-1);
            }

        }

    }
}