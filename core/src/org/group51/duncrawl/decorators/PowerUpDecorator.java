package org.group51.duncrawl.decorators;

import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.statusmodels.StatusEffect;

public abstract class PowerUpDecorator implements PowerUp {
    protected PowerUp power;
    protected int gridX;
    protected int gridY;

    public PowerUpDecorator(PowerUp power) {
        this.power = power;
    }

    @Override
    public void apply() {
        this.power.apply();
    }

    public void update() {
        this.apply();
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }
    public void setGridX(int gX) {
        gridX = gX;
    }
    public void setGridY(int gY) {
        gridY = gY;
    }
}
