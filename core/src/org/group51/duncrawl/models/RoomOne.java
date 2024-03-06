package org.group51.duncrawl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.models.creators.CrabCreator;
import org.group51.duncrawl.models.enemies.Jellyfish;
import org.group51.duncrawl.models.powerupmodels.BasePowerUp;
import org.group51.duncrawl.models.powerupmodels.HealthBoost;
import org.group51.duncrawl.models.powerupmodels.RangeBoost;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.viewmodels.PowerUpViewModel;
import org.group51.duncrawl.viewmodels.WatergunShotViewModel;
import org.group51.duncrawl.views.FirstLevel;

public class RoomOne extends Room {

    public static final int LAYER_INDEX = 0;
    public static final String COLLISION_LAYER = "RoomOneCollisions";

    public RoomOne(FirstLevel fr) {
        super(fr);
        this.enemyCreator = new CrabCreator();
        //Sprite tempSprite = new Sprite(new Texture("sprites/characters/red_sprite.png"));
        Sprite tempSprite = new Sprite(new Texture("sprites/enemies/crab.png"));
        //tempSprite.setScale(0.2f);
        tempSprite.setScale(2.0f);
        Enemy en1 = enemyCreator.createEnemy();

        Entity tempEnemy = (Entity) en1;
        EnemyViewModel enemy1 = new EnemyViewModel(tempSprite, fr, 200, 200, tempEnemy, this);
        enemies.add(enemy1);

        tempEnemy = new Jellyfish();
        tempSprite = new Sprite(new Texture("sprites/enemies/jellyfish.png"));
        tempSprite.setScale(2.0f);
        EnemyViewModel enemy2 = new EnemyViewModel(tempSprite, fr, 600, 1200, tempEnemy, this);
        enemies.add(enemy2);

        PowerUp tempPower = new HealthBoost(new BasePowerUp());
        tempSprite = new Sprite(new Texture("sprites/Powerups/health_powerup.png"));
        tempSprite.setScale(2.0f);
        PowerUpViewModel power1 = new PowerUpViewModel(tempSprite, fr, 500, 900, tempPower, this);
        powers.add(power1);

        tempPower = new RangeBoost(new BasePowerUp());
        tempSprite = new Sprite(new Texture("sprites/Powerups/range_powerup.png"));
        tempSprite.setScale(2.0f);
        PowerUpViewModel power2 = new PowerUpViewModel(tempSprite, fr, 900, 400, tempPower, this);
        powers.add(power2);


        //WatergunShotViewModel tempWater = new WatergunShotViewModel();
        //addProjectile(tempWater);


    }

    @Override
    public int processExits(float playerX, float playerY) {
        //System.out.println("x: " + playerX + "  y:" + playerY);
        if (playerX <= 100 && playerY >= 750 && playerY <= 900){
            //System.out.println("Wahoo");
            return 2; //West Exit
        }
        return -1;
    }

    @Override
    public int getLayerIndex() {
        return LAYER_INDEX;
    }

    @Override
    public String getCollisionLayerName() {
        return COLLISION_LAYER;
    }
}
