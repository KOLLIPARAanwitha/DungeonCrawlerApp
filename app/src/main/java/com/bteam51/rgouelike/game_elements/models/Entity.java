package com.bteam51.rgouelike.game_elements.models;

import com.bteam51.rgouelike.utils.Tileable;

/**
 * Represents a generic Entity
 * in the game.
 * Entities can move and have
 * stats such as health.
 * Entites are Tilable
 * and exist within rooms.
 */
public class Entity implements Tileable {
    protected int health;
    protected int attack;
    protected int defense;
    protected int gridX;
    protected int gridY;
    protected Room room;

    public Entity() {
        this(100, 10, 10);
    }

    public Entity(int gHealth, int gAttack, int gDefense) {
        health = gHealth;
        attack = gAttack;
        defense = gDefense;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
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
}
