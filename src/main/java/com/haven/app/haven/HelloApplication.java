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
        String fxmlFile = "signup.fxml"; // Change this to "login.fxml" dynamically if needed

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("HAVEN");
        stage.setScene(scene);

        // Disable resizing only for "signup.fxml" or "login.fxml"
        if (fxmlFile.equals("signup.fxml") || fxmlFile.equals("login.fxml")) {
            stage.setResizable(false);
        } else {
            if (fxmlFile.equals("Personality.fxml")) {
                primaryStage.setResizable(false);
            }
            else
            {
                primaryStage.setResizable(true);
            }
            primaryStage.setFullScreen(true);
        }

        stage.show();
    }

    //method to change the root node
    //fxmlFile The FXML file to load as the new root.

    public static void switchRoot(String fxmlFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            Parent newRoot = loader.load();
            primaryStage.getScene().setRoot(newRoot);
            // Set full screen mode

            primaryStage.setWidth(width);
            primaryStage.setHeight(height);

            // Disable resizing for "signup.fxml" or "login.fxml"
            if (fxmlFile.equals("signup.fxml") || fxmlFile.equals("login.fxml")) {
                primaryStage.setResizable(false);
            } else {
                if (fxmlFile.equals("Personality.fxml")) {
                    primaryStage.setResizable(false);
                }
                else
                {
                    primaryStage.setResizable(true);
                }
                primaryStage.setFullScreen(true);

                // Handle exiting full-screen mode with custom size
                primaryStage.fullScreenProperty().addListener((obs, wasFullScreen, isNowFullScreen) -> {
                    if (!isNowFullScreen) {
                        primaryStage.setWidth(width);
                        primaryStage.setHeight(height);
                    }
                });
            }

        } catch (Exception e) {
            System.err.println("Failed to load FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        launch();
    }
}
