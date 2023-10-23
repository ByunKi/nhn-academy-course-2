package com.nhnacademy.unit_4_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.List;

public class Mosaic extends Application {

    private static Stage window;
    private static MosaicCanvas canvas;
    private static boolean use3DEffect = true;
    private static int mosaicRows;
    private static int mosaicCols;

    public static void open() {
        open(20, 20, 15, 15);
    }

    public static void open(int rows, int columns) {
        open(rows, columns, 15, 15);
    }

    public static void open(int rows, int columns, int blockWidth, int blockHeight) {
        if (window != null)
            return;
        mosaicRows = rows;
        mosaicCols = columns;
        new Thread(
                () -> launch(Mosaic.class, new String[] { "" + rows, "" + columns, "" +
                        blockWidth, "" + blockHeight }))
                .start();
        do {
            delay(100);
        } while (window == null || canvas == null);
    }

    public static void close() {
        if (window != null) {
            Platform.runLater(() -> window.close());
        }
    }

    public static boolean isOpen() {
        return (window != null);
    }

    public static void delay(int milliseconds) {
        if (milliseconds > 0) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
            }
        }
    }

    public static Color getColor(int row, int col) {
        if (canvas == null)
            return Color.BLACK;
        return canvas.getColor(row, col);
    }

    public static int getRed(int row, int col) {
        if (canvas == null)
            return 0;
        if (row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row,col) = (" + row + "," + col
                    + ") is not in the mosaic.");
        }
        return (int) (255 * canvas.getRed(row, col));
    }

    public static int getGreen(int row, int col) {
        if (canvas == null)
            return 0;
        if (row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row,col) = (" + row + "," + col
                    + ") is not in the mosaic.");
        }
        return (int) (255 * canvas.getGreen(row, col));
    }

    public static int getBlue(int row, int col) {
        if (canvas == null)
            return 0;
        if (row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row,col) = (" + row + "," + col
                    + ") is not in the mosaic.");
        }
        return (int) (255 * canvas.getBlue(row, col));
    }

    public static void setColor(int row, int col, Color c) {
        if (canvas == null)
            return;
        if (row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row,col) = (" + row + "," + col
                    + ") is not in the mosaic.");
        }
        canvas.setColor(row, col, c);
    }

    public static void setColor(int row, int col, int red, int green, int blue) {
        if (canvas == null)
            return;
        if (row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row,col) = (" + row + "," + col
                    + ") is not in the mosaic.");
        }
        canvas.setColor(row, col, red / 255.0, green / 255.0, blue / 255.0);
    }

    public static void fill(Color c) {
        canvas.fill(c);
    }

    public static void fill(int red, int green, int blue) {
        canvas.fill(red / 255.0, green / 255.0, blue / 255.0);
    }

    public static void fillRandomly() {
        canvas.fillRandomly();
    }

    public static void setUse3DEffect(boolean use3D) {
        use3DEffect = use3D;
        if (canvas != null) {
            canvas.setGroutingColor(use3DEffect ? Color.GRAY : null);
            canvas.setUse3D(use3DEffect);
        }
    }

    public boolean getUse3DEffect() {
        return use3DEffect;
    }

    public void start(Stage stage) {
        window = stage;
        List<String> params = getParameters().getUnnamed();
        if (params.size() != 4)
            canvas = new MosaicCanvas();
        else
            canvas = new MosaicCanvas(Integer.parseInt(params.get(0)),
                    Integer.parseInt(params.get(1)),
                    Integer.parseInt(params.get(2)), Integer.parseInt(params.get(3)));
        if (!use3DEffect)
            canvas.setGroutingColor(null);
        canvas.setUse3D(use3DEffect);
        canvas.forceRedraw();
        Pane pane = new Pane(canvas);
        StackPane root = new StackPane(pane);
        root.setStyle("-fx-border-width: 2px; -fx-border-color: #333");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        stage.setTitle("Mosaic");
        stage.setResizable(false);
        stage.show();
    }

}