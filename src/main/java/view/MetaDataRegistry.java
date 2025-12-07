package view;

public class MetaDataRegistry {
    private static MetaDataRegistry INSTANCE;
    private int screenWidth;
    private int screenHeight;
    private int cellsize;
    private int squareOffset;

    private MetaDataRegistry(){
        screenHeight = 800;
        screenWidth = 800;
        cellsize = screenHeight/100;
        squareOffset = (screenWidth-screenHeight)/2;

    }

    public static MetaDataRegistry getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MetaDataRegistry();
        }
        return INSTANCE;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getCellsize() {
        return cellsize;
    }

    public void setCellsize(int cellsize) {
        this.cellsize = cellsize;
    }

    public int getSquareOffset() {
        return squareOffset;
    }

    public void setSquareOffset(int squareOffset) {
        this.squareOffset = squareOffset;
    }
}
