package org.group51.duncrawl.unittests;

import static org.junit.Assert.assertEquals;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.powerupmodels.BasePowerUp;
import org.group51.duncrawl.models.powerupmodels.HealthBoost;
import org.group51.duncrawl.models.statuseffects.ConfusedEffect;
import org.group51.duncrawl.models.statuseffects.SlowEffect;
import org.junit.Test;
public class PlayerStatusEffectTest {
    @Test
    public void testTimedDeactivation() {
        SlowEffect slow = new SlowEffect(5, 0, 1);
        for (int i = 0; i < 5; i++) {
            slow.apply();
            if (i < 4) {
                assertEquals(slow.isActive(), true);
            }
        }
        assertEquals(slow.isActive(), false);
    }

    @Test
    public void testStatusApplication() {
        ConfusedEffect confused = new ConfusedEffect();
        Player.getPlayer().addStatusEffect(confused);
        assertEquals(Player.getPlayer().getStatuses().contains(confused), true);
    }

    @Test
    public void testStatusRestoreStats() {
        Player.getPlayer().resetDefaults();
        int initialMovement = Player.getPlayer().getMaxMovementTimer();
        int slowdownSpeed = 10;
        SlowEffect slow = new SlowEffect(5, 0, slowdownSpeed);
        slow.apply();
        assertEquals(Player.getPlayer().getMaxMovementTimer(), slowdownSpeed);
        for (int i = 0; i < 5; i++) {
            slow.apply();
        }
        assertEquals(Player.getPlayer().getMaxMovementTimer(), initialMovement);
    }

    @Test
    public void testPreventDuplicateStatus() {
        ConfusedEffect confused = new ConfusedEffect();
        Player.getPlayer().addStatusEffect(confused);
        int size = Player.getPlayer().getStatuses().size();
        ConfusedEffect confused2 = new ConfusedEffect();
        Player.getPlayer().addStatusEffect(confused2);
        assertEquals(size,Player.getPlayer().getStatuses().size());
    }

    @Test
    public void testDeactivatedStatusNoEffect() {
        SlowEffect slow = new SlowEffect(5, 0, 1);
        for (int i = 0; i < 5; i++) {
            slow.apply();
        }
        assertEquals(slow.isActive(), false);
        int movementSpeed = Player.getPlayer().getMaxMovementTimer();
        Player.getPlayer().processStatusEffects();
        assertEquals(Player.getPlayer().getMaxMovementTimer(), movementSpeed);
    }

    @Test
    public void testNullStatusEffect() {
        int size = Player.getPlayer().getStatuses().size();
        ConfusedEffect confused2 = null;
        Player.getPlayer().addStatusEffect(confused2);
        assertEquals(size,Player.getPlayer().getStatuses().size());

    }
}
