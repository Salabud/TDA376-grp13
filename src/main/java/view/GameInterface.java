package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * GameInterface manages the UI components of the game interface.
 */
public class GameInterface {
    private final List<Node> nodes;

    private final Button exitButton;
    private final Button saveButton;
    private final Button speed1Button;
    private Button speed2Button;
    private final Button speed3Button;
    private final Button pauseButton;
    private final ComboBox<Tool> tools;
    private final String font;
    private final MetaDataRegistry metaData;
    private final Image shovelImage;
    private final Image poisonImage;
    private final Image foodImage;
    private final Image dirtImage;

    public GameInterface() {

        font = "-fx-base: rgb(50, 41 ,47); -fx-padding: 0 0 0 0; -fx-font-size: 12px; -fx-font-family: 'Daydream';";

        nodes = new ArrayList<>();
        metaData = MetaDataRegistry.getInstance();

        exitButton = new Button("Exit");
        exitButton.setLayoutY(0);
        exitButton.setLayoutX(metaData.getSquareOffset());
        exitButton.setPrefSize(80, 40);
        exitButton.setFocusTraversable(false);
        exitButton.setStyle(font);
        nodes.add(exitButton);

        saveButton = new Button("Save");
        saveButton.setLayoutX(80 + metaData.getSquareOffset());
        saveButton.setPrefSize(80, 40);
        saveButton.setFocusTraversable(false);
        saveButton.setStyle(font);
        nodes.add(saveButton);

        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(metaData.getResolutionX() / 2 - 140 + metaData.getSquareOffset());
        pauseButton.setPrefWidth(100);
        pauseButton.setPrefHeight(40);
        pauseButton.setFocusTraversable(false);
        pauseButton.setStyle(font);
        nodes.add(pauseButton);

        speed1Button = new Button(">");
        speed1Button.setLayoutX(metaData.getResolutionX() / 2 - 20 + metaData.getSquareOffset());
        speed1Button.setPrefWidth(40);
        speed1Button.setPrefHeight(40);
        speed1Button.setFocusTraversable(false);
        speed1Button.setStyle(font);
        nodes.add(speed1Button);

        speed3Button = new Button(">>");
        speed3Button.setLayoutX(metaData.getResolutionX() / 2 + 20 + metaData.getSquareOffset());
        speed3Button.setPrefSize(40, 40);
        speed3Button.setFocusTraversable(false);
        speed3Button.setStyle(font);
        nodes.add(speed3Button);

        tools = new ComboBox<>();
        tools.setFocusTraversable(false);
        tools.setLayoutX(metaData.getResolutionX() - 200 + metaData.getSquareOffset());
        tools.setPrefSize(200, 40);
        tools.getItems().addAll(Tool.values());
        tools.setValue(Tool.SELECT);
        tools.setStyle(font);
        nodes.add(tools);

        // Load icons for tools (used in dropdown rows)
        shovelImage = new Image(getClass().getResourceAsStream("/sprites/shovel.png"), 32, 32, false, false);
        poisonImage = new Image(getClass().getResourceAsStream("/sprites/Poison.png"), 32, 32, false, false);
        foodImage = new Image(getClass().getResourceAsStream("/sprites/apple.png"), 32, 32, false, false);
        dirtImage = new Image(getClass().getResourceAsStream("/sprites/Dirt.png"), 32, 32, false, false);

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

        // Add icons to each dropdown row and the selected value
        tools.setCellFactory(listView -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Tool item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(tools.getConverter().toString(item));
                    imageView.setFitWidth(24);
                    imageView.setFitHeight(24);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(imageFor(item));
                    setGraphic(imageView.getImage() != null ? imageView : null);
                }
            }
        });

        tools.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Tool item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(tools.getConverter().toString(item));
                    imageView.setFitWidth(24);
                    imageView.setFitHeight(24);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(imageFor(item));
                    setGraphic(imageView.getImage() != null ? imageView : null);
                }
            }
        });

        setPressedButton(">");
    }

    /**
     * sets which speed button should diplsay a different color to indicate that it
     * is the currently selected speed.
     *
     * @param button
     */
    public void setPressedButton(String button) {
        switch (button) {
            case ">":
                speed1Button.setStyle(
                        "-fx-base: rgb(25, 20 ,23); -fx-padding: 0 0 0 0; -fx-font-size: 12px; -fx-font-family: 'Daydream';");
                speed3Button.setStyle(font);
                break;
            case ">>>":
                speed1Button.setStyle(font);
                speed3Button.setStyle(
                        "-fx-base: rgb(25, 20 ,23); -fx-padding: 0 0 0 0; -fx-font-size: 12px; -fx-font-family: 'Daydream';");
                break;
        }
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getSpeed1Button() {
        return speed1Button;
    }

    public Button getSpeed3Button() {
        return speed3Button;
    }

    public ComboBox<Tool> getTools() {
        return tools;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public String getFont() {
        return font;
    }

    private Image imageFor(Tool tool) {
        return switch (tool) {
            case SHOVEL -> shovelImage;
            case PLACE_POISON -> poisonImage;
            case PLACE_FOOD -> foodImage;
            case PLACE_DIRT -> dirtImage;
            default -> null;
        };
    }

}
