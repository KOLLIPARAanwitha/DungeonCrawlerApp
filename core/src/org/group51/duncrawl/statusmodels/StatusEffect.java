package org.group51.duncrawl.statusmodels;

public interface StatusEffect {
    public void apply();

    public boolean isActive();
    public StatusEffect makeCopy();
}
