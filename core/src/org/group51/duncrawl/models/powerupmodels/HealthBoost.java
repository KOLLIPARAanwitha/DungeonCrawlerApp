package org.group51.duncrawl.models.powerupmodels;

import org.group51.duncrawl.decorators.PowerUpDecorator;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.PowerUp;

public class HealthBoost extends PowerUpDecorator {
    private boolean isActive;
    public HealthBoost(PowerUp power) {
        super(power);
        isActive = true;
    }

    public void apply() {
        super.apply();
        Player p = Player.getPlayer();
        p.setHealth(p.getHealth() + 100);
        isActive = false;
    }

    public void update() {
        this.apply();
    }

    public String toString() {
        return "Health Boost";
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
