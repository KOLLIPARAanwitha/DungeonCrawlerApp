package org.group51.duncrawl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.models.enemies.Crab;
import org.group51.duncrawl.models.enemies.Jellyfish;
import org.group51.duncrawl.models.enemies.Seal;
import org.group51.duncrawl.models.enemies.Turtle;
import org.group51.duncrawl.models.powerupmodels.BasePowerUp;
import org.group51.duncrawl.models.powerupmodels.HealthBoost;
import org.group51.duncrawl.models.powerupmodels.ReloadBoost;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.viewmodels.PowerUpViewModel;
import org.group51.duncrawl.views.FirstLevel;

public class RoomThree extends Room{

    public static final int LAYER_INDEX = 6;
    public static final String COLLISION_LAYER = "RoomThreeCollisions";

    public RoomThree(FirstLevel fr) {
        super(fr);
        Entity tempEnemy = new Seal();
        Sprite tempSprite = new Sprite(new Texture("sprites/enemies/seal.png"));
        tempSprite.setScale(2.0f);
        EnemyViewModel enemy2 = new EnemyViewModel(tempSprite, fr, 1000, 400, tempEnemy, this);
        enemies.add(enemy2);

        tempEnemy = new Crab();
        tempSprite = new Sprite(new Texture("sprites/enemies/crab.png"));
        tempSprite.setScale(2.0f);
        enemy2 = new EnemyViewModel(tempSprite, fr, 1100, 800, tempEnemy, this);
        enemies.add(enemy2);

        tempEnemy = new Turtle();
        tempSprite = new Sprite(new Texture("sprites/enemies/turtle.png"));
        tempSprite.setScale(2.0f);
        enemy2 = new EnemyViewModel(tempSprite, fr, 1100, 600, tempEnemy, this);
        enemies.add(enemy2);

        tempEnemy = new Jellyfish();
        tempSprite = new Sprite(new Texture("sprites/enemies/jellyfish.png"));
        tempSprite.setScale(2.0f);
        enemy2 = new EnemyViewModel(tempSprite, fr, 1100, 300, tempEnemy, this);
        enemies.add(enemy2);

        PowerUp tempPower = new ReloadBoost(new HealthBoost(new BasePowerUp()));
        tempSprite = new Sprite(new Texture("sprites/Powerups/health_reload_boost.png"));
        tempSprite.setScale(2.0f);
        PowerUpViewModel power1 = new PowerUpViewModel(tempSprite, fr, 900, 200, tempPower, this);
        powers.add(power1);
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
        if (playerX <= 550 && playerY <= 100) {
            return 3; //South Exit
        }
        if (playerX >= 1000 && playerX <= 1300 && playerY >= 1400) {
            return 5; //Level Exit
        }
        return -1;
    }
}
