package com.haven.app.haven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    public static Stage primaryStage;

    @Override


    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Personality.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setResizable(true);

        stage.setTitle("HAVEN");
        stage.setScene(scene);
        stage.show();

    }

    //method to change the root node
    //fxmlFile The FXML file to load as the new root.

    public static void switchRoot(String fxmlFile,int width,int height) {
        try {
            Parent newRoot = FXMLLoader.load(HelloApplication.class.getResource(fxmlFile));
            primaryStage.getScene().setRoot(newRoot);
            // Set new width and height
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);

        } catch (Exception e) {
            System.err.println("Failed to load FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        launch();
    }
}
