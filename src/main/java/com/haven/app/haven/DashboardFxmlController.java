package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.haven.app.haven.HelloApplication.primaryStage;

public class DashboardFxmlController {
    @FXML
    public Button user_button;
    public JFXButton music_prev;
    public JFXButton music_next;
    public JFXButton music_play;
    @FXML
    private Label scor; // Make sure this matches the fx:id in your FXML

    public ImageView profileImage;
    public void setScore(int score) {
        scor.setText("Score: " + score);
    }

    public void switch_screen_to_user_page(ActionEvent event) {
        HelloApplication.switchRoot("User.fxml", 1500, 820);
        System.out.println("YES");
    }
    public void switch_screen_to_journal_page(ActionEvent event) {
        HelloApplication.switchRoot("Journal.fxml", 1500, 820);
        System.out.println("YES");
    }

    public void initialize()
    {
        String gender = HelloController.getSelectedGender();
        if (gender != null) {
            if (gender.equals("Male")) {
                profileImage.setImage(new Image("E:\\Programming\\GitHub\\HavenApp\\src\\main\\resources\\noun-male-5295254.png"));
            } else if (gender.equals("Female")) {
                profileImage.setImage(new Image("E:\\Programming\\GitHub\\HavenApp\\src\\main\\resources\\noun-female-5295234.png"));
            }
        }

    }
}