package org.group51.duncrawl.models.enemies;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.models.Monster;
import org.group51.duncrawl.models.RandomMovementStyle;
import org.group51.duncrawl.models.statuseffects.BleedEffect;

public class Crab extends Monster {
    public Crab() {
        super();
        movementTimer = 12;
        this.attack = 20;
        this.movementStyle = new RandomMovementStyle();
        this.status = new BleedEffect();
    }

    public String getSpritePath() {
        return "sprites/enemies/crab.png";
    }


}
