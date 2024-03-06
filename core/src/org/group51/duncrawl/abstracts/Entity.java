package org.group51.duncrawl.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.models.RandomMovementStyle;
import org.group51.duncrawl.models.Room;
import org.group51.duncrawl.statusmodels.StatusEffect;

public abstract class Entity {
    protected int health;
    protected int attack;
    protected int defense;
    protected int gridX;
    protected int gridY;
    protected Room room;
    protected StatusEffect status;
    protected boolean isActive;

    protected int movementTimer;

    protected MovementStyle movementStyle;

    public Entity() {
        this(100, 10, 10, 10);
    }

    public Entity(int gHealth, int gAttack, int gDefense, int gMovementTimer) {
        health = gHealth;
        attack = gAttack;
        defense = gDefense;
        movementTimer = gMovementTimer;
        movementStyle = new RandomMovementStyle();
        status = null;
        isActive = true;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public StatusEffect getStatus() {
        return status;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setStatus(StatusEffect stat) {
        status = stat;
    }

    public int getDefense() {
        return defense;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public Room getRoom() {
        return room;
    }

    public void setHealth(int gHealth) {
        health = gHealth;
    }

    public void setAttack(int gAttack) {
        attack = gAttack;
    }

    public void setDefense(int gDefense) {
        defense = gDefense;
    }

    public void setGridX(int gX) {
        gridX = gX;
    }

    public void setGridY(int gY) {
        gridY = gY;
    }

    public void setRoom(Room gRoom) {
        room = gRoom;
    }

    public void updateFrame() {
        //Functionality will be added later
        return;
    }

    public int getMovementTimer() {
        return movementTimer;
    }

    public void setMovementTimer(int moveTime){
        movementTimer = moveTime;
    }

    public MovementStyle getMovementStyle() {
        return movementStyle;
    }
}
