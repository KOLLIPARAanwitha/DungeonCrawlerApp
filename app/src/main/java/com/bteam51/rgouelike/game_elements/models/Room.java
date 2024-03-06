package com.bteam51.rgouelike.game_elements.models;

//import android.graphics.drawable.Drawable;

import com.bteam51.rgouelike.utils.PositionCoordinate;
import com.bteam51.rgouelike.utils.Tileable;

/**
 * Stores the data associated with a room.
 * Stores references to the adjacent rooms.
 */
public class Room {
    protected Tile[][] layout;
    //Likely will add a HashSet<Entity> as well
    protected Room northRoom;
    protected Room southRoom;
    protected Room eastRoom;
    protected Room westRoom;

    protected int width;
    protected int height;
    protected int backgroundImageId;

    public Room() {
        this(10, 20);
    }
    public Room(int rows, int cols) {
        //The # of rows is effectively the height.
        //The # of cols is effectively the width.
        layout = new Tile[rows][cols];
        width = cols;
        height = rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                layout[i][j] = new Tile();
            }
        }
    }

    public Room getNorthRoom() {
        return northRoom;
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public Room getWestRoom() {
        return westRoom;
    }

    public Tile[][] getLayout() {
        return layout;
    }

    public void addTileEntry(Tileable entry, int rows, int cols) {
        layout[rows][cols].setContents(entry);
    }

    public Tile getTile(int rows, int cols) {
        return layout[rows][cols];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoomId() {
        return backgroundImageId;
    }

    public void setNorthRoom(Room nRoom) {
        northRoom = nRoom;
    }

    public void setSouthRoom(Room sRoom) {
        southRoom = sRoom;
    }

    public void setEastRoom(Room eRoom) {
        eastRoom = eRoom;
    }

    public void setWestRoom(Room wRoom) {
        westRoom = wRoom;
    }

    public PositionCoordinate northEntrance() {
        return null;
    }
    public PositionCoordinate southEntrance() {
        return null;
    }
    public PositionCoordinate eastEntrance() {
        return null;
    }
    public PositionCoordinate westEntrance() {
        return null;
    }

    public int[][] getExitZones() {
        return new int[10][20];
    }
}
