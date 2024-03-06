package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.PositionCoordinate;

public class RoomTypeThree extends Room {

    //1 = north, 2 = west, 3 = south; 4 = east;
    private static int[][] exitZones;
    private static Tile[][] defaultLayout;

    public RoomTypeThree() {
        super();
        backgroundImageId = 3;
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
                if (i == 0 || i > 5) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                if (j < 2 || j > 16) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                if (j < 8 && (i > 3 || i < 3)) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                exitZones[i][j] = 0;
            }
        }
        exitZones[3][2] = 2;

    }

    @Override
    public PositionCoordinate westEntrance() {
        return new PositionCoordinate(3, 2);
    }

    public int[][] getExitZones() {
        //return new int[10][10];
        return RoomTypeThree.exitZones;
    }
}
