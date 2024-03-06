package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;

import java.util.ArrayList;

public class WatergunShotViewModel extends Sprite implements Subscriber {
    private Vector2 velocity;
    private static int globalID = 0;
    private int id;
    private Room currRoom;
    private LevelBase currLevel;
    private Sprite verticalSprite;
    private Sprite horizontalSprite;

    private Rectangle shotBoundingBox;

    private int baseSpeed;

    private int lifetime;
    private boolean isActive;

    public WatergunShotViewModel() { //for testing purposes only
        super();

        verticalSprite = new Sprite(new Texture("sprites/weapons/vertical_water_shot.png"));
        this.set(verticalSprite);
        this.setX(600);
        this.setY(800);
        this.setScale(2f);
    }

    public WatergunShotViewModel(WatergunViewModel watergunView) {
        super();
        globalID++;
        id = globalID;
        velocity = new Vector2();
        verticalSprite = new Sprite(new Texture("sprites/weapons/vertical_water_shot.png"));
        horizontalSprite = new Sprite(new Texture("sprites/weapons/horizontal_water_shot.png"));
        currRoom = watergunView.getCurrRoom();
        currLevel = watergunView.getCurrLevel();
        int lastDir = watergunView.getLastDir();
        baseSpeed = Player.getPlayer().getProjectileSpeed();
        if (lastDir >= 2) {
            velocity.y = (lastDir == 2) ? baseSpeed : baseSpeed * -1;
            velocity.x = 0;
            this.set(verticalSprite);
        } else {
            velocity.x = (lastDir == 0) ? baseSpeed : baseSpeed * -1;
            velocity.y = 0;
            this.set(horizontalSprite);
        }
        this.setX(watergunView.getX());
        this.setY(watergunView.getY());
        isActive = true;
        currRoom.addProjectile(this);
        this.shotBoundingBox = this.getBoundingRectangle();
        lifetime = 0;
    }
    public void update() {
        if (isActive) {
            checkCollisions();
            this.setX(this.getX() + velocity.x);
            this.setY(this.getY() + velocity.y);
            this.shotBoundingBox = this.getBoundingRectangle();
            if (lifetime++ >= Player.getPlayer().getMaxProjectileLifetime()) {
                //currRoom.removeProjectile(this);
                isActive = false;
                return;
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    private void checkCollisions() {
        this.shotBoundingBox = this.getBoundingRectangle();
        ArrayList<EnemyViewModel> enemies = currRoom.getEnemies();
        for (EnemyViewModel enemy : enemies) {
            if (this.getBoundingRectangle().overlaps(enemy.getBoundingRectangle())) {
                if (enemy.isActive()) {
                    enemy.processAttack();
                    //currRoom.removeEnemy(enemy);
                    isActive = false;
                    currLevel.setScore(currLevel.getScore() + 100);
                    return;
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof WatergunShotViewModel) {
            WatergunShotViewModel other = (WatergunShotViewModel) obj;
            return other.id == this.id;
        }
        return false;
    }
}
