package com.bteam51.rgouelike;

import static org.junit.Assert.assertEquals;

import com.bteam51.rgouelike.game_elements.models.LeaderBoard;

import org.junit.Test;

import java.util.Arrays;

public class LeaderboardUnitTest {
    LeaderBoard testLeader = LeaderBoard.getBoard();

    @Test
    public void leaderBoardMulti() {
        testLeader.addPlayerScore("Gem", 1);
        testLeader.addPlayerScore("Atlas", 11);
        testLeader.addPlayerScore("Lily", 17);
        testLeader.addPlayerScore("Josh", 6);
        LeaderBoard.PlayerScore[] traversal = testLeader.getTopK(5);
        System.out.println(Arrays.toString(traversal));
        assertEquals(traversal[0].getScore(), 17);
        assertEquals(traversal[1].getScore(), 11);
        assertEquals(traversal[2].getScore(), 6);
        assertEquals(traversal[3].getScore(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leaderBoardAddNull() {
        testLeader.addPlayerScore(null, 1);
    }

    @Test
    public void doublePlayerName() {
        testLeader.addPlayerScore("Gem", 1);
        testLeader.addPlayerScore("Gem", 3);
        LeaderBoard.PlayerScore[] traversal = testLeader.getTopK(3);
        System.out.println(Arrays.toString(traversal));
        assertEquals(traversal[0].getScore(), 3);
        assertEquals(traversal[1].getScore(), 1);
    }

    @Test
    public void getsTopK() {
        testLeader.addPlayerScore("Eric", 1);
        testLeader.addPlayerScore("Tripp", 11);
        testLeader.addPlayerScore("Jeannie", 17);
        testLeader.addPlayerScore("Elliot", 6);
        testLeader.addPlayerScore("Anwitha", 3);
        LeaderBoard.PlayerScore[] traversal = testLeader.getTopK(3);
        assertEquals(traversal[0].getScore(), 17);
        assertEquals(traversal[1].getScore(), 11);
        assertEquals(traversal[2].getScore(), 6);
        assertEquals(traversal.length, 3);
    }
}