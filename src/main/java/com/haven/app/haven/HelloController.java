package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    public AnchorPane loginpane;
    @FXML
    public AnchorPane signuppane;
    public AnchorPane prompt1,prompt2;
    public Button next,finished;
    public AnchorPane dashbboard;
    public Button left_curved_button, right_curved_button, middle_button1, middle_button2, middle_button3;
    public Button smsButton;

    // ariful writing 
    //;
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField repasswordPasswordField;

    @FXML
    private TextField emailTextField;
    //ariful cmnt just
   public  void signUpButtonOnAction()
   {
       if (!usernameTextField.getText().isEmpty() && !passwordPasswordField.getText().isEmpty() && !repasswordPasswordField.getText().isEmpty() && !emailTextField.getText().isEmpty()) {
           //loginMessageLabel.setText("you try to login!");
           System.out.println("you try to login!");
           if(passwordPasswordField.getText().equals(repasswordPasswordField.getText()))
           {
            validateSignUp();
           }
               //loginMessageLabel.setText("Password Match!");
               //signupuser(event,emailTextField.getText(),usernameTextField.getText(),passwordPasswordField.getText(),repasswordPasswordField.getText());
           
       } else {
        //loginMessageLabel.setText("Please enter all the information correctly!");
       }
   }
//
public void validateSignUp() {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();

    if (connectDB == null) {
        System.out.println("Database connection failed.");
        return;
    }

    String addUser = "INSERT INTO useraccounts(Username, Password, Email) VALUES (?, ?, ?)";

    try {
        PreparedStatement preparedStatement = connectDB.prepareStatement(addUser);
        preparedStatement.setString(1, usernameTextField.getText());
        preparedStatement.setString(2, passwordPasswordField.getText());
        preparedStatement.setString(3, emailTextField.getText());

        preparedStatement.executeUpdate();

        HelloApplication.switchRoot("Personality.fxml", 894, 648);
        prompt1.setVisible(true);
        prompt2.setVisible(false);
        next.setVisible(true);
        finished.setVisible(false);
    } catch (SQLException e) {
        e.printStackTrace();
    } 
}
public void loginUser(ActionEvent event) {
    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();

    if (connectDB == null) {
        System.out.println("Database connection failed.");
        return;
    }

    String verifyLogin = "SELECT * FROM useraccounts WHERE Username = ? AND Password = ?";
    
    try {
        PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
        preparedStatement.setString(1, usernameTextField.getText());
        preparedStatement.setString(2, passwordPasswordField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Login successful, now switch to the dashboard screen
            System.out.println("Login successful!");
            switch_screen_to_dashboard(event);
        } else {
            System.out.println("Invalid credentials! Please try again.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


/// ariful     
    public Button user_button;
    @FXML
    public Button menu_journal,menu_contact,menu_resource,menu_back,menu_logout,menu_calendar;


    public void setLogin(ActionEvent event)
    {
        HelloApplication.switchRoot("login.fxml",619,434);
    }

    public void setSignup(ActionEvent event)
    {
        HelloApplication.switchRoot("signup.fxml",619,434);

    }

    public void switch_screen_to_personality(ActionEvent event)
    {

        // get the data for username, email password and confirm password

        HelloApplication.switchRoot("Personality.fxml",894,648);
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

    public void switch_screen_to_dashboard(ActionEvent event)
    {
        HelloApplication.switchRoot("Dashboard.fxml",815,667);
        dashbboard.setVisible(true);
        System.out.println("YES");
    }
    

//    @FXML
//    public void initialize() {
//        smsButton.setOnAction(this::email_send);
//    }

    @FXML
    private void email_send(ActionEvent event) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main/java/com/haven/app/haven/send_sms.py");
            processBuilder.inheritIO(); // Redirects output to the console
            Process process = processBuilder.start();
            process.waitFor(); // Waits for the process to finish
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*public void switch_screen_to_dashboard2(ActionEvent event)
    {
        HelloApplication.switchRoot("Dashboard.fxml");
        dashbboard.setVisible(true);
        System.out.println("YES");
    }*/
    @FXML
    public void setPrompt2(ActionEvent event)
    {
        prompt1.setVisible(false);
        prompt2.setVisible(true);
        next.setVisible(false);
        finished.setVisible(true);
    }
    @FXML
    public void change_color1(ActionEvent event)
    {
        left_curved_button.getStyleClass().add("left-curved-button-clicked");
        right_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button1.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button2.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button3.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
    }
    @FXML
    public void change_color2(ActionEvent event)
    {
        left_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        right_curved_button.getStyleClass().add("right-curved-button-clicked");
        middle_button1.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button2.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button3.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
    }
    @FXML
    public void change_color3(ActionEvent event)
    {
        left_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        right_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button1.getStyleClass().add("middle-button-clicked");
        middle_button2.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button3.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
    }
    @FXML
    public void change_color4(ActionEvent event)
    {
        left_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        right_curved_button.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button1.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        middle_button2.getStyleClass().add("middle-button-clicked");
        middle_button3.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
    }
    @FXML
    public void switch_screen_to_user(ActionEvent event)
    {
        HelloApplication.switchRoot("user.fxml",815,667);


    }


    /*adding click animations to user left sub buttons*/
    @FXML
    public void change_color5(ActionEvent event) /*for calender*/ /*needs debugging*/
    {
        menu_calendar.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_journal.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_contact.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");


    }
    @FXML
    public void change_color6(ActionEvent event) /*for journal*/ /*needs debugging*/
    {
        menu_calendar.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_journal.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_contact.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");

    }
    @FXML
    public void change_color7(ActionEvent event) /*for emergency contacts*/ /*needs debugging*/
    {
        menu_calendar.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_journal.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_contact.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");

    }
    @FXML
    public void change_color8(ActionEvent event) /*for medical resources*/ /*needs debugging*/
    {
        menu_calendar.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_journal.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_contact.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_resource.getStyleClass().add("user-menu-left-sub-button-clicked");
        System.out.println("Button working!");

    }

    @FXML
    public void switch_to_login_screen(ActionEvent event) /*for logout*/
    {
        HelloApplication.switchRoot("login.fxml",619,434);

    }







}