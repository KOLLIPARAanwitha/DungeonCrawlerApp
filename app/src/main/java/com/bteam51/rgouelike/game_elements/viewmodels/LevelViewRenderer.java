package com.bteam51.rgouelike.game_elements.viewmodels;

import com.bteam51.rgouelike.game_elements.models.Difficulty;
import com.bteam51.rgouelike.game_elements.models.LevelRenderer;
import com.bteam51.rgouelike.game_elements.models.Player;
import com.bteam51.rgouelike.game_elements.models.Room;

/**
 * Manages the interaction between
 * the LevelRenderer and the
 * different rooms.
 * Implemented as a singleton.
 */
public class LevelViewRenderer {
    private static volatile LevelViewRenderer levelViewRenderer;
    private static int screenWidth;
    private static int screenHeight;

    private LevelRenderer levelRenderer;
    private int charSprite;
    private boolean roomChange;

    private LevelViewRenderer() {
        levelRenderer = LevelRenderer.getLevelRenderer();
    }

    public static LevelViewRenderer getLevelViewRenderer() {
        if (levelViewRenderer == null) {
            synchronized (LevelViewRenderer.class) {
                if (levelViewRenderer == null) {
                    levelViewRenderer = new LevelViewRenderer();
                }
            }
        }
        return levelViewRenderer;
    }

    public void resetLevel(Difficulty diff) {
        levelRenderer.resetLevel(diff);
    }

    public void setPlayerName(String playerName) {
        levelRenderer.setPlayerName(playerName);
    }

    public String getPlayerName() {
        return levelRenderer.getPlayerName();
    }

    public int getPlayerHealth() {
        return levelRenderer.getPlayerHealth();
    }

    public int getScore() {
        return levelRenderer.getScore();
    }

    public Difficulty getDifficulty() {
        return levelRenderer.getDifficulty();
    }

    public void startLevel() {
        levelRenderer.setStartTime(System.currentTimeMillis());
    }

    public void updateFrame() {
        levelRenderer.updateFrame();
        //updateScore(System.currentTimeMillis());
    }

    //temporary method until code is refactored to use sprite class
    public int getCharSprite() {
        return charSprite;
    }

    public void setCharSprite(int charSpr) {
        charSprite = charSpr;
    }

    public void setScreenWidth(int width) {
        screenWidth = width;
    }

    public void setScreenHeight(int height) {
        screenHeight = height;
    }

    public void updateLastDir(int keyCode) {
        levelRenderer.updateLastDir(keyCode);
    }

    public void setCurRoom(Room room) {
        roomChange = false;
        levelRenderer.setCurRoom(room);
    }

    public int getRoomId() {
        return levelRenderer.getCurRoom().getRoomId();
    }

    public double playerViewX() {
        Player player = Player.getPlayer();
        Room room = levelRenderer.getCurRoom();
        double roomWidth = room.getWidth();
        return (screenWidth / roomWidth) * player.getGridX();
    }

    public double playerViewY() {
        Player player = Player.getPlayer();
        Room room = levelRenderer.getCurRoom();
        double roomHeight = room.getHeight();
        return (screenHeight / roomHeight) * player.getGridY();
    }

    public void setRoomChange(boolean change) {
        roomChange = change;
    }

    public boolean isRoomChange() {
        return roomChange;
    }

}
