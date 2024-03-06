package org.group51.duncrawl.models;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.Entity;

public abstract class Monster extends Entity implements Enemy {

    public Monster() {

    }
    @Override
    public void update() {
        return;
    }

    public abstract String getSpritePath();

}
