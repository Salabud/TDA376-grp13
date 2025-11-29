package View;

import Controller.InputHandler;
import Model.ModelListener;
import Model.World.World;
import app.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

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
    private Pane mainPane;



    public View(Stage stage) {
        this.stage = stage;
        this.root = new BorderPane();

    }
    
    public void initialize() {
        // initialize the Pane that will be used for the main display
        mainPane = new Pane();

        // Create canvas components
        this.worldCanvas = new WorldCanvas();
        this.entityCanvas = new EntityCanvas();
        this.interfaceCanvas = new InterfaceCanvas();

        
        // Layout components
        loadMainMenu();
        root.setCenter(mainPane);
        // Add other canvases as needed
        
        // Create scene
        scene = new Scene(root,800,800);
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
    }

    /**
     * @param world the world that is to be rendered
     */
    @Override
    public void onTilesetChanged(World world) {
        // Update specific entity rendering
        if (worldCanvas != null) {
            worldCanvas.render(world);
        }
    }
    @Override
    public void onEntitiesChanged(World world) {
        // Update specific entity rendering
        if (entityCanvas != null) {
            entityCanvas.updateEntities(world.getEntities());
        }
    }
    
    @Override
    public void onGameStateChanged(String newState) {
        // Update UI based on game state
        if(newState.equals("MAIN_MENU")){
            loadMainMenu();
        } else if (newState.equals("RUNNING")) {
            loadRunningGame();
        }
        if (interfaceCanvas != null) {
            interfaceCanvas.updateGameState(newState);
        }
    }
    private void loadMainMenu(){
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(MainMenu.getInstance().getNodes());
        mainPane.setStyle(MainMenu.getInstance().getBackGroundColor());
    }
    private void loadRunningGame() {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(worldCanvas, entityCanvas);
        mainPane.getChildren().addAll(GameInterface.getInstance().getNodes());
    }

    public void renderInterface() {
        if (interfaceCanvas != null) {
            interfaceCanvas.render();
        }
    }
    
    public Stage getStage() {
        return stage;
    }
}
