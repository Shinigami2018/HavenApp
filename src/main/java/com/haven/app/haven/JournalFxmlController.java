package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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
    public JFXListView<String> fileListView;  // Sidebar list

    public final String journalDirectory = "journals";  // Folder to store journal files



    @FXML
    public void saveJournalEntry() {
        try {
            File dir = new File(journalDirectory);
            if (!dir.exists()) dir.mkdir();  // Create the directory if not exists

            String fileName = "Journal_" + System.currentTimeMillis() + ".txt";
            File file = new File(dir, fileName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(journalTextArea.getText());
            writer.close();

            System.out.println("Journal saved: " + fileName);
            refreshFileList();  // Update sidebar list

        } catch (IOException e) {
            e.printStackTrace();
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
        refreshFileList();
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


    @FXML
    private void loadSelectedFile() {
        String selectedFile = fileListView.getSelectionModel().getSelectedItem();
        if (selectedFile == null) return;

        File file = new File(journalDirectory, selectedFile);
        if (file.exists()) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                journalTextArea.setText(content);
                System.out.println("Loaded file: " + selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Refreshes the ListView with available journal files.
     */
    @FXML
    private void refreshFileList() {
        File dir = new File(journalDirectory);
        if (!dir.exists()) return;

        List<String> files = Arrays.asList(dir.list((d, name) -> name.endsWith(".txt")));
        fileListView.getItems().setAll(files);
    }
}