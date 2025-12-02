package controller;

import model.Model;
import model.world.MaterialType;
import model.world.Tile;
import view.View;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.GameInterface;
//Ludvig ---- I would like to rename this class to GameInterfaceController. We need separate controllers for different game states, so I'm not sure what "Controller.java" would be used for
/**
 * Controller class - Mediator between Model and View.
 * Follows Single Responsibility Principle, handles user input and coordinates actions.
 * Extensibly implements InputHandler (following the observer pattern) to process View events.
 */
public class Controller implements InputHandler {
    private Model model;
    private View view;
    private GameInterface gameInterface;
    private boolean suppressFirstClick;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        gameInterface = view.getGameInterface();
        setupButtonHandlers();
        suppressFirstClick = true;

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
            // Convert screen coordinates to world coordinates
            double worldX = event.getX();
            double worldY = event.getY();


            //Testing stuff, not meant to stick around
            int intX = (int)(worldX/8);
            int intY = (int)(worldY/8);
            //model.getWorld().addTile(new Tile(intX, intY, MaterialType.DIRT)); //clicking places dirt
            for (int i = intX-1; i < intX + 2; i++){
                for (int a = intY-1; a < intY + 2; a++){
                    model.getWorld().addTile(new Tile(i, a, MaterialType.DIRT)); //clicking places dirt
                }
            }

        }
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
        gameInterface.getSpeedButton().setOnAction(e -> handleSpeedButton());
        gameInterface.getExitButton().setOnAction(e -> handleExitButton());
        gameInterface.getPauseButton().setOnAction(e -> handlePauseButton());
        }

    private void handlePauseButton() {
        Button btn = gameInterface.getPauseButton();
        if(btn.getText().equals("Pause")){
            model.setGameState("PAUSED");
            btn.setText("Resume");
        } else {
            model.setGameState("RUNNING");
            btn.setText("Pause");
        }

    }

    private void handleExitButton() {
        model.setGameState("MAIN_MENU");
    }

    /**
     * Handles world tick speed button toggle.
     */
    private void handleSpeedButton(){
        Button btn = gameInterface.getSpeedButton();
        if(btn.getText().equals("x3")){
            btn.setText("x1");
            model.setTickrate(20);
        }
        else {
            btn.setText("x3");
            model.setTickrate(60);
        }

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
