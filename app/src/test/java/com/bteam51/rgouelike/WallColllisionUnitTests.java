package com.bteam51.rgouelike;

import static org.junit.Assert.assertEquals;

import android.view.KeyEvent;

import com.bteam51.rgouelike.game_elements.models.LevelRenderer;
import com.bteam51.rgouelike.game_elements.models.Player;
import com.bteam51.rgouelike.game_elements.models.RoomTypeThree;

import org.junit.Test;

public class WallColllisionUnitTests {
    LevelRenderer testLev = LevelRenderer.getLevelRenderer();
    Player player = Player.getPlayer();

    @Test
    public void wallTest1() {
        player.setGridX(10);
        player.setGridY(5);
        player.setLastDir(KeyEvent.KEYCODE_S);
        player.setMovementCooldown(0);
        player.setCurRoom(new RoomTypeThree());
        player.checkMove();
        assertEquals(player.getGridX(), 10);
        assertEquals(player.getGridY(), 5);
    }

    @Test
    public void wallTest2() {
        player.setGridX(10);
        player.setGridY(4);
        player.setLastDir(KeyEvent.KEYCODE_S);
        player.setMovementCooldown(0);
        player.setCurRoom(new RoomTypeThree());
        player.checkMove();
        assertEquals(player.getGridX(), 10);
        assertEquals(player.getGridY(), 5);
    }


}
