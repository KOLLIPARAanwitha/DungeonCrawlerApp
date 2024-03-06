package org.group51.duncrawl.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import org.group51.duncrawl.abstracts.MovementStyle;

import java.util.Random;

public class RandomMovementStyle implements MovementStyle {
    @Override
    public Vector2 getDirection() {
        Vector2 velocity = new Vector2();
        velocity.x = 100;
        velocity.y = 100;
        Random random = new Random();
        float directionX = -1 + (2 * random.nextFloat());
        float directionY = -1 + (2 * random.nextFloat());
        if (Math.abs(directionX) >= Math.abs(directionY)) {
            directionY = 0; //Temporarily disabling diagonal movement
        } else {
            directionX = 0;
        }
        velocity.x = velocity.x * directionX;
        velocity.y = velocity.y * directionY;
        return velocity;
    }

    @Override
    public Vector2 getDirection(Touchpad touchpad) {
        return null;
    }
}
