package org.group51.duncrawl.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import org.group51.duncrawl.abstracts.MovementStyle;

public class PlayerDefaultMovementStyle implements MovementStyle {
    @Override
    public Vector2 getDirection() {
        return null;
    }

    @Override
    public Vector2 getDirection(Touchpad touchpad) {
        Vector2 velocity = new Vector2();
        velocity.x = touchpad.getKnobPercentX() * 4;
        velocity.y = touchpad.getKnobPercentY() * 4;

        if (Math.abs(velocity.x) >= Math.abs(velocity.y)) {
            velocity.y = 0; //Temporarily disabling diagonal movement
        } else {
            velocity.x = 0;
        }
        return velocity;
    }
}
