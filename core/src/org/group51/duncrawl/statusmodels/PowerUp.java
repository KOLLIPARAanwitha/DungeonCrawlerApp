package org.group51.duncrawl.statusmodels;

public interface PowerUp {
    public void apply();
    public void update();

    public boolean isActive();

    public void setActive(boolean active);
}
