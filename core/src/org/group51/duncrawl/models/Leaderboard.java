package org.group51.duncrawl.models;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class Leaderboard {
    private static volatile Leaderboard board;
    private TreeSet<PlayerScore> topPlayers;
    private int size;

    private PlayerScore lastAttempt;
    private Leaderboard() {
        topPlayers = new TreeSet<>();
        size = 0;
    }

    public static Leaderboard getBoard() {
        if (board == null) {
            synchronized (Leaderboard.class) {
                if (board == null) {
                    board = new Leaderboard();
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
        lastAttempt = playerScore;

        return playerScore;
    }

    public PlayerScore getLastAttempt() {
        return lastAttempt;
    }

    public static class PlayerScore implements Comparable<PlayerScore> {
        private String playerName;
        private int score;

        private Calendar attemptTime;


        public PlayerScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
            this.attemptTime = Calendar.getInstance();
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public String toString() {
            return "Player: " + this.playerName + " / Score: " + this.score
                    + " / Time: " + attemptTime.getTime();
        }



        public int compareTo(PlayerScore other) {
            return Integer.compare(other.score, this.score);
        }
    }
}
