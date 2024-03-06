package org.group51.duncrawl.models.enemies;

import org.group51.duncrawl.models.Monster;
import org.group51.duncrawl.models.SimpleMovementStyle;
import org.group51.duncrawl.models.statuseffects.ConfusedEffect;

import sun.java2d.pipe.SpanShapeRenderer;

public class Jellyfish extends Monster {

    public Jellyfish() {
        super();
        movementTimer = 20;
        this.attack = 10;
        this.movementStyle = new SimpleMovementStyle();
        this.status = new ConfusedEffect();
    }

    public String getSpritePath() {
        return "sprites/enemies/jellyfish.png";
    }
}
