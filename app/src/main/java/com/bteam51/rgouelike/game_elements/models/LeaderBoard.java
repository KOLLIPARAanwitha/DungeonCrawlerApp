package com.bteam51.rgouelike.game_elements.models;

import android.os.Build;

import java.time.LocalDateTime;
//import java.time.chrono.ChronoLocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class LeaderBoard {
    private static volatile LeaderBoard board;
    private TreeSet<PlayerScore> topPlayers;
    private int size;
    private LeaderBoard() {
        topPlayers = new TreeSet<>();
        size = 0;
    }

    public static LeaderBoard getBoard() {
        if (board == null) {
            synchronized (LeaderBoard.class) {
                if (board == null) {
                    board = new LeaderBoard();
                }
            }
        }
        return board;
    }

    public PlayerScore[] getTopK(int amt) {
        int trueAmt = amt;
        PlayerScore[] returnArr = new PlayerScore[amt];
        //This defaults to a post-order traversal. DO NOT USE Descending
        Iterator it = topPlayers.iterator();
        if (amt > topPlayers.size()) {
            trueAmt = this.size();
        }
        int i = 0;
        while (it.hasNext() && i < trueAmt) {
            PlayerScore curr = (PlayerScore) it.next();
            returnArr[i] = curr;
            i++;
        }
        return returnArr;
    }

    public int size() {
        return topPlayers.size();
    }

    public PlayerScore addPlayerScore(String playerName, int score) {
        if (playerName == null) {
            throw new IllegalArgumentException("Player Name Can't Be Null!");
        }
        PlayerScore playerScore = new PlayerScore(playerName, score);
        topPlayers.add(playerScore);

        return playerScore;
    }

    public static class PlayerScore implements Comparable<PlayerScore> {
        private String playerName;
        private int score;

        private LocalDateTime attemptTime;

        public PlayerScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.attemptTime = LocalDateTime.now();
            }
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public String toString() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return "Player: " + this.playerName + " / Score: " + this.score
                        + " / Time: " + attemptTime.getMonth() + " " + attemptTime.getDayOfMonth()
                        + "th " + attemptTime.getHour() + ":" + attemptTime.getMinute();
            }
            return "";
        }



        public int compareTo(PlayerScore other) {
            return Integer.compare(other.score, this.score);
        }
    }
}
