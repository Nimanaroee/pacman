package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController {
    @FXML
    protected static Label scoreboard;

    @FXML
    public static void setScoreboard(int score) {
        scoreboard.setText(String.valueOf(score));
    }

}
