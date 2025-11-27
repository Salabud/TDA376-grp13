package Controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Interface for handling user input from the View.
 * Follows Interface Segregation Principle.
 * Allows Controller to handle inputs without knowing View implementation details.
 */
public interface InputHandler {
    /**
     * Handle keyboard input.
     */
    void handleKeyPress(KeyEvent event);
    
    /**
     * Handle mouse click input.
     */
    void handleMouseClick(MouseEvent event);
    
    /**
     * Handle mouse movement input.
     */
    void handleMouseMove(MouseEvent event);
}
