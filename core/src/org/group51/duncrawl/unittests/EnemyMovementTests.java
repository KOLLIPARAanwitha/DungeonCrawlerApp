package org.group51.duncrawl.unittests;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.viewmodels.PlayerViewModel;
import org.group51.duncrawl.views.FirstLevel;
import org.junit.Test;

public class EnemyMovementTests {

    //Uses outdated frameworks, no longer valid tests
    //Test
    /*
    Player p = Player.getPlayer();
    FirstLevel lvl = new FirstLevel(new GameManager(), null);
    Sprite tempSprite = new Sprite(new Texture("sprites/characters/lime_sprite.png"));

    @Test(expected = IllegalArgumentException.class)
    public void subcriberIsAdded() {
        Enemy test = new EnemyViewModel(null, null, 0, 0);
        p.addSubscriber(test);
        assertEquals(p.getSubscriber(0), test);
    }
    public void subcriberIsObservered() {
        EnemyViewModel test = new EnemyViewModel(tempSprite, lvl, 0, 0);
        float oldPos = test.getX();
        p.addSubscriber(test);
        float newPos = test.getX();
        assertEquals(oldPos != newPos, true);
    }
    */
}
