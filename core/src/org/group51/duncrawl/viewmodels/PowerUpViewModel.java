package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.decorators.PowerUpDecorator;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;
import org.group51.duncrawl.statusmodels.PowerUp;

public class PowerUpViewModel extends Sprite implements PowerUp, Subscriber {
    private PowerUp self;
    private LevelBase currLevel;
    private Room room;
    private Rectangle powerupBoundingBox;
    //public ShapeRenderer sr;
    private final int TILESIZE = 75;

    public PowerUpViewModel(Sprite sprite, LevelBase currLevel, int x, int y, PowerUp gpowerup, Room gRoom) {
        super(sprite);
        sprite.setPosition(x, y);
        setX(x);
        setY(y);
        self = gpowerup;
        this.currLevel = currLevel;
        this.room = gRoom;
        //room.addSubscriber(this);
        this.powerupBoundingBox = this.getBoundingRectangle();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        //sr.begin();
    }

    public void update() {
        if (self.isActive()) {
            checkPlayerCollision();
        }

    }

    public void checkPlayerCollision() {
        Player player = Player.getPlayer();
        //System.out.println("Powerup checked!");
        if (this.getBoundingRectangle().overlaps(player.getPlayerViewModel().getBoundingRectangle())) {
            player.processPowerCollision(self);
            //room.removePower(this);
        }
    }

    public void apply() {}

    public boolean isActive() {
        return self.isActive();
    }

    public void setActive(boolean active) {
        self.setActive(active);
    }
}