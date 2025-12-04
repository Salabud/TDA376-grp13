package app;

import controller.GameInterfaceController;
import controller.MainMenuController;
import model.Model;
import model.world.World;
import view.View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

/**
 * Main application class, entry point for the Ant Simulator.
 * Sets up MVC components and starts the JavaFX application.
 */
public class Main extends Application {
    private Model model;

    @Override
    public void start(Stage stage) throws Exception {
        World world = new World();
        model = new Model();
        model.addWorld(world.withStartWorld());
        View view = new View(stage);
        GameInterfaceController gameInterfaceController = new GameInterfaceController(model, view);
        MainMenuController mainMenuController = new MainMenuController(model, view);
        model.addListener(view);
        view.setInputHandler(mainMenuController);
        view.initialize();
        model.initialise();
        stage.show();

        model.startTicking();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                view.renderInterface();
            }
        }.start();
    }

    @Override
    public void stop() throws Exception {
        if (model != null) {
            model.stopTicking();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
