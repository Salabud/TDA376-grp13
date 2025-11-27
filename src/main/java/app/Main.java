package app;

import Controller.Controller;
import Model.Model;
import Model.World.World;
import View.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main application entry point that wires up the MVC architecture.
 * Follows SOLID principles by keeping components decoupled through observer pattern.
 */
public class Main extends Application {
    private ScheduledExecutorService modelExecutor;

    @Override
    public void start(Stage stage) throws Exception {

        //Hardcoded stuff and things
        World world = new World();

        // Create Model (Subject in Observer pattern)
        Model model = new Model();
        model.addWorld(world);
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

        // Model tickrate: update simulation at fixed delay (no overlap)
        modelExecutor = Executors.newSingleThreadScheduledExecutor();
        modelExecutor.scheduleWithFixedDelay(() -> {
            Platform.runLater(model::update); // Ensure thread safety with JavaFX
        }, 0, model.getTickrate(), TimeUnit.MILLISECONDS);

        // UI tickrate: AnimationTimer for smooth rendering
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                view.renderInterface();
            }
        }.start();
    }

    @Override
    public void stop() throws Exception {
        if (modelExecutor != null && !modelExecutor.isShutdown()) {
            modelExecutor.shutdownNow();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
