package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportFxmlController {

    @FXML
    private HBox Menubuttons, bottomBar;

    @FXML
    private VBox sideBar, rightPanel;

    @FXML
    private StackPane graphContainer;

    @FXML
    private ListView<String> fileList;

    @FXML
    private JFXButton analyzeBtn, exportBtn;

    @FXML
    private LineChart<Number, Number> moodChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        xAxis.setLabel("Days");
        yAxis.setLabel("Mood Score");

        // Create a data series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("User Mood");

        // Fetch data from the database and populate the series
        fetchDataAndPopulateSeries(series);

        // Add series to the chart
        moodChart.getData().add(series);
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


    private void applyHoverEffect(JFXButton button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #FFA86B; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #FFD2A0; -fx-text-fill: black;"));
    }

    private void loadFileData() {
        fileList.getItems().addAll("Report 1 - Score: 78", "Report 2 - Score: 85", "Report 3 - Score: 90");
    }

    public void switch_to_dashboard(ActionEvent event) {
        HelloApplication.switchRoot("Dashboard.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
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

    private void makeResponsive() {
        sideBar.prefWidthProperty().bind(graphContainer.widthProperty().multiply(0.25));
        rightPanel.prefWidthProperty().bind(graphContainer.widthProperty().multiply(0.25));
        fileList.prefHeightProperty().bind(sideBar.heightProperty().multiply(0.85));
        graphContainer.prefHeightProperty().bind(graphContainer.widthProperty().multiply(0.75));
    }




}
