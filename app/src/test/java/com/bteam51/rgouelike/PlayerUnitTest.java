package com.bteam51.rgouelike;

import org.junit.Test;

import static org.junit.Assert.*;

import com.bteam51.rgouelike.game_elements.models.Player;
import com.bteam51.rgouelike.game_elements.models.Entity;
import com.bteam51.rgouelike.game_elements.models.Enemy;
import com.bteam51.rgouelike.game_elements.models.RoomTypeThree;
import com.bteam51.rgouelike.game_elements.models.RoomTypeTwo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerUnitTest {
    Player testPlayer = Player.getPlayer();
    Enemy testEnemy = new Enemy();

    @Test(expected = IllegalArgumentException.class)
    public void nullPlayer() {
        testPlayer.setName(null);

    }

    @Test
    public void testSetters() {
        testPlayer.setName("test");
        testPlayer.setHealth(0);
        assertEquals(testPlayer.getName(), "test");
        assertEquals(testPlayer.getHealth(), 0);
    }

    @Test
    public void testEnemySuper() {
        assertEquals(testEnemy.getHealth(), 100);
    }

    @Test
    public void enemyNonSingleton() {
        Enemy second = new Enemy();
        second.setHealth(200);
        assertEquals(testEnemy.getHealth(), 100);
        assertEquals(second.getHealth(), 200);
    }
}
    public void testExits() {
        testPlayer.setGridX(2);
        testPlayer.setGridY(16);
        testPlayer.setCurRoom(new RoomTypeTwo());
        testPlayer.checkExit();
        assertEquals(testPlayer.getCurrRoom() instanceof RoomTypeThree, true);
    }
    @Test
    public void testRoomNull() {
        testPlayer.setGridX(2);
        testPlayer.setGridY(16);
        testPlayer.setCurRoom(new RoomTypeTwo());
        assertEquals(testPlayer.getCurrRoom() == null, false);
    }

    @Test
    public void testSetters() {
        testPlayer.setName("test");
        testPlayer.setHealth(0);
        assertEquals(testPlayer.getName(), "test");
        assertEquals(testPlayer.getHealth(), 0);
    }
}
