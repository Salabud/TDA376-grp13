package view;

public class MetaDataRegistry {
    private static MetaDataRegistry INSTANCE;
    private int resolutionY;
    private int resolutionX;
    private double cellSize;
    private int squareOffset;
    private double zoom; // Current level of magnification/zoom
    private double cameraX; //X co-ord of the viewPort
    private double cameraY; //Y co-ord of the viewPort
    private double viewPortWidth; //Width of the current viewPort
    private double viewPortHeight; //Width of the current viewPort

    private MetaDataRegistry(){
        resolutionX = 800;
        resolutionY = 800;
        viewPortWidth = resolutionX;
        viewPortHeight = resolutionY;

        squareOffset = (resolutionY - resolutionX)/2;
        zoom =1;
        cellSize = ((double) resolutionX /100)*zoom;
        cameraX = 0;
        cameraY = 0;

    }

    public static MetaDataRegistry getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MetaDataRegistry();
        }
        return INSTANCE;
    }

    public int getGridSize() {
        return 100;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public double getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getSquareOffset() {
        return squareOffset;
    }

    public void setSquareOffset(int squareOffset) {
        this.squareOffset = squareOffset;
    }

    public double getZoom() {
        return zoom;

    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
        cellSize = (int)((resolutionX /100)*zoom);
    }

    public double getCameraX() {
        return cameraX;
    }

    public void setCameraX(double cameraX) {
        this.cameraX = cameraX;
    }

    public double getCameraY() {
        return cameraY;
    }

    public void setCameraY(double cameraY) {
        this.cameraY = cameraY;
    }

    public double getViewPortWidth() {
        return viewPortWidth;
    }

    public void setViewPortWidth(double viewPortWidth) {
        this.viewPortWidth = viewPortWidth;
    }

    public double getViewPortHeight() {
        return viewPortHeight;
    }

    public void setViewPortHeight(double viewPortHeight) {
        this.viewPortHeight = viewPortHeight;
    }
}
