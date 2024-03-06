package org.group51.duncrawl.unittests;

import static org.junit.Assert.assertEquals;

import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.powerupmodels.BasePowerUp;
import org.group51.duncrawl.models.powerupmodels.HealthBoost;
import org.group51.duncrawl.models.powerupmodels.ReloadBoost;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.junit.Test;

public class PlayerPowerUpTest {



    @Test
    public void testHealthApplication() {
        int initHealth = Player.getPlayer().getHealth();
        HealthBoost healthBoost = new HealthBoost(new BasePowerUp());
        healthBoost.apply();
        assertEquals(Player.getPlayer().getHealth(), initHealth + 100);
    }

    @Test
    public void testPowerUpInstantDeactivation() {
        HealthBoost boost = new HealthBoost(new BasePowerUp());
        boost.apply();
        assertEquals(boost.isActive(), false);
    }

    @Test
    public void testPowerUpWrapping() {
        int initHealth = Player.getPlayer().getHealth();
        int initReload = Player.getPlayer().getMaxReloadTime();
        PowerUp healthReload = new HealthBoost(new ReloadBoost(new BasePowerUp()));
        healthReload.apply();
        assertEquals(Player.getPlayer().getHealth(), initHealth + 100);
        assertEquals(Player.getPlayer().getMaxReloadTime(), initReload - 8);
    }

}
