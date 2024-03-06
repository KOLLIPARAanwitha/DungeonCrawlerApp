package com.bteam51.rgouelike.game_elements.models;

import android.view.KeyEvent;

import com.bteam51.rgouelike.utils.MovementStyle;

public class DefaultMovement implements MovementStyle {
    public DefaultMovement() {
        return;
    }

    public boolean attemptMove(Player player, Room room, int keyCode) {
        int row = player.getGridY();
        int col = player.getGridX();
        if (keyCode == KeyEvent.KEYCODE_W) {
            if (row - 1 >= 0 && room.getTile(row - 1, col).getContents() == null) {
                room.addTileEntry(null, row, col);
                room.addTileEntry(player, row - 1, col);
                player.setGridY(row - 1);
                return true;
            }
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_S) {
            if (row + 1 <= room.getHeight() - 1
                    && room.getTile(row + 1, col).getContents() == null) {
                room.addTileEntry(null, row, col);
                room.addTileEntry(player, row + 1, col);
                player.setGridY(row + 1);
                return true;
            }
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_A) {
            if (col - 1 >= 0 && room.getTile(row, col - 1).getContents() == null) {
                room.addTileEntry(null, row, col);
                room.addTileEntry(player, row, col - 1);
                player.setGridX(col - 1);
                return true;
            }
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_D) {
            if (col + 1 <= room.getWidth() - 1
                    && room.getTile(row, col + 1).getContents() == null) {
                room.addTileEntry(null, row, col);
                room.addTileEntry(player, row, col + 1);
                player.setGridX(col + 1);
                return true;
            }
            return false;
        }

        return false;
    }
}
