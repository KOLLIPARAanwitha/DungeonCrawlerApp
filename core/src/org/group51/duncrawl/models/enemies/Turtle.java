package org.group51.duncrawl.models.enemies;

import org.group51.duncrawl.models.Monster;
import org.group51.duncrawl.models.SimpleMovementStyle;

public class Turtle extends Monster {
    public Turtle() {
        super();
        movementTimer = 50;
        this.attack = 30;
        this.movementStyle = new SimpleMovementStyle();
    }

    public String getSpritePath() {
        return "sprites/enemies/turtle.png";
    }
}
