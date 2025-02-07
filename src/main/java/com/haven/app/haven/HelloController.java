package com.haven.app.haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    
    // ariful writing 
    
//    private Label loginMessageLabel;
//    private TextField usernameTextField;
//    private PasswordField passwordPasswordField;
//
//
//    public  void loginButtonOnAction(ActionEvent event)
//    {
//        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
//            //loginMessageLabel.setText("you try to login!");
//            validateLogin();
//        } else {
//            loginMessageLabel.setText("Please enter username and password.");
//
//        }
//    }
//
//    public void validateLogin()
//    {
//        DatabaseConnection connectNow = new DatabaseConnection();
//
//       // Connection connectdb = connectNow.getConnection();
//        String verifyLogin="SELECT count (1) FROM useraccounts WHERE username='" +usernameTextField.getText() + "'AND password='"+passwordPasswordField.getText()+ "'";
//
//        try{
//            Statement statement=connectdb.createStatement();
//            ResultSet queryResult=statement.executeQuery(verifyLogin);
//            while(queryResult.next())
//            {
//                if(queryResult.getInt(1)==1)
//                {
//                    loginMessageLabel.setText("Welcome!");
//                }
//                else
//                {
//                    loginMessageLabel.setText("Invalide Login ,Please Try again");
//
//                }
//            }
//        }
//        catch(Exception event){
//          event.printStackTrace();
//        }
//    }
   //ariful
public static void signupuser (ActionEvent event,String Email,String Username,String Password,String Repassword) throws SQLException {
    Connection connection = null;
    PreparedStatement psInsert = null;
    PreparedStatement psCheckUserExists = null;
    ResultSet resultSet = null;
    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "124519@#maisk#");
        psCheckUserExists = connection.prepareStatement("select * from useraccounts where username='" + Username + "'");
        resultSet = psCheckUserExists.executeQuery();
        if (resultSet.isBeforeFirst()) {
            System.out.println("Username already exists");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.showAndWait();
        }
        else
        {
            psInsert=connection.prepareStatement("INSERT INTO useraccounts(username,password,repassword,email) VALUES(?,?,?,?) ");
            psInsert.setString(1,Username);
            psInsert.setString(2,Password);
            psInsert.setString(3,Repassword);
            psInsert.setString(4,Email);
            psInsert.executeUpdate();
            signupuser(event,Email,Username,Password,Repassword);

        }
    }
    catch (SQLException e) {
        e.printStackTrace();

    }

    finally {
        if(resultSet!=null)
        { try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }

        }
        if(psInsert!=null)
        {
            try { psInsert.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        if(psCheckUserExists != null)
        {
            try { psCheckUserExists.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        if(connection!=null)
        {
            try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

}

public static void loginuser(ActionEvent event,String Email,String Username,String Password,String Repassword) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "124519@#maisk#");
            preparedStatement=connection.prepareStatement("select * from useraccounts where username='" + Username + "'");
            resultSet=preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst())
            {
                System.out.println("Username already exists");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.showAndWait();

            }else
            {
                while(resultSet.next())
                {
                    String gusername = resultSet.getString("username");
                    String gpassword = resultSet.getString("password");
                    String grepassword = resultSet.getString("repassword");
                    String gemail = resultSet.getString("email");
                    if(gpassword.equals(Password) && gusername == Username)
                    {
                        loginuser(event,Email,Username,Password,Repassword);
                    }
                    else
                    {
                        System.out.println("Wrong Password");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.showAndWait();
                        

                    }

                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null)
                { try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }}
            if(preparedStatement!=null)
                { try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }}
            if(connection!=null)
            {
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }

            }
        }

}
/// ariful     
    public Button user_button;
    @FXML
    public Button menu_journal,menu_contact,menu_resource,menu_back,menu_logout,menu_calendar;


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
    public void change_color5(ActionEvent event) /*for calender*/
    {
        menu_calendar.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_journal.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_contact.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");


    }
    @FXML
    public void change_color6(ActionEvent event) /*for journal*/
    {
        menu_calendar.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_journal.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_contact.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");

    }
    @FXML
    public void change_color7(ActionEvent event) /*for emergency contacts*/
    {
        menu_calendar.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_journal.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        menu_contact.getStyleClass().add("user-menu-left-sub-button-clicked");
        menu_resource.getStyleClass().removeIf(style -> style.endsWith("-clicked"));
        System.out.println("Button working!");

    }
    @FXML
    public void change_color8(ActionEvent event) /*for medical resources*/
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
        HelloApplication.switchRoot("log.fxml",400,259);

    }







}