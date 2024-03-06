package org.group51.duncrawl.models.enemies;

import org.group51.duncrawl.models.Monster;
import org.group51.duncrawl.models.RandomMovementStyle;
import org.group51.duncrawl.models.statuseffects.SlowEffect;

public class Seal extends Monster {
    public Seal() {
        super();
        movementTimer = 30;
        this.attack = 25;
        this.movementStyle = new RandomMovementStyle();
        this.status = new SlowEffect();
    }

    public String getSpritePath() {
        return "sprites/enemies/seal.png";
    }
}
