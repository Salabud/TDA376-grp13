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

        font = "-fx-base: rgb(50, 41 ,47); -fx-padding: 0 0 0 0; -fx-font-size: 25px; -fx-font-family: 'Segoe UI Emoji';";

        nodes = new ArrayList<>();
        //▶️⏩️🔍
        //🪏⛰️

        exitButton = new Button("✖\uFE0F");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(0);
        exitButton.setPrefSize(40,40);
        exitButton.setFocusTraversable(false);
        exitButton.setStyle(font);
        nodes.add(exitButton);

        saveButton = new Button("\uD83D\uDCBE");
        saveButton.setLayoutX(60);
        saveButton.setPrefSize(40,40);
        saveButton.setFocusTraversable(false);
        saveButton.setStyle(font);
        nodes.add(saveButton);

        pauseButton = new Button("⏯️");
        pauseButton.setLayoutX(340);
        pauseButton.setPrefWidth(40);
        pauseButton.setPrefHeight(40);
        pauseButton.setFocusTraversable(false);
        pauseButton.setStyle(font);
        nodes.add(pauseButton);

        speed1Button = new Button("▶\uFE0F");
        speed1Button.setLayoutX(380);
        speed1Button.setPrefWidth(40);
        speed1Button.setPrefHeight(40);
        speed1Button.setFocusTraversable(false);
        speed1Button.setStyle(font);
        nodes.add(speed1Button);


        speed3Button = new Button("⏩\uFE0F");
        speed3Button.setLayoutX(420);
        speed3Button.setPrefSize(40, 40);
        speed3Button.setFocusTraversable(false);
        speed3Button.setStyle(font);
        nodes.add(speed3Button);

        tools = new ComboBox<>();
        tools.setFocusTraversable(false);
        tools.setLayoutX(650);
        tools.setPrefSize(150,40);
        tools.getItems().addAll(Tool.values());
        tools.setValue(Tool.SELECT);
        tools.setStyle(font);
        nodes.add(tools);
        tools.setConverter(new StringConverter<>() {
            @Override
            public String toString(Tool tool) {
                return switch (tool) {
                    case SELECT -> "\uD83D\uDD0D";
                    case PLACE_DIRT -> "⛰\uFE0F";
                    case SHOVEL -> "\uD83E\uDE8F";
                    case PLACE_FOOD -> "\uD83C\uDF4E";
                    case PLACE_POSION -> "☣\uFE0F";
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
