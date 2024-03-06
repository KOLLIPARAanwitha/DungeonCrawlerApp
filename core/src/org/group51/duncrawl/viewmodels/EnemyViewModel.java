package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.MovementStyle;
import org.group51.duncrawl.models.Monster;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;
import org.group51.duncrawl.models.enemies.Jellyfish;

import java.util.Random;

public class EnemyViewModel extends Sprite implements Enemy {

    private Entity self;
    //private Vector2 velocity = new Vector2();
    //private float speed = 60*2;
    //private float gravity = 50 * 1.8f;

    private int movementTimer;
    //private boolean isActive;

    private LevelBase currLevel;
    private Room room;

    private boolean isCollidingX;
    private boolean isCollidingY;
    //private Touchpad touchpad;
    public Rectangle enemyBoundingBox;

    public ShapeRenderer sr;
    private final int TILESIZE = 75;

    public EnemyViewModel() {
        //Placeholder code for testing purposes.
        self = new Jellyfish();
        return;
    }

    public EnemyViewModel(Sprite sprite, LevelBase currLevel, int x, int y, Entity gEnemy, Room gRoom) {
        super(sprite);
        sprite.setPosition(x, y);
        setX(x);
        setY(y);
        self = gEnemy;
        this.currLevel = currLevel;
        this.enemyBoundingBox = this.getBoundingRectangle();
        gRoom.addSubscriber(this);
        room = gRoom;
        //isActive = true;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        sr.begin();
    }

    public MovementStyle getMovementStyle() {
        return self.getMovementStyle();
    }

    public boolean isActive() {
        return self.isActive();
    }

    public void setIsActive(boolean active) {
        self.setActive(active);
    }

    @Override
    public void update() {
        if (self.isActive()) {
            checkMovement();
            checkPlayerCollision();
        }

    }

    public void refreshBounds() {
        this.enemyBoundingBox = this.getBoundingRectangle();
    }
    public void checkPlayerCollision() {
        Player player = Player.getPlayer();
        //temporary approach, boundingRectangle is too large for accurate collisions
        //More precise approach is to directly use enemy x and y
        if (this.getBoundingRectangle().overlaps(player.getPlayerViewModel().getBoundingRectangle())) {
            player.processEnemyCollision(self);
        }

    }
    public void checkMovement() {
        this.movementTimer++;
        if(this.movementTimer >= self.getMovementTimer()) {
            Vector2 velocity = self.getMovementStyle().getDirection();
            /*
            velocity.x = 100;
            velocity.y = 100;
            Random random = new Random();
            float directionX = -1 + (2 * random.nextFloat());
            float directionY = -1 + (2 * random.nextFloat());
            if (Math.abs(directionX) >= Math.abs(directionY)) {
                directionY = 0; //Temporarily disabling diagonal movement
            } else {
                directionX = 0;
            }
            */

            float preSnapX = getX() + (velocity.x);
            float preSnapY = getY() + (velocity.y);

            float enemyX = (float) Math.floor((preSnapX + TILESIZE / 2) / TILESIZE) * TILESIZE;
            float enemyY = (float) Math.floor((preSnapY + TILESIZE / 2) / TILESIZE) * TILESIZE;
            float oldX = getX();
            float oldY = getY();
            this.setX(enemyX);
            this.setY(enemyY);
            detectCollision(oldX, oldY);
            this.movementTimer = 0;
        }
    }

    public void detectCollision(float oldX, float oldY) {
        // Perform collision detection
        this.enemyBoundingBox = this.getBoundingRectangle(); //reupdate hitbox
        for (int i = 0 ; i < currLevel.getCollisionLayer().getCount(); i++) {
            MapObject object = currLevel.getCollisionLayer().get(i);
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                if (enemyBoundingBox.overlaps(rect)) {
                    //System.out.println("Collision!");
                    //setX(oldX - (oldX - getX()) * -1.1f);
                    //setY(oldY - (oldY - getY()) * -1.1f);
                    setX(oldX);
                    setY(oldY);
                    this.enemyBoundingBox = this.getBoundingRectangle();
                }
            }
        }
    }

    public void processAttack() {
        self.setActive(false);
    }

}
