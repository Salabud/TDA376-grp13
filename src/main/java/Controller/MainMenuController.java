package Controller;

import Model.Model;
import View.View;
import app.Main;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import View.MainMenu;

public class MainMenuController implements InputHandler{
    private Model model;
    private View view;

    public MainMenuController(Model model, View view) {
        this.model = model;
        this.view = view;
        setupButtonHandlers();
    }
    @Override
    public void handleKeyPress(KeyEvent event) {

    }

    @Override
    public void handleMouseClick(MouseEvent event) {

    }

    @Override
    public void handleMouseMove(MouseEvent event) {

    }

    private void setupButtonHandlers(){
        MainMenu.getInstance().getNewGameButton().setOnAction(e -> {
            handleNewGameButton();
        });
    }

    private void handleNewGameButton() {
        model.newGame();
        model.setTickrate(model.getStartingTickrate());
        view.resetInterface();
        model.setGameState("RUNNING");
        view.setInputHandler(new Controller(model, view));
    }

}
