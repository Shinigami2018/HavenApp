package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

public class ReportFxmlController {

    @FXML
    private HBox topBar, bottomBar;

    @FXML
    private VBox sideBar, rightPanel;

    @FXML
    private StackPane graphContainer;

    @FXML
    private ListView<String> fileList;

    @FXML
    private JFXButton analyzeBtn, exportBtn;

    @FXML
    public void initialize() {
        applyHoverEffect(analyzeBtn);
        applyHoverEffect(exportBtn);
        loadFileData();
        makeResponsive();
    }

    private void applyHoverEffect(JFXButton button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #FFA86B; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #FFD2A0; -fx-text-fill: black;"));
    }

    private void loadFileData() {
        fileList.getItems().addAll("Report 1 - Score: 78", "Report 2 - Score: 85", "Report 3 - Score: 90");
    }

    private void makeResponsive() {
        sideBar.prefWidthProperty().bind(graphContainer.widthProperty().multiply(0.25));
        rightPanel.prefWidthProperty().bind(graphContainer.widthProperty().multiply(0.25));
        fileList.prefHeightProperty().bind(sideBar.heightProperty().multiply(0.85));
        graphContainer.prefHeightProperty().bind(graphContainer.widthProperty().multiply(0.75));
    }
}
