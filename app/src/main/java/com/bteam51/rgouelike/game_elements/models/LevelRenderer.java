package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.PositionCoordinate;

/**
 * Stores and manages the central game data.
 * Implemented as a Singleton.
 */
public class LevelRenderer {
    private static volatile LevelRenderer levelRenderer;

    private Entity[] entities;

    private int score;
    private long startTime;
    private Difficulty diff;
    private Player player;
    private double enemyDamageMultiplier;

    private Room curRoom;

    private Room roomOne = new RoomTypeOne();
    private Room roomTwo = new RoomTypeTwo();
    private Room roomThree = new RoomTypeThree();

    private Room levelExit = new LevelExit();


    private LevelRenderer() {
        player = Player.getPlayer();
        entities = new Entity[1];
        entities[0] = player;
        //add stuff later
    }

    public static LevelRenderer getLevelRenderer() {
        if (levelRenderer == null) {
            synchronized (LevelRenderer.class) {
                if (levelRenderer == null) {
                    levelRenderer = new LevelRenderer();
                }
            }
        }
        return levelRenderer;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(long currTime) {
        score = (int) (1000 - (currTime - startTime) / 100);
        score = (score < 0) ? 0 : score;
    }

    public void updateFrame() {
        updateScore(System.currentTimeMillis());
        for (Entity ent : entities) {
            ent.updateFrame();
        }
        //player.updateFrame();
        //checkExit();
    }

    public void setStartTime(long sTime) {
        startTime = sTime;
    }

    public void resetLevel(Difficulty gDiff) {
        diff = gDiff;
        switch (gDiff) {
        case EASY:
            enemyDamageMultiplier = 1;
            player.setHealth(100);
            break;
        case MEDIUM:
            enemyDamageMultiplier = 1.5;
            player.setHealth(90);
            break;
        case HARD:
            enemyDamageMultiplier = 2;
            player.setHealth(75);
            break;
        case ROGUELIKE:
            enemyDamageMultiplier = 3;
            player.setHealth(50);
            break;
        default:
        }
        player.setGridX(10);
        player.setGridY(2);
        setStartTime(System.currentTimeMillis()); // may start this later
    }

    public void setPlayerName(String gName) {
        player.setName(gName);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Difficulty getDifficulty() {
        return diff;
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public Room getCurRoom() {
        return curRoom;
    }

    public void setCurRoom(Room cRoom) {
        curRoom = cRoom;
        if (cRoom.getRoomId() == 3) {
            cRoom.setWestRoom(roomTwo);
        } else if (cRoom.getRoomId() == 2) {
            cRoom.setEastRoom(roomThree);
            cRoom.setNorthRoom(roomOne);
        } else if (cRoom.getRoomId() == 1) {
            cRoom.setSouthRoom(roomTwo);
            cRoom.setNorthRoom(levelExit);
        }
        player.setCurRoom(cRoom);
    }

    public void updateLastDir(int keyCode) {
        player.setLastDir(keyCode);
    }

    public void checkExit() {
        int[][] exits = curRoom.getExitZones();
        int exit = exits[player.getGridY()][player.getGridX()];
        Room newRoom;
        PositionCoordinate posC;


    }

}
