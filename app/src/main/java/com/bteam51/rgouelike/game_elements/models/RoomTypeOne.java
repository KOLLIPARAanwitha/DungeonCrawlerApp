package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.PositionCoordinate;

//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;

public class RoomTypeOne extends Room {

    private static int[][] exitZones;
    private static Tile[][] defaultLayout;
    public RoomTypeOne() {
        super();
        backgroundImageId = 1;
        //if (defaultLayout == null) {
        //    createDefaultLayout(height, width);
        //}
        createDefaultLayout(height, width);
        layout = defaultLayout;
    }



    public static void createDefaultLayout(int rows, int cols) {
        defaultLayout = new Tile[rows][cols];
        exitZones = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                defaultLayout[i][j] = new Tile();
                if (i == 0 || i > 6) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                if (j < 2 || j > 15) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                if (j >= 12 && i < 5) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                exitZones[i][j] = 0;
            }
            exitZones[6][10] = 3;
            exitZones[1][5] = 1;
        }

    }
    public int[][] getExitZones() {
        return exitZones;
    }

    public PositionCoordinate southEntrance() {
        return new PositionCoordinate(6, 10);
    }
}
