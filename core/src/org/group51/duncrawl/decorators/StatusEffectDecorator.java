package org.group51.duncrawl.decorators;

import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.statusmodels.StatusEffect;

public class StatusEffectDecorator implements StatusEffect, Subscriber {
    protected StatusEffect statusEffect;

    public StatusEffectDecorator(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    @Override
    public void apply() {
        //Player p = Player.getPlayer();
        //p.addStatusEffect(statusEffect);
        this.statusEffect.apply();
    }

    public boolean isActive() {
        return false;
    }

    @Override
    public void update() {
        this.apply();
    }

    @Override
    public StatusEffect makeCopy() {
        return null;
    }
}