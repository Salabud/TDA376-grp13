package View;

import Model.Entity;
import Model.ModelListener;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * View class - Observer of Model, displays game state.
 * Follows Single Responsibility Principle - handles rendering only.
 * Implements ModelListener to observe model changes.
 */
public class View implements ModelListener {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private InputHandler inputHandler;
    
    // Canvas components for rendering
    private WorldCanvas worldCanvas;
    private EntityCanvas entityCanvas;
    private InterfaceCanvas interfaceCanvas;
    
    public View(Stage stage) {
        this.stage = stage;
        this.root = new BorderPane();
    }
    
    /**
     * Initialize the view and set up the scene.
     */
    public void initialize() {
        // Create canvas components
        this.worldCanvas = new WorldCanvas();
        this.entityCanvas = new EntityCanvas();
        this.interfaceCanvas = new InterfaceCanvas();
        
        // Layout components
        root.setCenter(worldCanvas);
        // Add other canvases as needed
        
        // Create scene
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Ant Simulator");
        
        // Set up input handlers if controller is registered
        setupInputHandlers();
    }
    
    /**
     * Set the input handler (typically the Controller).
     * Follows Dependency Inversion - depends on InputHandler interface.
     */
    public void setInputHandler(InputHandler handler) {
        this.inputHandler = handler;
        if (scene != null) {
            setupInputHandlers();
        }
    }
    
    private void setupInputHandlers() {
        if (inputHandler != null && scene != null) {
            scene.setOnKeyPressed(inputHandler::handleKeyPress);
            scene.setOnMouseClicked(inputHandler::handleMouseClick);
            scene.setOnMouseMoved(inputHandler::handleMouseMove);
        }
    }
    
    // ModelListener implementation - Observer pattern
    @Override
    public void onModelUpdated() {
        // Refresh the entire view
        render();
    }
    
    @Override
    public void onEntitiesChanged() {
        // Update specific entity rendering
        if (entityCanvas != null) {
            entityCanvas.updateEntities();
        }
    }
    
    @Override
    public void onGameStateChanged(String newState) {
        // Update UI based on game state
        if (interfaceCanvas != null) {
            interfaceCanvas.updateGameState(newState);
        }
    }
    
    /**
     * Main rendering method.
     */
    private void render() {
        if (worldCanvas != null) {
            worldCanvas.render();
        }
        if (entityCanvas != null) {
            entityCanvas.render();
        }
        if (interfaceCanvas != null) {
            interfaceCanvas.render();
        }
    }
    
    public Stage getStage() {
        return stage;
    }
}
