package org.group51.duncrawl.models.statuseffects;

import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.StatusEffect;

public class SlowEffect implements StatusEffect {
    private int maxDuration;
    private int maxInterval;
    private int curInterval;
    private int curDuration;
    private boolean isActive;
    private int slowdown;

    public SlowEffect() {
        this(10, 20, 10);
    }

    public SlowEffect(int gDuration, int gInterval, int gSlowdown) {
        maxDuration = gDuration;
        maxInterval = gInterval;
        slowdown = gSlowdown;
        isActive = true;
        curInterval = 0;
        curDuration = 0;
    }

    public SlowEffect(SlowEffect other) {
        maxDuration = other.maxDuration;
        maxInterval = other.maxInterval;
        slowdown = other.slowdown;
        curInterval = 0;
        curDuration = 0;
        isActive = true;
    }

    @Override
    public void apply() {
        if (curInterval++ >= maxInterval) {
            Player p = Player.getPlayer();
            p.setMaxMovementTimer(slowdown);
            curInterval = 0;
            curDuration++;
        }
        if (curDuration >= maxDuration) {
            Player p = Player.getPlayer();
            p.setMaxMovementTimer(p.getOrigMaxMovementTimer());
            isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public String toString() {
        return "Slow";
    }

    @Override
    public StatusEffect makeCopy() {
        return new SlowEffect(this);
    }

}
