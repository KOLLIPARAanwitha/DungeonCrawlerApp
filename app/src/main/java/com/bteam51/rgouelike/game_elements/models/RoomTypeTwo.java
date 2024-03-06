package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.PositionCoordinate;

public class RoomTypeTwo extends Room {
    private static int[][] exitZones;
    private static Tile[][] defaultLayout;

    public RoomTypeTwo() {
        super();
        backgroundImageId = 2;
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
                if ((j > 12 || j < 6) && i > 3) {
                    defaultLayout[i][j].setContents(new Wall(j, i));
                }
                exitZones[i][j] = 0;
            }
        }
        exitZones[2][16] = 4;
        //exitZones[1][8] = 1;
        exitZones[1][9] = 1;


    }

    public int[][] getExitZones() {
        return exitZones;
    }

    public PositionCoordinate eastEntrance() {
        return new PositionCoordinate(2, 16);
    }

    public PositionCoordinate northEntrance() {
        return new PositionCoordinate(1, 9);
    }
}
