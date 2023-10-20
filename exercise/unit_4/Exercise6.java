package com.nhnacademy.unit_4_gui;

import javafx.scene.paint.Color;

public class Exercise6 {

    static final int ROWS = 40;
    static final int COLUMNS = 40;
    static final int SQUARE_SIZE = 10;

    static int currentRow;
    static int currentColumn;

    public static void main(String[] args) {
        Mosaic.setUse3DEffect(false);
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        fillWithBlack();
        currentRow = ROWS / 2; // start at center of window
        currentColumn = COLUMNS / 2;
        while (true) {
            changeToGradientGreenColor(currentRow, currentColumn);
            randomMove();
            Mosaic.delay(5);
        }
    }

    static void fillWithRandomColors() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                changeToRandomColor(row, column);
            }
        }
    }

    static void fillWithBlack() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Mosaic.setColor(row, column, Color.BLACK);
            }
        }
    }

    static void changeToRandomColor(int rowNum, int colNum) {
        int red = (int) (256 * Math.random());
        int green = (int) (256 * Math.random());
        int blue = (int) (256 * Math.random());
        Mosaic.setColor(rowNum, colNum, red, green, blue);
    }

    static void changeToGradientGreenColor(int rowNum, int colNum) {
        int red = 0;
        int green = Mosaic.getGreen(rowNum, colNum) + 25;
        if (green >= 255) {
            green = 0;
        }
        int blue = 0;
        Mosaic.setColor(rowNum, colNum, red, green, blue);
    }

    static void randomMove() {
        int directionNum;
        directionNum = (int) (4 * Math.random());
        switch (directionNum) {
            case 0:
                currentRow--;
                if (currentRow < 0) {
                    currentRow = ROWS - 1;
                }
                break;
            case 1:
                currentColumn++;
                if (currentColumn >= COLUMNS) {
                    currentColumn = 0;
                }
                break;
            case 2:
                currentRow++;
                if (currentRow >= ROWS) {
                    currentRow = 0;
                }
                break;
            case 3:
                currentColumn--;
                if (currentColumn < 0) {
                    currentRow = COLUMNS - 1;
                }
                break;
        }
    }
}