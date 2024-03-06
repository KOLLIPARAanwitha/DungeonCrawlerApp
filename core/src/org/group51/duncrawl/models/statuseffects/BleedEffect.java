package org.group51.duncrawl.models.statuseffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;

import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.StatusEffect;

public class BleedEffect implements StatusEffect {

    private int maxDuration;
    private int maxInterval;
    private int curInterval;
    private int curDuration;
    private int damage;

    private boolean isActive;

    public BleedEffect() {
        this(10, 50, 5);
    }
    public BleedEffect(int gDuration, int gInterval, int gDamage) {
        maxDuration = gDuration;
        maxInterval = gInterval;
        damage = gDamage;
        curInterval = 0;
        curDuration = 0;
        isActive = true;
    }
    public BleedEffect(BleedEffect other) {
        maxDuration = other.maxDuration;
        maxInterval = other.maxInterval;
        damage = other.damage;
        curInterval = 0;
        curDuration = 0;
        isActive = true;
    }
    @Override
    public void apply() {
        if (curInterval++ >= maxInterval) {
            Player p = Player.getPlayer();
            p.setHealth(p.getHealth() - damage);
            curInterval = 0;
            curDuration++;
        }
        if (curDuration >= maxDuration) {
            isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void update() {
        this.apply();
    }

    public String toString() {
        return "Bleed";
    }

    @Override
    public StatusEffect makeCopy() {
        return new BleedEffect(this);
    }
}
