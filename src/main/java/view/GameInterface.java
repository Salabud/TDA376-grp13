package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

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

        font = "-fx-base: rgb(50, 41 ,47); -fx-padding: 0 0 0 0; -fx-font-size: 12px; -fx-font-family: 'Daydream';";

        nodes = new ArrayList<>();

        exitButton = new Button("Exit");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(0);
        exitButton.setPrefSize(80,40);
        exitButton.setFocusTraversable(false);
        exitButton.setStyle(font);
        nodes.add(exitButton);

        saveButton = new Button("Save");
        saveButton.setLayoutX(80);
        saveButton.setPrefSize(80,40);
        saveButton.setFocusTraversable(false);
        saveButton.setStyle(font);
        nodes.add(saveButton);

        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(280);
        pauseButton.setPrefWidth(100);
        pauseButton.setPrefHeight(40);
        pauseButton.setFocusTraversable(false);
        pauseButton.setStyle(font);
        nodes.add(pauseButton);

        speed1Button = new Button(">");
        speed1Button.setLayoutX(380);
        speed1Button.setPrefWidth(40);
        speed1Button.setPrefHeight(40);
        speed1Button.setFocusTraversable(false);
        speed1Button.setStyle(font);
        nodes.add(speed1Button);


        speed3Button = new Button(">>");
        speed3Button.setLayoutX(420);
        speed3Button.setPrefSize(40, 40);
        speed3Button.setFocusTraversable(false);
        speed3Button.setStyle(font);
        nodes.add(speed3Button);

        tools = new ComboBox<>();
        tools.setFocusTraversable(false);
        tools.setLayoutX(600);
        tools.setPrefSize(200,40);
        tools.getItems().addAll(Tool.values());
        tools.setValue(Tool.SELECT);
        tools.setStyle(font);
        nodes.add(tools);
        tools.setConverter(new StringConverter<>() {
            @Override
            public String toString(Tool tool) {
                return switch (tool) {
                    case SELECT -> "Select";
                    case PLACE_DIRT -> "Place dirt";
                    case SHOVEL -> "Dig";
                    case PLACE_FOOD -> "Place food";
                    case PLACE_POISON -> "Place poison";
                };
            }
            @Override
            public Tool fromString(String string) {
                return null; // not needed for ComboBox
            }
        });

        setPressedButton(">");
    }

    /**
     * sets which speed button should diplsay a different color to indicate that it is the currently selected speed.
     * @param button
     */
    public void setPressedButton(String button){
        switch(button){
            case ">":
                speed1Button.setStyle("-fx-base: rgb(25, 20 ,23); -fx-padding: 0 0 0 0; -fx-font-size: 25px;");
                speed3Button.setStyle(font);
                break;
            case ">>>":
                speed1Button.setStyle(font);
                speed3Button.setStyle("-fx-base: rgb(25, 20 ,23); -fx-padding: 0 0 0 0; -fx-font-size: 25px;");
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
    public Button getSpeed3Button(){
        return speed3Button;
    }
    public ComboBox<Tool> getTools(){
        return tools;
    }
    public List<Node> getNodes(){
        return nodes;
    }
    public String getFont(){
        return font;
    }


}
