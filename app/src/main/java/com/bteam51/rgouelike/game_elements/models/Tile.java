package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.Tileable;

/**
 * Represents a single space/tile
 * in a room.
 * Can store a Player, Enemy, Item,
 * Powerup, or Obstacle.
 */
public class Tile {
    private Tileable contents;
    public Tile() {
        this(null);
    }

    public Tile(Tileable gContents) {
        contents = gContents;
    }

    public Tileable getContents() {
        return contents;
    }

    public void setContents(Tileable gContents) {
        contents = gContents;
    }

    public boolean isEmpty() {
        return contents == null;
    }
}
