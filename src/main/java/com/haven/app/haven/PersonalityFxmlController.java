package com.haven.app.haven;


import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class PersonalityFxmlController implements Initializable {
    public JFXButton sad_button;
    public JFXButton des_button;
    public JFXButton cur_button;
    public JFXButton joy_button;



    public void initialize(URL url, ResourceBundle rb) {
        // Animate button 1
        animateButton(sad_button);

        // Animate button 2
        animateButton(des_button);

        // Animate button 3
        animateButton(cur_button);

        // Animate button 4
        animateButton(joy_button);
    }

    private void animateButton(JFXButton button) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setNode(button);
        transition.setToY(-20);  // Move up by 200px
        transition.play();
    }
}
