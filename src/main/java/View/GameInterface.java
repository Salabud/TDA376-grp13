package View;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class GameInterface {
    private static GameInterface INSTANCE;
    private List<Node> nodes;

    private Button exitButton;
    private Button speedButton;

    private GameInterface(){
        nodes = new ArrayList<>();

        exitButton = new Button("Exit");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(0);
        nodes.add(exitButton);

        speedButton = new Button("x3");
        speedButton.setLayoutX(400);
        nodes.add(speedButton);
    }
    public Button getExitButton(){
        return exitButton;
    }
    public Button getSpeedButton(){
        return speedButton;
    }
    public List<Node> getNodes(){
        return nodes;
    }

    public static GameInterface getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GameInterface();
        }
        return INSTANCE;
    }
}
