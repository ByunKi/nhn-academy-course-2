package com.nhnacademy.unit_4_gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.application.Platform;

public class MosaicCanvas extends Canvas {

    private int rows;
    private int columns;
    private Color defaultColor;
    private Color groutingColor;

    private boolean alwaysDrawGrouting;
    private boolean use3D = true;
    private boolean autopaint = true;

    private Color[][] grid;

    private GraphicsContext g; // The graphics context for drawing on this canvas.

    public MosaicCanvas() {
        this(42, 42);
    }

    public MosaicCanvas(int rows, int columns) {
        this(rows, columns, 16, 16);
    }

    public MosaicCanvas(int rows, int columns, int preferredBlockWidth, int preferredBlockHeight) {

        this.rows = rows;
        this.columns = columns;
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("Rows and Columns must be greater than zero.");
        preferredBlockHeight = Math.max(preferredBlockHeight, 5);
        preferredBlockWidth = Math.max(preferredBlockWidth, 5);
        grid = new Color[rows][columns];
        defaultColor = Color.BLACK;
        groutingColor = Color.GRAY;
        alwaysDrawGrouting = false;
        setWidth(preferredBlockWidth * columns);
        setHeight(preferredBlockHeight * rows);
        g = getGraphicsContext2D();
    }

    public void setDefaultColor(Color c) {
        if (c == null)
            c = Color.BLACK;
        if (!c.equals(defaultColor)) {
            defaultColor = c;
            forceRedraw();
        }
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setGroutingColor(Color c) {
        if (c == null || !c.equals(groutingColor)) {
            groutingColor = c;
            forceRedraw();
        }
    }

    public Color getGroutingColor(Color c) {
        return groutingColor;
    }

    public void setAlwaysDrawGrouting(boolean always) {
        if (alwaysDrawGrouting != always) {
            alwaysDrawGrouting = always;
            forceRedraw();
        }
    }

    public boolean getUse3D() {
        return use3D;
    }

    public void setUse3D(boolean use3D) {
        this.use3D = use3D;
    }

    public boolean getAlwaysDrawGrouting() {
        return alwaysDrawGrouting;
    }

    public void setGridSize(int rows, int columns, boolean preserveData) {
        if (rows <= 0 && columns <= 0)
            throw new IllegalArgumentException("Rows and columns must be positive.");
        Color[][] newGrid = new Color[rows][columns];
        if (preserveData) {
            int rowMax = Math.min(rows, this.rows);
            int colMax = Math.min(columns, this.columns);
            for (int r = 0; r < rowMax; r++)
                for (int c = 0; c < colMax; c++)
                    newGrid[r][c] = grid[r][c];
        }
        grid = newGrid;
        this.rows = rows;
        this.columns = columns;
        forceRedraw();
    }

    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }

