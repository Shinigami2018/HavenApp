package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class DashboardFxmlController {
    public Button user_button;
    public void switch_screen_to_user_page(ActionEvent event)
    {
        HelloApplication.switchRoot("User.fxml",1500,820);
        System.out.println("YES");
    }
}
