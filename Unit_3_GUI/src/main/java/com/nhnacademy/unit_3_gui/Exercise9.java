package com.nhnacademy.unit_3_gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Exercise9 extends Application {

    public void drawFrame(GraphicsContext g, int frameNumber, double elapsedSeconds, int width, int height) {

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height);

        for (int i = 100; i <= 500; i += 100) {
            g.strokeLine(0, i, 800, i);
        }

        int startVerticalPosition = 0;
        int[] frames = {800, 400, 200};
        Color[] colors = {Color.RED, Color.LIGHTGREEN, Color.BLUE, Color.LIGHTCYAN, Color.MAGENTA, Color.YELLOW};

        for (int i = 0; i < frames.length; i++, startVerticalPosition += 100) {
            int cyclicFrameNumber = frameNumber % frames[i];
            g.setFill(colors[i]);
            g.fillRect(cyclicFrameNumber, startVerticalPosition, 100, 100);
        }

        for (int i = 0; i < frames.length; i++, startVerticalPosition += 100) {
            int oscilationFrameNumber = frameNumber % (2  * frames[i]);
            if (oscilationFrameNumber > frames[i]) {
                oscilationFrameNumber = 2 * frames[i] - oscilationFrameNumber;
            }
            g.setFill(colors[i + 3]);
            g.fillRect(oscilationFrameNumber, startVerticalPosition, 100, 100);
        }

    }

    public void start(Stage stage) {
        int width = 800;
        int height = 600;
        Canvas canvas = new Canvas(width,height);
        drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Animation");
        stage.show();
        stage.setResizable(false);
        AnimationTimer anim = new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
                }
                else if (now - previousTime > 0.95e9/60) {
                    frameNum++;
                    drawFrame(canvas.getGraphicsContext2D(), frameNum, (now-startTime)/1e9, width, height);
                    previousTime = now;
                }
            }
        };
        anim.start();
    }

    public static void main(String[] args) {
        launch();
    }
}