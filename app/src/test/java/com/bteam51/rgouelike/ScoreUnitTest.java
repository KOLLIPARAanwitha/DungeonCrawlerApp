package com.bteam51.rgouelike;

import com.bteam51.rgouelike.game_elements.models.LevelRenderer;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class ScoreUnitTest {
    LevelRenderer testLev = LevelRenderer.getLevelRenderer();

    @Test
    public void largePosTest1() {
        testLev.setStartTime(0);
        testLev.updateScore(100000);
        assertEquals(testLev.getScore(), 0);
    }

    @Test
    public void smallPosScoreTest1() {
        testLev.setStartTime(100);
        testLev.updateScore(110);
        assertEquals(testLev.getScore(), 1000);
    }
}
