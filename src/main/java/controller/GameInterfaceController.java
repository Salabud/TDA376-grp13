package controller;

import controller.mouseTool.*;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import model.Model;
import model.ModelListener;
import model.datastructures.Position;
import model.world.World;
import view.MetaDataRegistry;
import view.Tool;
import view.View;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.GameInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller class - Mediator between Model and View.
 * Follows Single Responsibility Principle, handles user input and coordinates actions.
 * Extensibly implements InputHandler (following the observer pattern) to process View events.
 */
public class GameInterfaceController implements InputHandler, ModelListener {
    private Model model;
    private View view;
    private GameInterface gameInterface;
    private boolean suppressFirstClick;
    private MouseTool currentTool;
    private boolean dragging;
    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();
    private Set<KeyCode> keysPressed;
    
    public GameInterfaceController(Model model, View view) {
        this.model = model;
        this.view = view;
        gameInterface = view.getGameInterface();
        setupButtonHandlers();
        suppressFirstClick = true;
        currentTool = null;
        dragging = false;
        currentTool = SelectTool.getInstance();
        keysPressed = new HashSet<>();

        // Connect controller to tools
        SelectTool.getInstance().setController(this);

    }

    /**
     * Handles key press events.
     * @param event : The key event to handle.
     */
    public void handleKeyPress(KeyEvent event) {
        keysPressed.add(event.getCode());

        switch (event.getCode()) {
            case UP:
                pan(0,50);
                break;
            case DOWN:
                pan(0,-50);
                break;
            case LEFT:
                pan(50,0);
                break;
            case RIGHT:
                pan(-50,0);
                break;
            case Z:
                zoom(metaData.getZoom()+0.2);
                break;
            case X:
                zoom(metaData.getZoom()-0.2);
                break;
            case SPACE:
                // Pause/Resume game
                togglePause();
                break;
            default:
                break;
        }
    }
    public void handleKeyRelease(KeyEvent event) {
        //keysPressed.remove(event.getCode());
    }

    /**
     * Handles mouse click events.
     * @param event : The mouse event to handle.
     */
    @Override
    public void handleMouseClick(MouseEvent event) {
        if(suppressFirstClick){
            suppressFirstClick = false;
        } else {
            System.out.println("CLICK on scene");
            if (currentTool.isClickTriggered()) applyTool(event);
        }
    }
    @Override
    public void handleMousePressed(MouseEvent event) {
        if (suppressFirstClick) {
            suppressFirstClick = false;
            return;
        }
        dragging = true;
        if (currentTool.isPressTriggered()) applyTool(event);
    }
    public void handleMouseDragged(MouseEvent event) {
        if (dragging && currentTool.isDraggedTriggered()) {
            applyTool(event);
        }
    }

    public void handleMouseReleased(MouseEvent event) {
        dragging = false;
    }
    /**
     * Handles mouse move events (for e.g. hover effects).
     * @param event : The mouse event to handle.
     */
    @Override
    public void handleMouseMove(MouseEvent event) {
        double worldX = event.getX();
        double worldY = event.getY();
        
        // Update hover state if needed
    }

    private void applyTool(MouseEvent event) {
        double worldX = event.getX() - metaData.getSquareOffset() - metaData.getCameraX();
        double worldY = event.getY() - metaData.getCameraY();

        int posX = (int)(worldX / (metaData.getCellSize()));
        int posY = (int)(worldY / (metaData.getCellSize()));
        Position mousePosition = new Position(posX, posY);

        currentTool.execute(model.getWorld(), mousePosition);
    }

    /**
     * Toggle game pause state.
     */
    private void togglePause() {
        String currentState = model.getGameState();
        if ("RUNNING".equals(currentState)) {
            // Pause game logic here
            // model.setGameState("PAUSED");

        } else if ("PAUSED".equals(currentState)) {

            // Resume game logic here
            // model.setGameState("RUNNING");
        }
    }

