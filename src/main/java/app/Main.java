package app;

import Controller.Controller;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static Thread gameThread;

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

        gameThread = new Thread() {
            @Override
            public void run(){
                try {
                    while(true){
                        Thread.sleep(600);
                        System.out.println("ticking...");
                    }
                } catch(InterruptedException e){

                }
            }
        };

        System.out.println("Starting program");
        gameThread.start();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
