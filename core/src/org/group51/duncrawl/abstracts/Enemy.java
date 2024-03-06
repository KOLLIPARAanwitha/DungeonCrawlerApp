package org.group51.duncrawl.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Enemy extends Subscriber{

    /**
     * Updates when observer notifies enemies
     */
    public void update();

    public MovementStyle getMovementStyle();


}
