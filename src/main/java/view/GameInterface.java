package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class GameInterface {
    private List<Node> nodes;

    private Button exitButton;
    private Button saveButton;
    private Button speed1Button;
    private Button speed2Button;
    private Button speed3Button;
    private Button pauseButton;
    private ComboBox<Tool> tools;
    private String font;

    public GameInterface(){
        font = "-fx-base: rgb(50, 41 ,47); -fx-padding: -10 0 0 0; -fx-font-family: 'Daydream'; -fx-font-size: 15px;";

        nodes = new ArrayList<>();

        exitButton = new Button(" Exit ");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(0);
        exitButton.setFocusTraversable(false);
        exitButton.setStyle(font);
        nodes.add(exitButton);

        saveButton = new Button(" Save ");
        saveButton.setLayoutX(70);
        saveButton.setFocusTraversable(false);
        saveButton.setStyle(font);
        nodes.add(saveButton);

        pauseButton = new Button(" Pause ");
        pauseButton.setLayoutX(300);
        pauseButton.setPrefWidth(100);
        pauseButton.setFocusTraversable(false);
        pauseButton.setStyle(font);
        nodes.add(pauseButton);

        speed1Button = new Button(">");
        speed1Button.setLayoutX(400);
        speed1Button.setMinWidth(40);
        speed1Button.setFocusTraversable(false);
        speed1Button.setStyle(font);
        nodes.add(speed1Button);


        speed2Button = new Button(">>");
        speed2Button.setLayoutX(440);
        speed2Button.setMinWidth(40);
        speed2Button.setFocusTraversable(false);
        speed2Button.setStyle(font);
        nodes.add(speed2Button);

        speed3Button = new Button(">>>");
        speed3Button.setLayoutX(480);
        speed3Button.setMinWidth(40);
        speed3Button.setFocusTraversable(false);
        speed3Button.setStyle(font);
        nodes.add(speed3Button);

        tools = new ComboBox<>();
        tools.setFocusTraversable(false);
        tools.setLayoutX(550);
        tools.getItems().addAll(Tool.values());
        tools.setValue(Tool.SELECT);
        tools.setStyle(font);
        nodes.add(tools);

        setPressedButton(">");
    }

    /**
     * sets which speed button should diplsay a different color to indicate that it is the currently selected speed.
     * @param button
     */
    public void setPressedButton(String button){
        switch(button){
            case ">":
                speed1Button.setStyle("-fx-base: rgb(180,180,180);");
                speed2Button.setStyle("");
                speed3Button.setStyle("");
                break;
            case ">>":
                speed1Button.setStyle("");
                speed2Button.setStyle("-fx-base: rgb(180,180,180);");
                speed3Button.setStyle("");
                break;
            case ">>>":
                speed1Button.setStyle("");
                speed2Button.setStyle("");
                speed3Button.setStyle("-fx-base: rgb(180,180,180);");
                break;
        }
    }
    public Button getPauseButton(){
        return  pauseButton;
    }
    public Button getExitButton(){
        return exitButton;
    }
    public Button getSaveButton(){
        return saveButton;
    }
    public Button getSpeed1Button(){
        return speed1Button;
    }
    public Button getSpeed2Button(){
        return speed2Button;
    }
    public Button getSpeed3Button(){
        return speed3Button;
    }
    public List<Node> getNodes(){
        return nodes;
    }


}
