package com.bteam51.rgouelike.utils;

import com.bteam51.rgouelike.game_elements.models.Player;
import com.bteam51.rgouelike.game_elements.models.Room;

public interface MovementStyle {
    public boolean attemptMove(Player player, Room room, int keyCode);
}
