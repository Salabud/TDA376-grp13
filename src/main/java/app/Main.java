package app;

import Controller.Controller;
import Model.Model;
import Model.World.World;
import View.View;
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
        model.addWorld(world);
        View view = new View(stage);
        Controller controller = new Controller(model, view);
        model.addListener(view);
        view.setInputHandler(controller);
        view.initialize();
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
