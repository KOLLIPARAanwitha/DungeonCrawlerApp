package com.bteam51.rgouelike.game_elements.models;

/**
 * Represents a generic enemy.
 * Is an Entity.
 */
public class Enemy extends Entity {

    private static int idValue; //used to give a distinct ID to each enemy.
    private int enemyId; //variable used to ensure enemies with the same stats are distinct
    public Enemy() {
        super();
    }
}
