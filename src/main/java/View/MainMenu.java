package View;

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
    private List<Node> nodes;
    private Button newGameButton;
    private Button loadGameButton;
    private Button settingsButton;
    private String backGroundColor;
    private String buttonColor;
    private Label titleLabel;

    private MainMenu(){
        nodes = new ArrayList<>();
        backGroundColor = "-fx-background-color: rgb(112,93,86);";
        buttonColor = "-fx-base: rgb(50, 41 ,47);";


        titleLabel = new Label("Ant Simulator");
        titleLabel.setFont(new Font(60));
        titleLabel.setLayoutX(200);
        titleLabel.setLayoutY(50);
        nodes.add(titleLabel);

        newGameButton = new Button("New Game");
        newGameButton.setFont(new Font(30));
        newGameButton.setLayoutX(100);
        newGameButton.setLayoutY(200);
        newGameButton.setMinWidth(600);
        newGameButton.setMinHeight(100);
        newGameButton.setStyle(buttonColor);
        nodes.add(newGameButton);

        loadGameButton = new Button("Load Game");
        loadGameButton.setFont(new Font(30));
        loadGameButton.setLayoutX(100);
        loadGameButton.setLayoutY(350);
        loadGameButton.setMinWidth(600);
        loadGameButton.setMinHeight(100);
        loadGameButton.setStyle(buttonColor);;
        nodes.add(loadGameButton);

        settingsButton = new Button("Settings");
        settingsButton.setFont(new Font(30));
        settingsButton.setLayoutX(100);
        settingsButton.setLayoutY(500);
        settingsButton.setMinWidth(600);
        settingsButton.setMinHeight(100);
        settingsButton.setStyle(buttonColor);
        nodes.add(settingsButton);

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
}
