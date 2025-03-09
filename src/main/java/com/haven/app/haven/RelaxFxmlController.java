package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RelaxFxmlController {

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private JFXButton blackColor, redColor, blueColor, greenColor, eraser, clearBtn;

    private GraphicsContext gc;
    private Color currentColor = Color.BLACK; // Default drawing color

    @FXML
    public void initialize() {
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        gc.setStroke(currentColor);
        gc.setLineWidth(5);

        // Event handlers for drawing
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        // Color Selection
        blackColor.setOnAction(e -> changeColor(Color.BLACK));
        redColor.setOnAction(e -> changeColor(Color.RED));
        blueColor.setOnAction(e -> changeColor(Color.BLUE));
        greenColor.setOnAction(e -> changeColor(Color.GREEN));
        eraser.setOnAction(e -> changeColor(Color.WHITE)); // Eraser acts as white

        // Clear Canvas
        clearBtn.setOnAction(e -> clearCanvas());
    }

    private void changeColor(Color color) {
        currentColor = color;
        gc.setStroke(color);
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    public void switch_to_dashboard(ActionEvent event) {
        HelloApplication.switchRoot("Dashboard.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }
    public void switch_to_journal(ActionEvent event) {
        HelloApplication.switchRoot("Journal.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }

    public void switch_to_report(ActionEvent event) {
        HelloApplication.switchRoot("Report.fxml",1550,830);
        HelloApplication.primaryStage.setMaximized(true);
    }
}
