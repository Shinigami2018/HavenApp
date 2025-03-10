package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class PersonalityFxmlController implements Initializable {
    public JFXButton sad_button;
    public JFXButton des_button;
    public JFXButton cur_button;
    public JFXButton joy_button;
    public JFXButton gra_button, exc_button, hop_button, con_button, ind_button, conf_button, nos_button;
    public JFXButton gui_button, sha_button, ang_button, fea_button, str_button, hopl_button, fra_button, empt_button;
    public JFXButton anx_button, wor_button, par_button, hel_button, ent_button, rel_button, pri_button, cont_button, sat_button;
    public JFXButton pea_button, emp_button, bor_button, lon_button;

    public Map<JFXButton, Integer> buttonScores = new HashMap<>();
    public int score = 0;
    public int clk = 0;
    public AnchorPane prompt1, prompt2;

    public Button next, finished;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize button scores
        initializeButton();

        // Add event handlers to buttons
        addEventHandlers();

        // Animate buttons
        animateButtons();
    }

    public void initializeButton() {
        buttonScores.put(hel_button, -2);
        buttonScores.put(par_button, -2);
        buttonScores.put(anx_button, -2);
        buttonScores.put(empt_button, -2);
        buttonScores.put(des_button, -2);
        buttonScores.put(wor_button, -2);

        buttonScores.put(sad_button, -1);
        buttonScores.put(sha_button, -1);
        buttonScores.put(hopl_button, -1);
        buttonScores.put(fra_button, -1);
        buttonScores.put(str_button, -1);
        buttonScores.put(fea_button, -1);
        buttonScores.put(ang_button, -1);
        buttonScores.put(gui_button, -1);
        buttonScores.put(lon_button, -1);

        buttonScores.put(cur_button, 1);
        buttonScores.put(nos_button, 1);
        buttonScores.put(conf_button, 1);
        buttonScores.put(ind_button, 1);
        buttonScores.put(bor_button, 1);

        buttonScores.put(joy_button, 2);
        buttonScores.put(gra_button, 2);
        buttonScores.put(exc_button, 2);
        buttonScores.put(hop_button, 2);
        buttonScores.put(con_button, 2);
        buttonScores.put(rel_button, 2);
        buttonScores.put(ent_button, 2);
        buttonScores.put(pri_button, 2);
        buttonScores.put(cont_button, 2);
        buttonScores.put(sat_button, 2);
        buttonScores.put(pea_button, 2);
        buttonScores.put(emp_button, 2);

        // Add scores for other buttons similarly
    }

    public void addEventHandlers() {
        hel_button.setOnAction(event -> handleButtonClick(hel_button));
        par_button.setOnAction(event -> handleButtonClick(par_button));
        anx_button.setOnAction(event -> handleButtonClick(anx_button));
        empt_button.setOnAction(event -> handleButtonClick(empt_button));
        des_button.setOnAction(event -> handleButtonClick(des_button));
        wor_button.setOnAction(event -> handleButtonClick(wor_button));
        sad_button.setOnAction(event -> handleButtonClick(sad_button));
        cur_button.setOnAction(event -> handleButtonClick(cur_button));
        joy_button.setOnAction(event -> handleButtonClick(joy_button));
        gra_button.setOnAction(event -> handleButtonClick(gra_button));
        exc_button.setOnAction(event -> handleButtonClick(exc_button));
        hop_button.setOnAction(event -> handleButtonClick(hop_button));
        con_button.setOnAction(event -> handleButtonClick(con_button));
        ind_button.setOnAction(event -> handleButtonClick(ind_button));
        conf_button.setOnAction(event -> handleButtonClick(conf_button));
        nos_button.setOnAction(event -> handleButtonClick(nos_button));
        gui_button.setOnAction(event -> handleButtonClick(gui_button));
        sha_button.setOnAction(event -> handleButtonClick(sha_button));
        ang_button.setOnAction(event -> handleButtonClick(ang_button));
        fea_button.setOnAction(event -> handleButtonClick(fea_button));
        str_button.setOnAction(event -> handleButtonClick(str_button));
        hopl_button.setOnAction(event -> handleButtonClick(hopl_button));
        fra_button.setOnAction(event -> handleButtonClick(fra_button));
        ent_button.setOnAction(event -> handleButtonClick(ent_button));
        rel_button.setOnAction(event -> handleButtonClick(rel_button));
        pri_button.setOnAction(event -> handleButtonClick(pri_button));
        cont_button.setOnAction(event -> handleButtonClick(cont_button));
        sat_button.setOnAction(event -> handleButtonClick(sat_button));
        pea_button.setOnAction(event -> handleButtonClick(pea_button));
        emp_button.setOnAction(event -> handleButtonClick(emp_button));
        bor_button.setOnAction(event -> handleButtonClick(bor_button));
        lon_button.setOnAction(event -> handleButtonClick(lon_button));
        // Add event handlers for other buttons similarly
    }

    public void handleButtonClick(JFXButton button) {
        System.out.println(button.getText() + " clicked");
        score += buttonScores.getOrDefault(button, 0);
        clk++;
        if (clk == 6) {
            switchToDashboard(score);
        }
    }

    public void switchToDashboard(int score) {
        try {
            System.out.println("Score: " + score);
            String name = HelloController.usename;
            HelloApplication.switchRoot("Dashboard.fxml", 1550, 830);
            saveScoreToDatabase(name, score);
            DashboardFxmlController.setScore(score); // eikhane ashole ki korte chacchis ARIFUL??? BOHUTKAHINI KOREO BUJLAM NA KAJ KI EITAR

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void saveScoreToDatabase(String username, int score) {
        Connection connection = null;
        PreparedStatement getUserIDStmt = null;
        ResultSet resultSet = null;
        PreparedStatement insertMoodStmt = null;

        try {
            System.out.println("Saving score to database");
            System.out.println("Username: " + username);
            System.out.println("Score: " + score);

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "124519@#maisk#");

            // Step 1: Get user ID from username
            if(username == null) {
               username = HelloController.UserName;
                System.out.println("Username: " + username);
            }
            getUserIDStmt = connection.prepareStatement("SELECT iduseraccounts FROM useraccounts WHERE Username = ?");
            getUserIDStmt.setString(1, username);
            resultSet = getUserIDStmt.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("iduseraccounts");  // Fetch user ID
                System.out.println("User ID: " + userID);

                // Step 2: Get current date (you can replace with a real date system)
                timefunction timefunction1 = new timefunction();
                int day = timefunction1.uday; // Get the day from your time function
                System.out.println("Day: " + day);

                // Step 3: Insert new score record into moodhistory
                insertMoodStmt = connection.prepareStatement(
                        "INSERT INTO moodhistory (user_id, date, moodscore) VALUES (?, ?, ?)"
                );
                insertMoodStmt.setInt(1, userID);
                insertMoodStmt.setInt(2, day);
                insertMoodStmt.setInt(3, score);
                int rowsInserted = insertMoodStmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Score saved successfully.");
                } else {
                    System.out.println("Failed to save score.");
                }
            } else {
                System.out.println("User not found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (getUserIDStmt != null) getUserIDStmt.close();
                if (insertMoodStmt != null) insertMoodStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
    }



    private void animateButtons() {
        animateButton(sad_button);
        animateButton(des_button);
        animateButton(cur_button);
        animateButton(joy_button);
        animateButton(gra_button);
        animateButton(exc_button);
        animateButton(hop_button);
        animateButton(con_button);
        animateButton(ind_button);
        animateButton(conf_button);
        animateButton(nos_button);
        animateButton(gui_button);
        animateButton(sha_button);
        animateButton(ang_button);
        animateButton(fea_button);
        animateButton(str_button);
        animateButton(hopl_button);
        animateButton(fra_button);
        animateButton(empt_button);
        animateButton(anx_button);
        animateButton(wor_button);
        animateButton(par_button);
        animateButton(hel_button);
        animateButton(ent_button);
        animateButton(rel_button);
        animateButton(pri_button);
        animateButton(cont_button);
        animateButton(sat_button);
        animateButton(pea_button);
        animateButton(emp_button);
        animateButton(bor_button);
        animateButton(lon_button);
    }

    private void animateButton(JFXButton button) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setNode(button);
        transition.setToY(-20);  // Move up by 20px
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
    }
}