package org.group51.duncrawl.models.powerupmodels;

import org.group51.duncrawl.decorators.PowerUpDecorator;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.PowerUp;

public class ReloadBoost extends PowerUpDecorator {
    private boolean isActive;
    public ReloadBoost(PowerUp powerup) {
        super(powerup);
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void apply() {
        super.apply();
        Player p = Player.getPlayer();
        p.setMaxReloadTime(p.getMaxReloadTime() - 8);
        isActive = false;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
