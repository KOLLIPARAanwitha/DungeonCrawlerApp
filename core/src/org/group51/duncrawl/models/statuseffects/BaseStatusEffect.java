package org.group51.duncrawl.models.statuseffects;

import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.statusmodels.StatusEffect;

public class BaseStatusEffect implements StatusEffect, Subscriber {
    @Override
    public void apply() {
        return;
    }

    @Override
    public void update() {
        this.apply();
    }

    public boolean isActive() {
        return false;
    }

    @Override
    public StatusEffect makeCopy() {
        return null;
    }
}
