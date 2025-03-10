package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.io.IOException;

public class DashboardFxmlController {
    @FXML
    public Button user_button;
    public JFXButton music_prev;
    public JFXButton music_next;
    public JFXButton music_play;
    @FXML
    public static Label Score; // Make sure this matches the fx:id in your FXML

    @FXML
    public ImageView profileImage;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    public static void setScore(int score) {
        Score.setText(String.valueOf(score));
        String s = Score.getText();
        System.out.println(s);

    }

    public void switch_screen_to_user_page(ActionEvent event) {
        HelloApplication.switchRoot("User.fxml", 1550, 830);
        System.out.println("YES");
    }

    public void switch_to_journal(ActionEvent event) {
        HelloApplication.switchRoot("Journal.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }
    public void switch_to_relax(ActionEvent event) {
        HelloApplication.switchRoot("Relax.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }
    public void switch_to_report(ActionEvent event) {
        HelloApplication.switchRoot("Report.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }
    public void audioplay(ActionEvent event) {
        System.out.println("Audio Play"+mediaPlayer);
        if (mediaPlayer == null) {
            Media sound = new Media(new File("src/main/resources/com/haven/app/haven/surah.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }

        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer = null;
            isPlaying = false;
        } else {
            mediaPlayer.play();
            isPlaying = true;
        }
    }
    public void audioprev(ActionEvent event) {
        System.out.println("Audio Prev"+mediaPlayer);
        if (mediaPlayer == null) {
            Media sound = new Media(new File("src/main/resources/com/haven/app/haven/halal.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }

        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer = null;
            isPlaying = false;
        } else {
            mediaPlayer.play();
            isPlaying = true;
        }
    }
    public void audionext(ActionEvent event) {
        System.out.println("Audio Next"+mediaPlayer);
        if (mediaPlayer == null ) {
            Media sound = new Media(new File("src/main/resources/com/haven/app/haven/fatiha.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }

        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer = null;
            isPlaying = false;
        } else {
            mediaPlayer.play();
            isPlaying = true;
        }
    }

    public void initialize() {
        String gender = HelloController.getSelectedGender();
        if (gender != null) {
            if (gender.equals("Male")) {
                profileImage.setImage(new Image("noun-male-5295254.png"));
            } else if (gender.equals("Female")) {
                profileImage.setImage(new Image("noun-female-5295234.png"));
            }
        }

        music_play.setOnAction(this::audioplay);
        music_prev.setOnAction(this::audioprev);
        music_next.setOnAction(this::audionext);

    }
}