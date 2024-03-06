package com.bteam51.rgouelike;

import static org.junit.Assert.assertTrue;

import com.bteam51.rgouelike.game_elements.models.Difficulty;

import com.bteam51.rgouelike.game_elements.models.LevelRenderer;

import org.junit.Test;


public class HealthDifficultyUnitTest {
    LevelRenderer testLev = LevelRenderer.getLevelRenderer();
    int easyHealth;
    int rogueHealth;
    int mediumHealth;
    int hardHealth;

    @Test
    public void ExtremeHealthLevelTest() {
        testLev.resetLevel(Difficulty.EASY);
        easyHealth = testLev.getPlayerHealth();
        testLev.resetLevel(Difficulty.ROGUELIKE);
        rogueHealth = testLev.getPlayerHealth();
        assertTrue(rogueHealth < easyHealth);
    }

    @Test
    public void HealthLevelTest() {
        testLev.resetLevel(Difficulty.MEDIUM);
        mediumHealth = testLev.getPlayerHealth();
        testLev.resetLevel(Difficulty.HARD);
        hardHealth = testLev.getPlayerHealth();
        assertTrue(hardHealth < mediumHealth);
    }

}
