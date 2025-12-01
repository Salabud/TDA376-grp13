package Controller;

import Model.Model;
import View.View;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Controller class - Mediator between Model and View.
 * Follows Single Responsibility Principle, handles user input and coordinates actions.
 * Extensibly implements InputHandler (following the observer pattern) to process View events.
 */
public class Controller implements InputHandler {
    private Model model;
    private View view;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupButtonHandlers();
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
        double worldX = event.getX();
        double worldY = event.getY();
        
        // Process click (e.g., select entity, place task, etc.)
        // model.handleWorldClick(worldX, worldY);
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
        view.speedButton.setOnAction(e -> handleSpeedButton());
    }

    /**
     * Handles world tick speed button toggle.
     */
    private void handleSpeedButton(){
        System.out.println("Click");
        if(view.speedButton.getText().equals("x3")){
            view.speedButton.setText("x1");
            model.setTickrate(20);
        }
        else {
            view.speedButton.setText("x3");
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
