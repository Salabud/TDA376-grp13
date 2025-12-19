package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for storing all the node elements that make up the Main Menu
 */
public class MainMenu {
    private static MainMenu INSTANCE;
    private final List<Node> nodes;
    private final Button newGameButton;
    private final Button loadGameButton;
    private final Button settingsButton;
    private final String backGroundColor;
    private final String buttonColor;
    private final Label titleLabel;
    private final MetaDataRegistry metaData = MetaDataRegistry.getInstance();

    private MainMenu(){
        nodes = new ArrayList<>();
        backGroundColor = "-fx-background-color: rgb(112,93,86);";
        buttonColor = "-fx-base: rgb(50, 41 ,47); -fx-padding: -20 0 0 0; -fx-font-family: 'Daydream'; -fx-font-size: 30px;";
        Font.loadFont(getClass().getResourceAsStream("/fonts/Daydream.otf"), 40);

        titleLabel = new Label("Ant Simulator");
        titleLabel.setFont(new Font(60));
        titleLabel.setLayoutX(0);
        titleLabel.setMinWidth(metaData.getResolutionY());
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setLayoutY(50);
        titleLabel.setStyle("-fx-font-family: 'Daydream'; -fx-font-size: 40px; -fx-text-fill: rgb(250, 149, 0)");
        nodes.add(titleLabel);

        newGameButton = new Button("New Colony");
        newGameButton.setLayoutX(100);
        newGameButton.setLayoutY(200);
        newGameButton.setMinWidth(600);
        newGameButton.setMinHeight(100);
        newGameButton.setStyle(buttonColor);
        newGameButton.setFocusTraversable(false);
        nodes.add(newGameButton);

        loadGameButton = new Button("Load Colony");
        loadGameButton.setLayoutX(100);
        loadGameButton.setLayoutY(350);
        loadGameButton.setMinWidth(600);
        loadGameButton.setMinHeight(100);
        loadGameButton.setStyle(buttonColor);
        loadGameButton.setFocusTraversable(false);
        nodes.add(loadGameButton);

        settingsButton = new Button("Settings");
        settingsButton.setLayoutX(100);
        settingsButton.setLayoutY(500);
        settingsButton.setMinWidth(600);
        settingsButton.setMinHeight(100);
        settingsButton.setStyle(buttonColor);
        settingsButton.setFocusTraversable(false);
        nodes.add(settingsButton);

        refreshSize();

    }

    public static MainMenu getInstance() {
        if (INSTANCE == null){
            INSTANCE = new MainMenu();
        }
        return INSTANCE;
    }

    /**
     * @return all ui elements (nodes) from the Main Menu
     */
    public List<Node> getNodes(){
        return nodes;
    }

    public Button getNewGameButton(){
        return newGameButton;
    }
    public Button getLoadGameButton() {
        return loadGameButton;
    }
    public Button getSettingsButton(){
        return settingsButton;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void refreshSize(){
        newGameButton.setLayoutX(metaData.getResolutionY()/2-300);

        loadGameButton.setLayoutX(metaData.getResolutionY()/2-300);

        settingsButton.setLayoutX(metaData.getResolutionY()/2-300);
    }
}
