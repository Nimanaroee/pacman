package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;

import java.io.IOException;

public class LoginMenuView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Data.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMenuView.class.getResource("login-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Data.H, Data.V);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}