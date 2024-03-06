package com.bteam51.rgouelike.utils;

public class PositionCoordinate {
    private int row;
    private int col;
    public PositionCoordinate(int rows, int cols) {
        row = rows;
        col = cols;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
