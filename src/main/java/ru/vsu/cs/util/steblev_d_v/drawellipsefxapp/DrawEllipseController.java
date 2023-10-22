package ru.vsu.cs.util.steblev_d_v.drawellipsefxapp;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

import static java.lang.Math.round;


public class DrawEllipseController {

    private double rX;
    private double rY;
    private Color color;
    private double x;
    private double y;

    @FXML
    AnchorPane anchorPane;


    @FXML
    public TextField RX;
    @FXML
    public TextField RY;

    @FXML
    public Button rXButton;
    @FXML
    public Button rYButton;
    @FXML
    public Button clearButton;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    Canvas canvas;

    @FXML
    public Button drawButton;


    @FXML
    private void initialize() {

//
//        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue)
//                -> canvas.setWidth(newValue.doubleValue()));
//        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue)
//                -> canvas.setHeight(newValue.doubleValue()));

        rXButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    System.out.println("ClickRX");
                    rX = Integer.parseInt(RX.getText());
                    System.out.println(rX);

                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect data type entered!");
                }
            }
        });

        rYButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    System.out.println("ClickRY");
                    rY = Integer.parseInt(RY.getText());
                    System.out.println(rY);

                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect data type entered!");
                }

            }
        });

        colorPicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                color = colorPicker.getValue();
                System.out.println(color);
            }
        });

        canvas.setOnMouseClicked(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            System.out.println(x);
            System.out.println(y);
        });


        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                draw(gc, rX, rY, x, y, color);
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }
        });
    }


    public void draw(GraphicsContext gc, double rX, double rY, double xc, double yc, Color color) {
        canvas.getGraphicsContext2D().setStroke(color);

        double dx, dy, d1, d2, x, y;
        x = 0;
        y = rY;

        // Initial decision parameter of region 1
        d1 = (rY * rY) - (rX * rX * rY) + (0.25 * rX * rX);
        dx = 2 * rY * rY * x;
        dy = 2 * rX * rX * y;


        // For region 1
        while (dx < dy) {

            // Print points based on 4-way symmetry
            gc.strokeOval(round(x + xc), round(y + yc), 1, 1);
            gc.strokeOval(round(-x + xc), round(y + yc), 1, 1);
            gc.strokeOval(round(x + xc), round(-y + yc), 1, 1);
            gc.strokeOval(round(-x + xc), round(-y + yc), 1, 1);


            if (d1 < 0) {
                x++;
                dx = dx + (2 * rY * rY);
                d1 = d1 + dx + (rY * rY);
            } else {
                x++;
                y--;
                dx = dx + (2 * rY * rY);
                dy = dy - (2 * rX * rX);
                d1 = d1 + dx - dy + (rY * rY);
            }
        }

        // Decision parameter of region 2
        d2 = ((rY * rY) * ((x + 0.5) * (x + 0.5)))
                + ((rX * rX) * ((y - 1) * (y - 1)))
                - (rX * rX * rY * rY);

        // Plotting points of region 2
        while (y >= 0) {
            gc.strokeOval(round(x + xc), round(y + yc), 1, 1);
            gc.strokeOval(round(-x + xc), round(y + yc), 1, 1);
            gc.strokeOval(round(x + xc), round(-y + yc), 1, 1);
            gc.strokeOval(round(-x + xc), round(-y + yc), 1, 1);

            // Checking and updating parameter
            // value based on algorithm
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rX * rX);
                d2 = d2 + (rX * rX) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * rY * rY);
                dy = dy - (2 * rX * rX);
                d2 = d2 + dx - dy + (rX * rX);
            }
        }

    }
}















