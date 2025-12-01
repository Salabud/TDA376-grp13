package View;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class GameInterface {
    private List<Node> nodes;

    private Button exitButton;
    private Button speedButton;
    private Button pauseButton;

    public GameInterface(){
        nodes = new ArrayList<>();

        exitButton = new Button("Exit");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(0);
        exitButton.setFocusTraversable(false);
        nodes.add(exitButton);

        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(300);
        pauseButton.setPrefWidth(100);
        pauseButton.setFocusTraversable(false);
        nodes.add(pauseButton);

        speedButton = new Button("x3");
        speedButton.setLayoutX(400);
        speedButton.setFocusTraversable(false);
        nodes.add(speedButton);
    }
    public Button getPauseButton(){
        return  pauseButton;
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

}
