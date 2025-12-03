package controller;

import model.Model;
import view.View;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.MainMenu;

import java.io.IOException;

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

    @Override
    public void handleMousePressed(MouseEvent event) {

    }

    @Override
    public void handleMouseReleased(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {

    }

    private void setupButtonHandlers(){
        MainMenu.getInstance().getNewGameButton().setOnAction(e -> {
            handleNewGameButton();
        });
        MainMenu.getInstance().getLoadGameButton().setOnAction(e -> {
            try {
                handleLoadButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void handleLoadButton() throws IOException {
        model.loadColony();
        model.setTickrate(model.getStartingTickrate());
        view.resetInterface();
        model.setGameState("RUNNING");
        view.setInputHandler(new Controller(model, view));
    }

    private void handleNewGameButton() {
        model.newGame();
        model.setTickrate(model.getStartingTickrate());
        view.resetInterface();
        model.setGameState("RUNNING");
        view.setInputHandler(new Controller(model, view));
    }

}
