package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;

public class WatergunViewModel extends Sprite {
    private int lastDir;
    private Player player;
    private LevelBase currLevel;
    Sprite upSprite;
    Sprite downSprite;
    Sprite leftSprite;
    Sprite rightSprite;

    private int cooldown;

    Room currRoom;

    public WatergunViewModel(LevelBase gLevel) {
        super();
        currLevel = gLevel;
        upSprite = new Sprite(new Texture("sprites/weapons/watergun_up.png"));
        downSprite = new Sprite(new Texture("sprites/weapons/watergun_down.png"));
        leftSprite = new Sprite(new Texture("sprites/weapons/watergun_left.png"));
        rightSprite = new Sprite(new Texture("sprites/weapons/watergun_right.png"));
        this.set(rightSprite);
        player = Player.getPlayer();
        cooldown = 0;
    }

    public int getLastDir() {
        return lastDir;
    }

    public Room getCurrRoom() {
        return currRoom;
    }

    public LevelBase getCurrLevel() {
        return currLevel;
    }

    public void attemptShoot() {
        if (cooldown >= player.getMaxReloadTime()) {
            PlayerViewModel playerView = player.getPlayerViewModel();
            currRoom = playerView.getRoom();
            lastDir = playerView.getLastDir();
            WatergunShotViewModel projectile = new WatergunShotViewModel(this);
            cooldown = 0;
        }
    }

    public void update() {
        PlayerViewModel playerView = player.getPlayerViewModel();
        currRoom = playerView.getRoom();
        lastDir = playerView.getLastDir();
        cooldown++;
        System.out.println("Cooldown: " + cooldown);
        if (lastDir == 1) {
            this.set(leftSprite);
            this.setX(playerView.getX());
            this.setY(playerView.getY() + 15);
        } else if (lastDir == 0) {
            //facing the right direction
            this.set(rightSprite);
            this.setX(playerView.getX() + 50);
            this.setY(playerView.getY() + 15);
        } else if (lastDir == 2) {
            this.set(upSprite);
            this.setX(playerView.getX());
            this.setY(playerView.getY() + 15);
        } else if (lastDir == 3) {
            this.set(downSprite);
            this.setX(playerView.getX());
            this.setY(playerView.getY() + 5);
        }
    }
}
