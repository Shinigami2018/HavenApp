package com.haven.app.haven;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class HelloController {
    @FXML
    public AnchorPane loginpane;
    @FXML
    public AnchorPane signuppane;
    public AnchorPane prompt1, prompt2;
    public Button next, finished;
    public AnchorPane dashbboard;
    public Button left_curved_button, right_curved_button, middle_button1, middle_button2, middle_button3;
    public Button smsButton;
    public static String usename;
    public static  String eEmail;
    public static String ePHN;


    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField repasswordPasswordField;

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField emergency;
    @FXML
    private TextField ephone;

    public JFXRadioButton maleRadio = new JFXRadioButton();

    @FXML
    public JFXRadioButton femaleRadio = new JFXRadioButton();

    @FXML
    public ToggleGroup genderGroup; // This ensures only one selection at a time

    public static String selectedGender = "male";

    //ariful cmnt just
    public void signUpButtonOnAction() {
        if (!usernameTextField.getText().isEmpty() && !passwordPasswordField.getText().isEmpty() && !repasswordPasswordField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !emergency.getText().isEmpty() && !ephone.getText().isEmpty()) {
            //loginMessageLabel.setText("you try to login!");
            System.out.println("you try to login!");
            if (passwordPasswordField.getText().equals(repasswordPasswordField.getText())) {
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

        String addUser = "INSERT INTO useraccounts(Username, Password, Email,Eemail,Phone,score,Gender) VALUES (?, ?, ?, ?, ?,?,?)";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(addUser);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordPasswordField.getText());
            preparedStatement.setString(3, emailTextField.getText());
            preparedStatement.setString(4, emergency.getText());
            String phn = "+88" + ephone.getText();
            preparedStatement.setString(5, phn);
            preparedStatement.setInt(6, 0);
            if (maleRadio.isSelected()) {
                selectedGender = "Male";
            } else if (femaleRadio.isSelected()) {
                selectedGender = "Female";
            }
            preparedStatement.setString(7, selectedGender);
            usename = usernameTextField.getText();
            eEmail = emergency.getText();
            ePHN = phn;

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
                selectedGender = resultSet.getString("Gender");
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
    public Button menu_journal, menu_contact, menu_resource, menu_back, menu_logout, menu_calendar;


    public void setLogin(ActionEvent event) {
        HelloApplication.switchRoot("login.fxml", 619, 434);
    }

    public void setSignup(ActionEvent event) {
        HelloApplication.switchRoot("signup.fxml", 619, 434);

    }


    /*on-click */


    public void switch_screen_to_personality(ActionEvent event) {

        // get the data for username, email password and confirm password

        HelloApplication.switchRoot("Personality.fxml", 894, 648);
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

    public void switch_screen_to_dashboard(ActionEvent event) {
        HelloApplication.switchRoot("Dashboard.fxml", 1500, 820);
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
            // Get user input for recipient email (replace this with actual user input mechanism)
            String recipientEmail = eEmail; // Replace with actual user input

            // Pass email as an argument to Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/java/com/haven/app/haven/send_sms.py", recipientEmail);
            processBuilder.inheritIO(); // Redirects output to the console
            Process process = processBuilder.start();
            //process.waitFor(); // Waits for the process to finish

            // Send SMS as well
            emergencySMS sms = new emergencySMS("User needs help! Please call or message as soon as possible!", ePHN);
            sms.sendSms();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void switch_to_login_screen(ActionEvent event) /*for logout*/ {
        HelloApplication.switchRoot("login.fxml", 619, 434);

    }

    public void initialize() {
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
    }

    public void setSelectedGender() {

        if (maleRadio.isSelected()) {
            selectedGender = "Male";
        } else if (femaleRadio.isSelected()) {
            selectedGender = "Female";
        }
    }

    public static String getSelectedGender() {
        return selectedGender;
    }


}