package org.group51.duncrawl.unittests;

import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.models.enemies.Crab;
import org.group51.duncrawl.models.enemies.Jellyfish;
import org.group51.duncrawl.models.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class EnemyDamageTests {

    Player player = Player.getPlayer();

    @Test
    public void jellyfishAttackEasy() {
        player.setHealth(100);
        player.setDamageTakenMult(1);
        Entity enemy = new Jellyfish();
        player.processEnemyCollision(enemy);
        assertEquals(player.getHealth(), 90);
    }

    @Test
    public void jellyfishAttackMedium() {
        player.setHealth(100);
        player.setDamageTakenMult(1.5);
        Entity enemy = new Jellyfish();
        player.processEnemyCollision(enemy);
        assertEquals(player.getHealth(), 85);
    }

    @Test
    public void jellyfishAttackHard() {
        player.setHealth(100);
        player.setDamageTakenMult(2);
        Entity enemy = new Jellyfish();
        player.processEnemyCollision(enemy);
        assertEquals(player.getHealth(), 80);
    }

    @Test
    public void crabAttackEasy() {
        player.setHealth(100);
        player.setDamageTakenMult(1);
        Entity enemy = new Crab();
        player.processEnemyCollision(enemy);
        assertEquals(player.getHealth(), 80);
    }

    @Test
    public void crabAttackMedium() {
        player.setHealth(100);
        player.setDamageTakenMult(1.5);
        Entity enemy = new Crab();
        player.processEnemyCollision(enemy);
        assertEquals(player.getHealth(), 70);
    }


}
