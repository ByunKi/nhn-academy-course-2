package com.nhnacademy.unit_4_gui;

public class Exercise7 {

    final static int ROWS = 80;
    final static int COLUMNS = 80;
    final static int SQUARE_SIZE = 5;

    static int currentRow;
    static int currentColumn;

    public static void main(String[] args) {
        Mosaic.setUse3DEffect(false);
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        fillWithRandomColors();
        while (true) {
            currentRow = (int) (Math.random() * ROWS);
            currentColumn = (int) (Math.random() * COLUMNS);
            changeNeighborColor();
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

    static void changeToRandomColor(int rowNum, int colNum) {
        int red = (int) (256 * Math.random());
        int green = (int) (256 * Math.random());
        int blue = (int) (256 * Math.random());
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

    static void changeNeighborColor() {

        int red = Mosaic.getRed(currentRow, currentColumn);
        int green = Mosaic.getGreen(currentRow, currentColumn);
        int blue = Mosaic.getBlue(currentRow, currentColumn);

        switch ((int) (Math.random() * 4)) {
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
        Mosaic.setColor(currentRow, currentColumn, red, green, blue);
    }

}