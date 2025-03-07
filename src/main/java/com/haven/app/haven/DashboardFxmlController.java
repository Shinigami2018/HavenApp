package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashboardFxmlController {
    @FXML
    public Button user_button;
    
    @FXML
    private Label scor; // Make sure this matches the fx:id in your FXML

    public void setScore(int score) {
        scor.setText("Score: " + score);
    }

    public void switch_screen_to_user_page(ActionEvent event) {
        HelloApplication.switchRoot("User.fxml", 1500, 820);
        System.out.println("YES");
    }
}