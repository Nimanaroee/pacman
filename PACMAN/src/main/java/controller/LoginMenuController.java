package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Data;
import model.User;
import view.GameMenuView;

public class LoginMenuController {
    @FXML
    protected Button loginButton;
    @FXML
    protected Button registerButton;
    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected Label labelText;

    GameMenuView gameMenuView;

    @FXML
    protected void onClickLoginButton() throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Data.getUserByUsername(username);
        if(user == null) {
            labelText.setText("this username doesnt exist!");
            return;
        }
        if(!user.getPassword().equals(password)) {
            labelText.setText("password doesnt match!");
            return;
        }
        Data.setLoggedUser(username);

        //// change length and difficulty
        gameMenuView = new GameMenuView();
        gameMenuView.start(Data.stage);
    }
    @FXML
    protected void onClickRegisterButton(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String errors = "";
        if(!username.matches(" *(?<username>[a-zA-Z0-9_]+) *"))
            errors += "invalid username! \n";
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$"))
            errors += "invalid password! \n";
        if(errors.length() != 0) {
            labelText.setText(errors);
            return;
        }
        Data.addUser(new User(username, password));

        labelText.setText("");
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("User created successfully!");
        errorAlert.showAndWait();
    }
}