package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;



public class DashboardFxmlController {
    @FXML
    public Button user_button;
    @FXML
    public Button music_prev;
    @FXML
    public Button music_next;
    @FXML
    public Button music_play;
    // Make sure this matches the fx:id in your FXML

    @FXML
    public ImageView profileImage;
    @FXML
    public  Label Score=new Label();
    @FXML
    public Label stt=new Label();
    @FXML
    public Label dis=new Label();

    @FXML
    private ProgressBar progressBar=new ProgressBar();
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @FXML
    private NumberAxis xAxis = new NumberAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    @FXML
    private XYChart<Number, Number> moodChart = new LineChart<>(xAxis, yAxis);

    public static void setScore(int score) {
        String ss=String.valueOf(score);
        System.out.println(ss);

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

        dis.setText("Your Score is :");
        String nam=HelloController.UserName;
        System.out.println(nam);

        int scr = getUserScore(nam);

        Score.setText(String.valueOf(scr));


        System.out.println(scr);
        if(scr<=0)
        {    progressBar.setProgress(0);
            System.out.println(scr+"happy");
            stt.setText("STAY HAPPY");
        }
        else if(scr<=6)
        {
            double progress = scr / 100.0; // Assuming the score is out of 100
            progressBar.setProgress(progress);
            stt.setText("Cheer UP");
        }

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

        // Set the string to display
        messageLabel.setText("Welcome to the Dashboard!");

        // Apply animations
        applyTypewriterEffect(messageLabel, displayRandomQuote()+" ");

        quotebox.setFillHeight(true);
        //  graph
        xAxis.setLabel("Days");
        yAxis.setLabel("Mood Score");

<<<<<<< Updated upstream
        // Create a data series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();


        // Fetch data from the database and populate the series
        fetchDataAndPopulateSeries(series);

        // Add series to the chart
        moodChart.getData().add(series);

    }
    public static int getUserScore(String username) {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        if (connectDB == null) {
            System.out.println("Database connection failed.");
            return -1; // Return an invalid score
        }

        String query = "SELECT date,moodscore FROM moodhistory WHERE user_id = ? ORDER BY date ASC";
        int userID = getUserID(); // Replace with actual method to get the current user ID
        System.out.println(userID);
        int score = 0;
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt("moodscore");
            }

            resultSet.close();
            preparedStatement.close();
            connectDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return score;
    }

    public static int getUserID() {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        if (connectDB == null) {
            System.out.println("Database connection failed.");
            return -1; // Return an invalid user ID
        }
        System.out.println( HelloController.UserName );
        String query = "SELECT iduseraccounts FROM useraccounts WHERE Username = ?";
        int userID =-1;

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, HelloController.UserName );

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userID = resultSet.getInt("iduseraccounts");
                System.out.println(userID);
            }

            resultSet.close();
            preparedStatement.close();
            connectDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }


    private void fetchDataAndPopulateSeries(XYChart.Series<Number, Number> series) {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        if (connectDB == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String query = "SELECT date,moodscore FROM moodhistory WHERE user_id = ? ORDER BY date ASC";
        int userID = getUserID(); // Replace with actual method to get the current user ID
        System.out.println(userID);
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int dat = resultSet.getInt("date");
                System.out.println("hi"+dat);
                int moodScor = resultSet.getInt("moodscore");
                System.out.println("bye"+moodScor);
                series.getData().add(new XYChart.Data<>(dat, moodScor));
            }

            resultSet.close();
            preparedStatement.close();
            connectDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
=======
        setHostServices(hostServices);

>>>>>>> Stashed changes
    }



    private static final String[] QUOTES = {
            "Believe you can and you're halfway there.",
            "Keep your face always toward the sunshine, and shadows will fall behind you.",
            "You are capable of amazing things.",
            "Difficulties in life are intended to make us better, not bitter.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "Happiness is not by chance, but by choice.",
            "Start where you are. Use what you have. Do what you can.",
            "Every day may not be good, but there's something good in every day.",
            "Do what you can, with what you have, where you are.",
            "Act as if what you do makes a difference. It does.",
            "Success is not the key to happiness. Happiness is the key to success."
    };

    public String displayRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(QUOTES.length);
        return QUOTES[index];
    }



    @FXML
    private Label messageLabel = new Label(); // Label to display the string

    @FXML
    HBox quotebox = new HBox(messageLabel);// Container for the quote

    private void applyTypewriterEffect(Label label, String text) {
        final int[] index = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (index[0] <= text.length()) {
                label.setText(text.substring(0, index[0])); // Reveal one character at a time
                index[0]++;
            }
        }));
        timeline.setCycleCount(text.length()); // Number of characters
        timeline.play();
    }

    public HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


    @FXML
    private Button game_button;

    private void openWebsite(String url) {
        if (hostServices != null) {
            hostServices.showDocument(url);
        } else {
            System.out.println("HostServices is not available.");
        }
    }

    @FXML
    private void play_games(ActionEvent event) {
        openWebsite("https://www.google.com");
    }




}