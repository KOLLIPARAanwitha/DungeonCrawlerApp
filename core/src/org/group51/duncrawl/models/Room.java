package org.group51.duncrawl.models;

import com.badlogic.gdx.graphics.g2d.Batch;

//import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.abstracts.EnemyCreator;
import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.abstracts.Publisher;
import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.viewmodels.EnemyViewModel;
import org.group51.duncrawl.viewmodels.PowerUpViewModel;
import org.group51.duncrawl.viewmodels.WatergunShotViewModel;

import java.util.ArrayList;

public class Room implements Publisher {
    protected Room northRoom;
    protected Room southRoom;
    protected Room eastRoom;
    protected Room westRoom;

    protected LevelBase levelBase;

    protected EnemyCreator enemyCreator;

    ArrayList<EnemyViewModel> enemies = new ArrayList<EnemyViewModel>();

    ArrayList<PowerUpViewModel> powers = new ArrayList<PowerUpViewModel>();
    ArrayList<WatergunShotViewModel> projectiles = new ArrayList<WatergunShotViewModel>();

    ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();


    public Room(LevelBase lb) {
        this.levelBase = lb;
    }


    public Room getNorthRoom() {
        return northRoom;
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public Room getWestRoom() {
        return westRoom;
    }

    public EnemyCreator getEnemyCreator() {
        return enemyCreator;
    }

    public void setNorthRoom(Room nRoom) {
        northRoom = nRoom;
    }

    public void setSouthRoom(Room sRoom) {
        southRoom = sRoom;
    }

    public void setEastRoom(Room eRoom) {
        eastRoom = eRoom;
    }

    public void setWestRoom(Room wRoom) {
        westRoom = wRoom;
    }

    public void setEnemyCreator(EnemyCreator enCreator) {
        enemyCreator = enCreator;
    }

    public String getCollisionLayerName() {
        return "";
    }

    public int getLayerIndex() {
        return -1;
    }

    public int processExits(float playerX, float playerY) {
        return -1;
        //1 means north, 2 means west, 3 means south, 4 means east, 5 means level exit
    }


    public void renderEnemies(Batch b) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isActive()) {
                enemies.get(i).draw(b);
            }
        }
    }

    public void renderPowerups(Batch b) {
        for (int i = 0; i < powers.size(); i++) {
            if(powers.get(i).isActive()) {
                powers.get(i).draw(b);
            }

        }
    }

    public void renderProjectiles(Batch b) {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isActive()) {
                projectiles.get(i).draw(b);
            }
        }
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
        for (WatergunShotViewModel proj : projectiles) {
            proj.update();
        }
        for (PowerUpViewModel power : powers) {
            power.update();
        }
    }

    public void notifyPowers() {
        for (PowerUp power: powers) {
            power.update();
        }
    }

    @Override
    public void addSubscriber(Subscriber enemy) {
        subscribers.add(enemy);
    }

    @Override
    public Subscriber removeSubscriber(Subscriber enemy) {
        subscribers.remove(enemy);
        return enemy; //this is such bad practice but idc I need to get this done
    }

    public ArrayList<EnemyViewModel> getEnemies() {
        return enemies;
    }

    public void addPower(PowerUpViewModel power) {
        powers.add(power);
        //subscribers.add(power);
    }
    public void removePower(PowerUpViewModel power) {
            powers.remove(power);
            //subscribers.remove(power);
    }
    public void removeEnemy(EnemyViewModel enemy) {
        enemies.remove(enemy);
        subscribers.remove(enemy);
    }

    public void addProjectile(WatergunShotViewModel projectile) {
        if (projectile == null) {
            return;
        }
        projectiles.add(projectile);
        //subscribers.add(projectile);
    }
    public void removeProjectile(WatergunShotViewModel projectile) {
        projectiles.remove(projectile);
        //subscribers.remove(projectile);
    }
}
