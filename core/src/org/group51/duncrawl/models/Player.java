package org.group51.duncrawl.models;

import org.group51.duncrawl.abstracts.Enemy;
import org.group51.duncrawl.abstracts.EnemyPublisher;
import org.group51.duncrawl.abstracts.Entity;
import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.MovementStyle;
import org.group51.duncrawl.abstracts.Publisher;
import org.group51.duncrawl.abstracts.Subscriber;
import org.group51.duncrawl.decorators.PowerUpDecorator;
import org.group51.duncrawl.models.statuseffects.BleedEffect;
import org.group51.duncrawl.models.statuseffects.ConfusedEffect;
import org.group51.duncrawl.models.statuseffects.SlowEffect;
import org.group51.duncrawl.statusmodels.PowerUp;
import org.group51.duncrawl.statusmodels.StatusEffect;
import org.group51.duncrawl.viewmodels.PlayerViewModel;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Player extends Entity implements Publisher{
    private static volatile Player player;
    private String name;
    private int score;
    private ArrayList<Subscriber> subscribers; //used to keep track of powerups and statuses
    private ArrayList<PowerUp> powerUps;
    private ArrayList<StatusEffect> statuses;
    private int invicibilityTimer = 0;
    private double damageTakenMult;
    private MovementStyle movementStyle;
    private int maxMovementTimer;

    private int origMaxMovementTimer;
    private int maxReloadTime;
    private int projectileSpeed;

    private int maxProjectileLifetime;
    private PlayerViewModel playerViewModel; //bad practice, but whatever



    private Player(String gName, int gHealth) {
        super(gHealth, 10, 10, 5);
        invicibilityTimer = 0;
        name = gName;
        this.subscribers = new ArrayList<Subscriber>();
        //this.powerUps = new ArrayList<PowerUp>();
        this.statuses = new ArrayList<StatusEffect>();
        this.movementStyle = new PlayerDefaultMovementStyle();
        maxMovementTimer = 5;
        origMaxMovementTimer = 5;
        maxReloadTime = 30;
        projectileSpeed = 15;
        maxProjectileLifetime = 30;
    }

    public void resetDefaults() {
        invicibilityTimer = 0;
        movementTimer = 5;
        this.subscribers = new ArrayList<Subscriber>();
        //this.powerUps = new ArrayList<PowerUp>();
        this.statuses = new ArrayList<StatusEffect>();
        this.movementStyle = new PlayerDefaultMovementStyle();
        //this.movementStyle = new PlayerConfusedMovementStyle();
        maxMovementTimer = 5;
        origMaxMovementTimer = 5;
        maxReloadTime = 30;
        projectileSpeed = 15;
        maxProjectileLifetime = 30;
    }

    private Player() {
        this("Asian", 100);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getMaxMovementTimer() {
        return maxMovementTimer;
    }

    public void setMaxReloadTime(int newMax) {
        maxReloadTime = newMax;
    }


    public int getOrigMaxMovementTimer() {
        return origMaxMovementTimer;
    }

    public void resetMovement() {
        movementStyle = new PlayerDefaultMovementStyle();
    }

    public void  setOrigMaxMovementTimer(int origTime) {
        origMaxMovementTimer =  origTime;
    }

    public void setProjectileSpeed(int newSpeed) {
        projectileSpeed = newSpeed;
    }

    public int getMaxProjectileLifetime() {
        return maxProjectileLifetime;
    }

    public void setMaxProjectileLifetime(int newMax) {
        maxProjectileLifetime = newMax;
    }

    public int getMaxReloadTime() {
        return maxReloadTime;
    }

    public int getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setMaxMovementTimer(int maxTime) {
        maxMovementTimer =  maxTime;
    }

    public int getHealth() {
        return health;
    }

    public int getInvicibilityTimer() {
        return invicibilityTimer;
    }

    public void setInvicibilityTimer(int inTimer) {
        invicibilityTimer = inTimer;
    }

    public void setDamageTakenMult(double mult) {
        damageTakenMult = mult;
    }

    public void setMovementStyle(MovementStyle gMoveStyle) {
        movementStyle = gMoveStyle;
    }

    public MovementStyle getMovementStyle() {
        return movementStyle;
    }

    public PlayerViewModel getPlayerViewModel() {
        return playerViewModel;
    }

    public void setName(String gName) {
        if (gName == null) {
            throw new IllegalArgumentException("Player Name Can't Be Null!");
        }
        name = gName;
    }

    public void setPlayerViewModel(PlayerViewModel playerView) {
        playerViewModel = playerView;
    }

    public void processEnemyCollision(Entity enemy) {
        if (invicibilityTimer <= 0) {
            invicibilityTimer = 50;
            if (enemy.getStatus() != null) {
                if (!checkDuplicates(enemy.getStatus())) {
                    statuses.add(enemy.getStatus().makeCopy());
                }

            }
            health -= (int) (enemy.getAttack() * damageTakenMult);
        }
    }

    public boolean checkDuplicates(StatusEffect status) {
        if (status instanceof BleedEffect) {
            for (StatusEffect statusEffect : statuses) {
                if (statusEffect instanceof BleedEffect && statusEffect.isActive()) {
                    return true;
                }
            }
        } else if (status instanceof SlowEffect) {
            for (StatusEffect statusEffect : statuses) {
                if (statusEffect instanceof SlowEffect && statusEffect.isActive()) {
                    return true;
                }
            }
        } else if (status instanceof ConfusedEffect) {
            for (StatusEffect statusEffect : statuses) {
                if (statusEffect instanceof ConfusedEffect && statusEffect.isActive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void processPowerCollision(PowerUp power) {
        power.apply();
        LevelBase curLevel = playerViewModel.getCurrLevel();
        curLevel.setScore(curLevel.getScore() + 50);

    }

    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public void addSubscriber(Subscriber gSubscriber) {
        subscribers.add(gSubscriber);
    }

    public Subscriber removeSubscriber(Subscriber gSubscriber) {
        subscribers.remove(gSubscriber);
        return gSubscriber;
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void processStatusEffects() {
        for (StatusEffect status : statuses) {
            if (status.isActive()) {
                status.apply();
            }
        }
    }

    public void addPowerUp(PowerUp power) {

    }
    public void addStatusEffect(StatusEffect stat) {
        if (stat == null) {
            return;
        }
        if (checkDuplicates(stat)) {
            this.statuses.add(stat);
        }

    }

    public ArrayList<StatusEffect> getStatuses() {
        return statuses;
    }


}
