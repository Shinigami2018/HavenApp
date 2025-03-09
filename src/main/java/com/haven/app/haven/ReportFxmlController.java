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
        applyHoverEffect(analyzeBtn);
        applyHoverEffect(exportBtn);
        loadFileData();
        makeResponsive();
        xAxis.setLabel("Days");
        yAxis.setLabel("Mood Score");

        // Create a data series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("User Mood");

        // Dummy data (Replace this with database values)
        series.getData().add(new XYChart.Data<>(1, 5));
        series.getData().add(new XYChart.Data<>(2, 7));
        series.getData().add(new XYChart.Data<>(3, 6));
        series.getData().add(new XYChart.Data<>(4, 8));
        series.getData().add(new XYChart.Data<>(5, 4));

        // Add series to the chart
        moodChart.getData().add(series);
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
