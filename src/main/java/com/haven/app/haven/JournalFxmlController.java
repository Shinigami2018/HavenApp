package com.haven.app.haven;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class JournalFxmlController {

    @FXML
    public TextArea journalTextArea;

    public final String defaultFilePath = "journal.txt";  // Default file

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
}