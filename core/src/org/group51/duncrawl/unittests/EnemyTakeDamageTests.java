package org.group51.duncrawl.unittests;

import org.group51.duncrawl.models.enemies.Jellyfish;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.views.FirstLevel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyTakeDamageTests {

    @Test
    public void checkEnemyProcessAttack() {
        EnemyViewModel enemy2 = new EnemyViewModel();
        enemy2.processAttack();
        assertEquals(enemy2.isActive(), false);
    }
}
