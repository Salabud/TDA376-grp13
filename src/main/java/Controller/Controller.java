package Controller;

import Model.Model;
import View.View;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import View.GameInterface;

/**
 * Controller class - Mediator between Model and View.
 * Follows Single Responsibility Principle - handles user input and coordinates actions.
 * Implements InputHandler to process View events.
 */
public class Controller implements InputHandler {
    private Model model;
    private View view;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupButtonHandlers();
    }
    
    // InputHandler implementation
    @Override
    public void handleKeyPress(KeyEvent event) {
        // Process keyboard input and update model accordingly
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
    
    @Override
    public void handleMouseClick(MouseEvent event) {
        // Convert screen coordinates to world coordinates
        double worldX = event.getX();
        double worldY = event.getY();
        
        // Process click (e.g., select entity, place task, etc.)
        // model.handleWorldClick(worldX, worldY);
    }
    
    @Override
    public void handleMouseMove(MouseEvent event) {
        // Handle mouse movement (e.g., hover effects)
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
        GameInterface.getInstance().getSpeedButton().setOnAction(e -> handleSpeedButton());
        GameInterface.getInstance().getExitButton().setOnAction(e -> handleExitButton());
        }

    private void handleExitButton() {
        model.setGameState("MAIN_MENU");
    }

    private void handleSpeedButton(){
        Button btn = GameInterface.getInstance().getSpeedButton();
        System.out.println("Click");
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
