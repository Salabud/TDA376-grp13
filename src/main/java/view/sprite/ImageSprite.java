package view.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import view.MetaDataRegistry;

public class ImageSprite implements Paintable{
    private double size;
    GraphicsContext gc;
    Image image;

    public ImageSprite(double size, GraphicsContext gc, Image image){
        this.image = image;
        this.gc = gc;
        this.size = size;
    }

    @Override
    public void paint(int x, int y) {
        double cellSize = MetaDataRegistry.getInstance().getCellSize();
        double centerOffsetX = (cellSize-size*MetaDataRegistry.getInstance().getCellSize())/2;
        double centerOffsetY = (cellSize-size*MetaDataRegistry.getInstance().getCellSize())/2;

        gc.drawImage(image,x+centerOffsetX,y+centerOffsetY, MetaDataRegistry.getInstance().getCellSize()*size, size*MetaDataRegistry.getInstance().getCellSize());
    }
}
