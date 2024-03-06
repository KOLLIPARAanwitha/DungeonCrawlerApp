package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.Publisher;
import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;

import java.awt.Shape;
import java.util.ArrayList;

public class PlayerViewModel extends Sprite {
    Vector2 velocity = new Vector2();
    private float speed = 60*2;

    private final static float INTERVAL_SCALING_FACTOR = 0.3F;
    //makes movement more manageable for low intervals

    private Player self = Player.getPlayer();
    private float gravity = 50 * 1.8f;

    private LevelBase currLevel;

    private Touchpad touchpad;
    public Rectangle playerBoundingBox;

    public ShapeRenderer sr;

    private final int TILESIZE = 75;
    private int movementTimer = 0; //used to control movement
    private int roomTimer;

    private int exitStatus;
    private int lastDir;

    private Room currRoom;
    //private WatergunViewModel watergun;


    public PlayerViewModel(Sprite sprite, LevelBase currLevel, Touchpad touchpad) {
        super(sprite);
        sprite.setPosition(currLevel.camera.position.x, currLevel.camera.position.y);
        this.currLevel = currLevel;
        this.touchpad = touchpad;
        //sprite.setScale(0.3f);

        this.playerBoundingBox = this.getBoundingRectangle();
        self.setPlayerViewModel(this);
        this.setX(1200);
        this.setY(800);
        exitStatus = -1;
        roomTimer = 0;
        movementTimer = 0;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        sr.begin();
    }

    public void setRoom(Room gRoom) {
        currRoom = gRoom;
    }

    public Room getRoom(){
        return currRoom;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public int getLastDir() {
        return lastDir;
    }
    public void refreshBounds() {
        this.playerBoundingBox = this.getBoundingRectangle();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public LevelBase getCurrLevel() {
        return currLevel;
    }

    public void update(float interval) {
        exitStatus = checkExits();
        checkMovement(interval);
        if (self.getInvicibilityTimer() > 0) {
            self.setInvicibilityTimer(self.getInvicibilityTimer() - 1);
        }
        self.processStatusEffects();

    }

    public int checkExits() {
        this.playerBoundingBox = this.getBoundingRectangle();
        roomTimer++;
        if (roomTimer >= 50) {
            roomTimer = 0;
            return currRoom.processExits(this.getX(), this.getY());
            /*
            System.out.println("x:" + this.getX());
            for (MapObject object : currLevel.getCollisionLayer()) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    if (playerBoundingBox.overlaps(rect)) {
                        System.out.println("af");
                        if (this.getX() <= 200) {
                            System.out.println("West Exit");
                            return 2;
                        }

                    }
                }

            }
             */

        }
        return -1; //No exit or changed rooms too recently
    }

    public void checkMovement(float interval) {
        movementTimer++; //MovementTimer stores the number of frames left
        if (movementTimer >= self.getMaxMovementTimer()) {
            /*
            velocity.x = touchpad.getKnobPercentX() * 4;
            velocity.y = touchpad.getKnobPercentY() * 4;

            if (Math.abs(velocity.x) >= Math.abs(velocity.y)) {
                velocity.y = 0; //Temporarily disabling diagonal movement
            } else {
                velocity.x = 0;
            }
            */
            velocity = self.getMovementStyle().getDirection(touchpad);
            if (Math.abs(velocity.y) > 0) {
                lastDir = (velocity.y > 0) ? 2 : 3;
            } else if (Math.abs(velocity.x) > 0){
                lastDir = (velocity.x > 0) ? 0 : 1;
            }

            float preSnapX = getX() + velocity.x * (interval * INTERVAL_SCALING_FACTOR);
            float preSnapY = getY() + velocity.y * (interval * INTERVAL_SCALING_FACTOR);
            float playerX = (float) Math.floor((preSnapX + TILESIZE / 2) / TILESIZE) * TILESIZE;
            float playerY = (float) Math.floor((preSnapY + TILESIZE / 2) / TILESIZE) * TILESIZE;
            float oldX = getX();
            float oldY = getY();
            this.setX(playerX);
            this.setY(playerY);
            //if (velocity.x != 0 || velocity.y != 0) {
            //self.notifySubscribers();
            //}
            detectCollision(oldX, oldY);
            //it may be better to look at horizontal and vertical movement seperately.
            movementTimer = 0;
            //self.setHealth(self.getHealth() - 1);
        }

    }

    public void detectCollision(float oldX, float oldY) {
        // Perform collision detection
        this.playerBoundingBox = this.getBoundingRectangle(); //reupdate hitbox
        //System.out.println("Current layer: " + currLevel.getCollisionLayerName());
        for (MapObject object : currLevel.getCollisionLayer()) {

            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                //System.out.println("Height" + rect.getHeight() +  " Width " + rect.getWidth());
                if (playerBoundingBox.overlaps(rect)) {
                    //System.out.println("Collision!");
                    //setX(oldX - (oldX - getX()) * -1.1f);
                    //setY(oldY - (oldY - getY()) * -1.1f);

                    setX(oldX);
                    setY(oldY);
                    this.playerBoundingBox = this.getBoundingRectangle();
                }
            }
        }
    }
}
