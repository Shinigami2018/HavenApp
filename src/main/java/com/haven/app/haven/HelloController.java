package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLOutput;

public class HelloController {
    @FXML
    public AnchorPane loginpane;
    @FXML
    public AnchorPane signuppane;


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


}