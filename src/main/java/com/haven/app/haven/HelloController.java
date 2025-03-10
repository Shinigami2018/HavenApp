package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import static com.haven.app.haven.HelloApplication.primaryStage;

public class HelloController {
    public static String UserName;
    @FXML
    public AnchorPane loginpane;
    @FXML
    public AnchorPane signuppane;
    //    public AnchorPane prompt1, prompt2;
//    public Button next, finished;
    public BorderPane dashbboard;
    public Button left_curved_button, right_curved_button, middle_button1, middle_button2, middle_button3;
    public Button smsButton;
    public static String usename;
    public static String eEmail;
    public static String ePHN;
    public static String useremail;
    @FXML
    public StackPane centerContent = new StackPane();
    @FXML
    public Label account_name=new Label();
    @FXML
    public ImageView userPhoto = new ImageView();

    @FXML
    public TextField usernameTextField;

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

    @FXML
    public static String selectedGender = "male";

    @FXML
    public AnchorPane left_bar, right_bar;
    @FXML
    public BorderPane root;

    @FXML
    public VBox leftContent, rightContent;
    @FXML
    public JFXButton menu_journal, menu_contacts, menu_resource,user_back,user_logout;

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
            useremail = emailTextField.getText();
            try {
                preparedStatement.executeUpdate();
                ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/java/com/haven/app/haven/signupmail.py", useremail, usename);
                processBuilder.inheritIO(); // Redirects output to the console
                Process process = processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            HelloApplication.switchRoot("Personality.fxml", 1550, 830);
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
        String mooddata = "SELECT date FROM moodhistory WHERE user_id = ? ORDER BY date DESC LIMIT 1";

        UserName = usernameTextField.getText();
        System.out.println("hi"+UserName);
        int latestDate = 0;

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordPasswordField.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Login successful
                System.out.println("Login successful!");
                selectedGender = resultSet.getString("Gender");
                int userID = resultSet.getInt("iduseraccounts");

                PreparedStatement moodStatement = connectDB.prepareStatement(mooddata);
                moodStatement.setInt(1, userID);
                ResultSet moodResultSet = moodStatement.executeQuery();

                if (moodResultSet.next()) {
                    latestDate = moodResultSet.getInt("date");
                }

                moodResultSet.close();
                moodStatement.close();

                timefunction tf = new timefunction();
                int currDay = tf.uday;

                if (latestDate < currDay) {
                    HelloApplication.switchRoot("Personality.fxml", 1550, 830);
                } else {
                    switch_screen_to_dashboard(event);
                }
            } else {
                System.out.println("Invalid credentials! Please try again.");
            }

            resultSet.close();
            preparedStatement.close();
            connectDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /// ariful
    public Button user_button;
    @FXML



    public void setLogin(ActionEvent event) {
        HelloApplication.switchRoot("login.fxml", 619, 434);
    }

    public void setSignup(ActionEvent event) {
        HelloApplication.switchRoot("signup.fxml", 619, 434);

    }


    /*on-click */


    public void switch_screen_to_personality(ActionEvent event) {

        // get the data for username, email password and confirm password

        HelloApplication.switchRoot("Personality.fxml", 1550, 830);


    }

    public void switch_screen_to_dashboard(ActionEvent event) {
        HelloApplication.switchRoot("Dashboard.fxml", 1550, 830);
        if (dashbboard != null) {
            dashbboard.setVisible(true);
        } else {
            System.out.println("Dashboard is null");
        }

    }


    @FXML
    private void email_send(ActionEvent event) {
        try {
            // Get user input for recipient email (replace this with actual user input mechanism)
            String recipientEmail = eEmail; // Replace with actual user input

            // Pass email as an argument to Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/java/com/haven/app/haven/send_sms.py", recipientEmail, usename);
            processBuilder.inheritIO(); // Redirects output to the console
            Process process = processBuilder.start();
            //process.waitFor(); // Waits for the process to finish

            // Send SMS as well
            String smsBODY = MessageFormat.format("{0} needs help! Please call or message as soon as possible!", usename);
            emergencySMS sms = new emergencySMS(smsBODY, ePHN);
            sms.sendSms();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void switch_to_login_screen(ActionEvent event) /*for logout*/ {
        HelloApplication.switchRoot("login.fxml", 619, 434);
        if (selectedGender != null) {
            if (selectedGender.equals("Male")) {
                userPhoto.setImage(new Image("noun-male-5295254.png"));
            } else if (selectedGender.equals("Female")) {
                userPhoto.setImage(new Image("noun-female-5295234.png"));
            }
        }

    }
    @FXML
    private WebView webView = new WebView();

    public void initialize() {
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        account_name.setText(UserName);
        webView.setVisible(false);
        if (selectedGender != null) {
            if (selectedGender.equals("Male")) {
                userPhoto.setImage(new Image("noun-male-5295254.png"));
            } else if (selectedGender.equals("Female")) {
                userPhoto.setImage(new Image("noun-female-5295234.png"));
            }
        }



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


    public void mental_resources(ActionEvent event) {
        centerContent.setOpacity(0.8);
        webView.setVisible(true);
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://monerbondhu.com/");
    }


}