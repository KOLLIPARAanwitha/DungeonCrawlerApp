package org.group51.duncrawl.models.statuseffects;

import org.group51.duncrawl.abstracts.MovementStyle;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.PlayerConfusedMovementStyle;
import org.group51.duncrawl.models.RandomMovementStyle;
import org.group51.duncrawl.statusmodels.StatusEffect;

public class ConfusedEffect implements StatusEffect {

    private int maxDuration;
    private int maxInterval;
    private int curInterval;
    private int curDuration;
    private boolean isActive;
    private MovementStyle movementStyle;

    public ConfusedEffect() {
        this(10, 30);
    }

    public ConfusedEffect(int gDuration, int gInterval) {
        maxDuration = gDuration;
        maxInterval = gInterval;
        isActive = true;
        movementStyle = new PlayerConfusedMovementStyle();
        curInterval = 0;
        curDuration = 0;
    }

    public ConfusedEffect(ConfusedEffect other) {
        maxDuration = other.maxDuration;
        maxInterval = other.maxInterval;
        movementStyle = new PlayerConfusedMovementStyle();
        curInterval = 0;
        curDuration = 0;
        isActive = true;
    }

    @Override
    public void apply() {
        if (curInterval++ >= maxInterval) {
            Player p = Player.getPlayer();
            p.setMovementStyle(movementStyle);
            curInterval = 0;
            curDuration++;
        }
        if (curDuration >= maxDuration) {
            Player p = Player.getPlayer();
            p.resetMovement();
            isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public String toString() {
        return "Confused";
    }

    @Override
    public StatusEffect makeCopy() {
        return new ConfusedEffect(this);
    }

}
