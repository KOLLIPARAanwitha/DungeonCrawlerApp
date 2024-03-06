package org.group51.duncrawl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.models.enemies.Seal;
import org.group51.duncrawl.models.enemies.Turtle;
import org.group51.duncrawl.models.powerupmodels.BasePowerUp;
import org.group51.duncrawl.models.powerupmodels.HealthBoost;
import org.group51.duncrawl.models.powerupmodels.RangeBoost;
import org.group51.duncrawl.models.powerupmodels.ReloadBoost;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.viewmodels.PowerUpViewModel;
import org.group51.duncrawl.views.FirstLevel;

public class RoomTwo extends Room{

    public static final int LAYER_INDEX = 3;
    public static final String COLLISION_LAYER = "RoomTwoCollisions";
    public RoomTwo(FirstLevel fr) {
        super(fr);
        Entity tempEnemy = new Seal();
        Sprite tempSprite = new Sprite(new Texture("sprites/enemies/seal.png"));
        tempSprite.setScale(2.0f);
        EnemyViewModel enemy2 = new EnemyViewModel(tempSprite, fr, 1000, 400, tempEnemy, this);
        enemies.add(enemy2);

        tempEnemy = new Turtle();
        tempSprite = new Sprite(new Texture("sprites/enemies/turtle.png"));
        tempSprite.setScale(2.0f);
        enemy2 = new EnemyViewModel(tempSprite, fr, 400, 1000, tempEnemy, this);
        enemies.add(enemy2);

        PowerUp tempPower = new ReloadBoost(new BasePowerUp());
        tempSprite = new Sprite(new Texture("sprites/Powerups/reload_boost_powerup.png"));
        tempSprite.setScale(2.0f);
        PowerUpViewModel power1 = new PowerUpViewModel(tempSprite, fr, 900, 900, tempPower, this);
        powers.add(power1);

        tempPower = new RangeBoost(new HealthBoost(new BasePowerUp()));
        tempSprite = new Sprite(new Texture("sprites/Powerups/range_health_powerup.png"));
        tempSprite.setScale(2.0f);
        PowerUpViewModel power2 = new PowerUpViewModel(tempSprite, fr, 1300, 300, tempPower, this);
        powers.add(power2);
    }

    @Override
    public int getLayerIndex() {
        return LAYER_INDEX;
    }

    @Override
    public String getCollisionLayerName() {
        return COLLISION_LAYER;
    }

    @Override
    public int processExits(float playerX, float playerY) {
        //System.out.println("x: " + playerX + "  y:" + playerY);
        if (playerX <= 550 && playerY >= 1400) {
            return 1; //North Exit
        }
        if (playerX >= 1400 && playerY >= 750 && playerY <= 900){
            return 4; //East Exit
        }
        return -1;
    }
}
