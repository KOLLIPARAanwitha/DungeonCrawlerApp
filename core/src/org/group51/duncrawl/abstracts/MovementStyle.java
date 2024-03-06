package org.group51.duncrawl.abstracts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public interface MovementStyle {
    public Vector2 getDirection();

    public Vector2 getDirection(Touchpad touchpad);
}
