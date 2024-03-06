package org.group51.duncrawl.models.powerupmodels;

import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.PowerUp;

public class BasePowerUp implements PowerUp, Subscriber {

    @Override
    public void apply() {
        //Player.getPlayer().removeSubscriber(this);
    }

    public void update() {
        this.apply();
    }

    public boolean isActive() {
        return false;
    }

    public void setActive(boolean active) {
        return;
    }
}
