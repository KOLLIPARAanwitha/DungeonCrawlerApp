package org.group51.duncrawl.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import org.group51.duncrawl.abstracts.MovementStyle;

public class SimpleMovementStyle implements MovementStyle {
    private int counter;
    private int status;
    private Vector2 velocity;
    public SimpleMovementStyle() {
        counter = 0;
        velocity = new Vector2();
        velocity.x = 50;
        velocity.y = 50;
        status = 0;
    }

    public Vector2 getDirection() {
        counter++;
        if (counter >= 10) {
            if (Math.random() >= 0.5) {
                velocity.x *= -1;
            } else {
                velocity.y *= -1;
            }
            status++;
            if (status >= 1000) {
                status = 0;
            }
            counter = 0;
        }
        Vector2 temp = new Vector2();
        if (status % 2 == 0) {
            temp.x = velocity.x;
            temp.y = 0;
        } else {
            temp.x = 0;
            temp.y = velocity.y;
        }
        return temp;
    }

    @Override
    public Vector2 getDirection(Touchpad touchpad) {
        return null;
    }
}
