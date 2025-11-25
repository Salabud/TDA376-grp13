import Controller.Controller;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application entry point that wires up the MVC architecture.
 * Follows SOLID principles by keeping components decoupled through observer pattern.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Create Model (Subject in Observer pattern)
        Model model = new Model();
        
        // Create View (Observer of Model)
        View view = new View(stage);
        
        // Create Controller (Mediator between View and Model)
        Controller controller = new Controller(model, view);
        
        // Register View as observer of Model (Dependency Inversion - depends on interface)
        model.addListener(view);
        
        // Register Controller as input handler for View
        view.setInputHandler(controller);
        
        // Initialize and show the view
        view.initialize();
        stage.show();
        
        // Start the model (game loop, etc.)
        model.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
