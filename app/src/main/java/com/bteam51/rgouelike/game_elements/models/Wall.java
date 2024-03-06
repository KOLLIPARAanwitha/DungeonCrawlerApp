package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.Tileable;

public class Wall implements Tileable {
    private int gridX;
    private int gridY;
    public Wall(int gX, int gY) {
        gridX = gX;
        gridY = gY;
    }

    public int getGridX() {
        return gridX;
    }
    public int getGridY() {
        return gridY;
    }
}
