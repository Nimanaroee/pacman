module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.media;
    requires java.desktop;
    //requires com.google.gson;

    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    //exports model.saved to com.google.gson;
}