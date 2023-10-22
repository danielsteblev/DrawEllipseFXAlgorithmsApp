package ru.vsu.cs.util.steblev_d_v.drawellipsefxapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawEllipseApplication extends Application {
    @Override
    public void start(Stage window) throws IOException {
        Parent root = FXMLLoader.load(DrawEllipseApplication.class.getResource("hello-view.fxml"));
        window.setScene(new Scene(root, 800, 600));
        window.setTitle("Draw Ellipse Algorithm");
        window.show();
        window.setResizable(false);





    }

    public static void main(String[] args) {
        launch();
    }
}