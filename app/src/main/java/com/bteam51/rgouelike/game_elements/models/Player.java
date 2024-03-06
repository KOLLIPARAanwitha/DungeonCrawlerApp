package com.bteam51.rgouelike.game_elements.models;

//import com.bteam51.rgouelike.utils.Sprite;

import android.view.KeyEvent;

import com.bteam51.rgouelike.game_elements.viewmodels.LevelViewRenderer;
import com.bteam51.rgouelike.utils.MovementStyle;
import com.bteam51.rgouelike.utils.PositionCoordinate;

/**
 * Stores the player attributes
 * and handles player logic.
 * Implemented as a Singleton.
 * Is an Entity.
 */
public class Player extends Entity {
    private static volatile Player player;
    private int health;
    private String name;
    private int score;
    private int movementCooldown;
    private Room curRoom;

    private int lastDir;

    private MovementStyle moveStyle;

    private Player(String gName, int gHealth) {
        super(gHealth, 10, 10);
        name = gName;
        moveStyle = new DefaultMovement();
        movementCooldown = 0;
    }

    private Player() {
        this("Asian", 100);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String gName) {
        if (gName == null) {
            throw new IllegalArgumentException("Player Name Can't Be Null!");
        }
        name = gName;
    }

    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public void setLastDir(int last) {
        lastDir = last;
    }

    public void setCurRoom(Room room) {
        curRoom = room;
    }

    public Room getCurrRoom() {
        return curRoom;
    }

    public void checkMove() {

        if (movementCooldown > 0) {
            movementCooldown--;
            return;
        }
        if (moveStyle.attemptMove(this, curRoom, lastDir)) {
            movementCooldown = 2;
            lastDir = -1;
        }

    }

    public void updateFrame() {
        checkMove();
        checkExit();
    }

    public void setMovementCooldown(int mC) {
        movementCooldown = mC;
    }

    public void checkExit() {
        LevelRenderer levR = LevelRenderer.getLevelRenderer();
        if (curRoom == null) {
            return;
        }
        int[][] exits = curRoom.getExitZones();

        if (movementCooldown == 0) {
            int exit = exits[gridY][gridX];
            Room newRoom;
            PositionCoordinate posC;
            LevelViewRenderer levV = LevelViewRenderer.getLevelViewRenderer();

            if (exit == 1 && lastDir == KeyEvent.KEYCODE_W) {
                newRoom = curRoom.getNorthRoom();
                if (newRoom.getRoomId() == 4) {
                    lastDir = -1;
                    movementCooldown = 2;
                    levR.setCurRoom(newRoom);
                    levV.setRoomChange(true);
                    return;
                }
                posC = newRoom.southEntrance();
                gridX = posC.getCol();
                gridY = posC.getRow();
                curRoom = newRoom;
                lastDir = -1;
                movementCooldown = 2;
                levR.setCurRoom(newRoom);
                levV.setRoomChange(true);
                return;
            } else if (exit == 2 && lastDir == KeyEvent.KEYCODE_A) {
                newRoom = curRoom.getWestRoom();
                posC = newRoom.eastEntrance();
                gridX = posC.getCol();
                gridY = posC.getRow();
                curRoom = newRoom;
                lastDir = -1;
                movementCooldown = 2;
                levR.setCurRoom(newRoom);
                levV.setRoomChange(true);
                return;
            } else if (exit == 3 && lastDir == KeyEvent.KEYCODE_S) {
                newRoom = curRoom.getSouthRoom();
                posC = newRoom.northEntrance();
                gridX = posC.getCol();
                gridY = posC.getRow();
                curRoom = newRoom;
                lastDir = -1;
                movementCooldown = 2;
                levR.setCurRoom(newRoom);
                levV.setRoomChange(true);
                return;
            } else if (exit == 4 && lastDir == KeyEvent.KEYCODE_D) {
                newRoom = curRoom.getEastRoom();
                posC = newRoom.westEntrance();
                gridX = posC.getCol();
                gridY = posC.getRow();
                curRoom = newRoom;
                lastDir = -1;
                movementCooldown = 2;
                levR.setCurRoom(newRoom);
                levV.setRoomChange(true);
                return;
            }





        }



    }


}
