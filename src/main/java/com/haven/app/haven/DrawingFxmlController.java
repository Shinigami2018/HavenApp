package com.haven.app.haven;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawingFxmlController implements Initializable {
    public GraphicsContext gc;

    @FXML
    private Canvas drawing_board;

    @FXML
    private JFXButton white_marker;

    @FXML
    private JFXButton eraser;

    public void initialize(URL location, ResourceBundle resources) {
        drawing_board.setHeight(491);
        drawing_board.setWidth(1205);
        gc = drawing_board.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillOval(0, 0, drawing_board.getWidth(), drawing_board.getHeight());
        gc.setFill(Color.WHITE);

        drawing_board.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.fillOval(event.getX(),event.getY(),10,10);
            }
        });
    }
}
