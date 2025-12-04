package controller;

import controller.mouseTool.*;
import javafx.scene.control.ComboBox;
import model.Model;
import model.datastructures.Position;
import view.Tool;
import view.View;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.GameInterface;
/**
 * Controller class - Mediator between Model and View.
 * Follows Single Responsibility Principle, handles user input and coordinates actions.
 * Extensibly implements InputHandler (following the observer pattern) to process View events.
 */
public class GameInterfaceController implements InputHandler {
    private Model model;
    private View view;
    private GameInterface gameInterface;
    private boolean suppressFirstClick;
    private MouseTool currentTool;
    private boolean dragging;

    
    public GameInterfaceController(Model model, View view) {
        this.model = model;
        this.view = view;
        gameInterface = view.getGameInterface();
        setupButtonHandlers();
        suppressFirstClick = true;
        currentTool = null;
        dragging = false;
        currentTool = SelectTool.getInstance();

        // Connect controller to tools
        SelectTool.getInstance().setController(this);

    }

    /**
     * Handles key press events.
     * @param event : The key event to handle.
     */
    @Override
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                // Move camera up or handle up action
                break;
            case S:
            case DOWN:
                // Move camera down or handle down action
                break;
            case A:
            case LEFT:
                // Move camera left or handle left action
                break;
            case D:
            case RIGHT:
                // Move camera right or handle right action
                break;
            case SPACE:
                // Pause/Resume game
                togglePause();
                break;
            default:
                break;
        }
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
        double worldX = event.getX();
        double worldY = event.getY();

        int posX = (int)(worldX / 8);
        int posY = (int)(worldY / 8);
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
            gameInterface.getPauseButton().setStyle("-fx-base: rgb(25, 20 ,23); -fx-padding: 0 0 0 0; -fx-font-size: 25px;");
        }
        else{
            gameInterface.getPauseButton().setStyle(gameInterface.getFont());
            model.setGameState("RUNNING");
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
     * Additional controller methods for specific game actions.
     * These methods update the Model, which then notifies the View through observers.
     */
    
    public void startGame() {
        // model.start();
    }
    
    public void stopGame() {
        // model.stop();
    }
}
