package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

import static com.haven.app.haven.HelloApplication.primaryStage;

public class JournalFxmlController {

    @FXML
    public TextArea journalTextArea;

    public final String defaultFilePath = "journal.txt";  // Default file
    public VBox sidebar;
    public VBox Editor;
    public StackPane bottom;
    public JFXButton saveButton;
    public JFXButton loadButton;

    /**
     * Saves the journal text to a file chosen by the user.
     */
    @FXML
    public void saveJournalEntry() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Journal Entry");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(journalTextArea.getText());
                System.out.println("Journal saved successfully at " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads a previous journal entry from a file chosen by the user.
     */
    @FXML
    public void loadJournalEntry() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Journal Entry");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                journalTextArea.setText(content.toString());
                System.out.println("Journal loaded from " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void initialize() {
        // Make sidebar width adjust dynamically (take 20% of window width)
        sidebar.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.17));
        // Make the editor dynamically resize
        Editor.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.75)); // 75% of window width

        // Make the text area take full width inside Editor
        journalTextArea.prefWidthProperty().bind(Editor.widthProperty().subtract(20));//
        bottom.prefWidthProperty().bind(primaryStage.widthProperty().multiply(1));
        saveButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
        loadButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));// Add margin


    }


    public void refreshFileList(ActionEvent event) {
    }

    public void loadSelectedFile(MouseEvent mouseEvent) {
    }
}