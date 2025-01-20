package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLOutput;

public class HelloController {
    @FXML
    public AnchorPane loginpane;
    @FXML
    public AnchorPane signuppane;
    public AnchorPane prompt1,prompt2;
    public Button next,finished;


    public void setLogin(ActionEvent event)
    {
        loginpane.setVisible(true);
        signuppane.setVisible(false);
        System.out.println("Login Successful");
    }

    public void setSignup(ActionEvent event)
    {
        loginpane.setVisible(false);
        signuppane.setVisible(true);
        System.out.println("Signup Successful");

    }

    public void switch_screen_to_personality(ActionEvent event)
    {
        HelloApplication.switchRoot("Personality.fxml");
        prompt1.setVisible(true);
        prompt2.setVisible(false);
        next.setVisible(true);
        finished.setVisible(false);

    }

    /*public void setPrompt1(ActionEvent event)
    {
        prompt1.setVisible(true);
        prompt2.setVisible(false);
        next.setVisible(true);
        finished.setVisible(false);
    }*/

    public void setPrompt2(ActionEvent event)
    {
        prompt1.setVisible(false);
        prompt2.setVisible(true);
        next.setVisible(false);
        finished.setVisible(true);
    }



}