    public Color getColor(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns)
            return grid[row][col];
        else
            return null;
    }

    public double getRed(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null)
            return grid[row][col].getRed();
        else
            return defaultColor.getRed();
    }

    public double getGreen(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null)
            return grid[row][col].getGreen();
        else
            return defaultColor.getGreen();
    }

    public double getBlue(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null)
            return grid[row][col].getBlue();
        else
            return defaultColor.getBlue();
    }

    public void setColor(int row, int col, Color c) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[row][col] = c;
            drawSquare(row, col);
        }
    }

    public void setColor(int row, int col, double red, double green, double blue) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            red = (red < 0) ? 0 : ((red > 1) ? 1 : red);
            green = (green < 0) ? 0 : ((green > 1) ? 1 : green);
            blue = (blue < 0) ? 0 : ((blue > 1) ? 1 : blue);
            grid[row][col] = Color.color(red, green, blue);
            drawSquare(row, col);
        }
    }

    public void setHSBColor(int row, int col,
            double hue, double saturation, double brightness) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            hue = (hue < 0) ? 0 : ((hue > 360) ? 360 : hue);
            saturation = (saturation < 0) ? 0 : ((saturation > 1) ? 1 : saturation);
            brightness = (brightness < 0) ? 0 : ((brightness > 1) ? 1 : brightness);
            grid[row][col] = Color.hsb(hue, saturation, brightness);
            drawSquare(row, col);
        }
    }

    public void fill(Color c) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                grid[i][j] = c;
        forceRedraw();
    }

    public void fill(double red, double green, double blue) {
        red = (red < 0) ? 0 : ((red > 1) ? 1 : red);
        green = (green < 0) ? 0 : ((green > 1) ? 1 : green);
        blue = (blue < 0) ? 0 : ((blue > 1) ? 1 : blue);
        fill(Color.color(red, green, blue));
    }

    public void fillRandomly() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Color.color(Math.random(), Math.random(), Math.random());
            }
        }
        forceRedraw();
    }

    public void clear() {
        fill(null);
    }

    public boolean getAutopaint() {
        return autopaint;
    }

    public void setAutopaint(boolean autopaint) {
        if (this.autopaint == autopaint)
            return;
        this.autopaint = autopaint;
        if (autopaint)
            forceRedraw();
    }

    final public void forceRedraw() {
        drawAllSquares();
    }

    public Object copyColorData() {
        Color[][] copy = new Color[rows][columns];
        copy[rows - 1] = new Color[columns + 4];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                copy[r][c] = grid[r][c];
        copy[rows - 1][columns] = defaultColor;
        copy[rows - 1][columns + 1] = groutingColor;
        copy[rows - 1][columns + 2] = (alwaysDrawGrouting ? Color.BLACK : null);
        copy[rows - 1][columns + 3] = (use3D ? Color.BLACK : null);
        return copy;
    }

    public boolean restoreColorData(Object data) {
        if (data == null || !(data instanceof Color[][]))
            return false;
        Color[][] newGrid = (Color[][]) data;
        int newRows = newGrid.length;
        if (newRows == 0 || newGrid[0].length == 0)
            return false;
        int newColumns = newGrid[0].length;
        for (int r = 1; r < newRows - 1; r++)
            if (newGrid[r].length != newColumns)
                return false;
        if (newGrid[newRows - 1].length != newColumns + 4)
            return false;
        if (newGrid[newRows - 1][newColumns] == null)
            return false;
        rows = newRows;
        columns = newColumns;
        grid = new Color[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                grid[i][j] = newGrid[i][j];
        defaultColor = newGrid[newRows - 1][newColumns];
        groutingColor = newGrid[newRows - 1][newColumns + 1];
        alwaysDrawGrouting = newGrid[newRows - 1][newColumns + 2] != null;
        use3D = newGrid[newRows - 1][newColumns + 3] != null;
        forceRedraw();
        return true;
    }

    public int xCoordToColumnNumber(double x) {
        if (x < 0)
            return -1;
        double colWidth = getWidth() / columns;
        int col = (int) (x / colWidth);
        if (col >= columns)
            return columns;
        else
            return col;
    }

    public int yCoordToRowNumber(double y) {
        if (y < 0)
            return -1;
        double rowHeight = getHeight() / rows;
        int row = (int) (y / rowHeight);
        if (row >= rows)
            return rows;
        else
            return row;
    }

    private void drawSquare(int row, int col) {
        if (autopaint) {
            if (Platform.isFxApplicationThread()) {
                drawOneSquare(row, col);
            } else {
                Platform.runLater(() -> drawOneSquare(row, col));
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    private void drawAllSquares() {
        if (Platform.isFxApplicationThread()) {
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < columns; c++)
                    drawOneSquare(r, c);
        } else {
            Platform.runLater(() -> {
                for (int r = 0; r < rows; r++)
                    for (int c = 0; c < columns; c++)
                        drawOneSquare(r, c);
            });
        }
        try { // to avoid overwhelming the application thread with draw operations...
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
    }

    private void drawOneSquare(int row, int col) {
        // only called from two previous methods
        double rowHeight = getHeight() / rows;
        double colWidth = getWidth() / columns;
        int y = (int) Math.round(rowHeight * row);
        int h = Math.max(1, (int) Math.round(rowHeight * (row + 1)) - y);
        int x = (int) Math.round(colWidth * col);
        int w = Math.max(1, (int) Math.round(colWidth * (col + 1)) - x);
        Color c = grid[row][col];
        g.setFill((c == null) ? defaultColor : c);
        if (groutingColor == null || (c == null && !alwaysDrawGrouting)) {
            if (!use3D || c == null)
                g.fillRect(x, y, w, h);
            else
                fill3DRect(c, x, y, w, h);
        } else {
            if (!use3D || c == null)
                g.fillRect(x + 1, y + 1, w - 2, h - 2);
            else
                fill3DRect(c, x + 1, y + 1, w - 2, h - 2);
            g.setStroke(groutingColor);
            g.strokeRect(x + 0.5, y + 0.5, w - 1, h - 1);
        }
    }

    private void fill3DRect(Color color, int x, int y, int width, int height) {
        double h = color.getHue();
        double b = color.getBrightness();
        double s = color.getSaturation();
        if (b > 0.8) {
            b = 0.8;
            g.setFill(Color.hsb(h, s, b));
        } else if (b < 0.2) {
            b = 0.2;
            g.setFill(Color.hsb(h, s, b));
        }
        g.fillRect(x, y, width, height);
        g.setStroke(Color.hsb(h, s, b + 0.2));
        g.strokeLine(x + 0.5, y + 0.5, x + width - 0.5, y + 0.5);
        g.strokeLine(x + 0.5, y + 0.5, x + 0.5, y + height - 0.5);
        g.setStroke(Color.hsb(h, s, b - 0.2));
        g.strokeLine(x + width - 0.5, y + 1.5, x + width - 0.5, y + height - 0.5);
        g.strokeLine(x + 1.5, y + height - 0.5, x + width - 0.5, y + height - 0.5);
    }

} // end class MosaicCanvas