    private void setupButtonHandlers(){
        gameInterface.getSpeed1Button().setOnAction(e -> handleSpeed1Button());
        gameInterface.getSpeed3Button().setOnAction(e -> handleSpeed3Button());
        gameInterface.getExitButton().setOnAction(e -> handleExitButton());
        gameInterface.getSaveButton().setOnAction(e -> handleSaveButton());
        gameInterface.getPauseButton().setOnAction(e -> handlePauseButton());

        ComboBox<Tool> toolCombo = gameInterface.getTools();
        toolCombo.valueProperty().addListener((obs, oldTool, newTool) -> {
          if (newTool != null){
              switch(newTool){
                  case PLACE_DIRT -> currentTool = PlaceDirt.getInstance();
                  case SHOVEL -> currentTool = Shovel.getInstance();
                  case PLACE_FOOD ->  currentTool = PlaceFood.getInstance();
                  case SELECT -> currentTool = SelectTool.getInstance();
                  case PLACE_POISON ->  currentTool = PlacePoison.getInstance();
              }
          }
        });
        }


    //TODO Implement
    private void handleSaveButton() {
        model.saveColony();
    }

    private void handlePauseButton() {
        if (model.getGameState().equals("RUNNING")){
            model.setGameState("PAUSED");
            gameInterface.getPauseButton().setStyle("-fx-base: rgb(25, 20 ,23);  -fx-padding: 0 0 0 0; -fx-font-size: 12px; -fx-font-family: 'Daydream';");
        }
        else{
            gameInterface.getPauseButton().setStyle(gameInterface.getFont());
            model.setGameState("RUNNING");
            suppressFirstClick = true;
        }

    }

    private void handleExitButton() {
        model.setGameState("MAIN_MENU");
    }

    /**
     * Handles world tick speed button toggles.
     */
    private void handleSpeed1Button(){
        model.setTickrate(60);
        gameInterface.setPressedButton(">");
    }
    private void handleSpeed3Button(){
        model.setTickrate(20);
        gameInterface.setPressedButton(">>>");
    }

    public void selectEntity(int entityId){
        view.setSelectedEntity(entityId);
    }


    /**
     * Change the level of zoom in the simulation
     * @param zoom
     */
    public void zoom(double zoom){
        double oldZoom = metaData.getZoom();
        double cameraX = metaData.getCameraX();
        double cameraY = metaData.getCameraY();
        double screenWidth = metaData.getResolutionY()/zoom;
        double screenHeight = metaData.getResolutionX()/zoom;


        metaData.setZoom(zoom); //zoom is a double between 1 and 2 (2 = 2x zoom)

        // adjust camera so same world point remains centered
        //metaData.setCameraX((int) (cameraX-(screenWidth*zoom -screenWidth)/2));
        //metaData.setCameraY((int) (cameraY-(screenHeight*zoom -screenHeight)/2));
        view.updateZoom();
    }
    public void pan(int x, int y){
        metaData.setCameraX(metaData.getCameraX()+x);
        metaData.setCameraY(metaData.getCameraY()+y);
        view.updateZoom();
    }




    /**
     * Additional controller methods for specific game actions.
     * These methods update the Model, which then notifies the View through observers.
     */
    
    public void startGame() {
        // model.start();
    }
    
    public void stopGame() {
        // model.stop();
    }

    @Override
    public void onModelUpdated() {

    }

    @Override
    public void onTick() {
        //Does not work for some reason. Threading?
        //System.out.println(Arrays.toString(keysPressed.toArray()));
        if (keysPressed.contains(KeyCode.DOWN))  pan(0, 4);
        if (keysPressed.contains(KeyCode.DOWN))  pan(0, -4);
        if (keysPressed.contains(KeyCode.LEFT))  pan(4, 0);
        if (keysPressed.contains(KeyCode.RIGHT)) pan(-4, 0);

    }

    @Override
    public void onTilesetChanged(World world) {

    }

    @Override
    public void onGameStateChanged(String newState) {

    }
}
