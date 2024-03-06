package org.group51.duncrawl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.group51.duncrawl.abstracts.LevelBase;
import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Leaderboard;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.models.Room;
import org.group51.duncrawl.models.RoomOne;
import org.group51.duncrawl.models.RoomThree;
import org.group51.duncrawl.models.RoomTwo;
import org.group51.duncrawl.statusmodels.StatusEffect;
import org.group51.duncrawl.viewmodels.PlayerViewModel;
import org.group51.duncrawl.viewmodels.WatergunViewModel;

import java.awt.Shape;
import java.util.ArrayList;

public class FirstLevel extends LevelBase {
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private PlayerViewModel playerView;
    private WatergunViewModel watergun;
    private Skin theme = new Skin(Gdx.files.internal("skins/pixthulhu-ui.json"));
    private Touchpad touchpad;
    private Room model;

    private int scoreCounter; //temporary
    private String difficultyText;
    private int difficulty;
    private int scoreMult;

    private Label healthText;
    private TextButton shootButton;
    private int[] renderedIndexes;

    private RoomOne roomOne;
    private RoomTwo roomTwo;

    private RoomThree roomThree;

    //private Room currRoom;

    private ShapeRenderer sr;


    public FirstLevel(GameManager game, AssetManager manager, int difficulty, String spritePathName) {
        super(game, manager, new TmxMapLoader().load("maps/SandyRoomsTemp.tmx"));
        sr = new ShapeRenderer();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Player player = Player.getPlayer();
        player.resetDefaults();
        this.difficulty = difficulty;
        if (difficulty == 0) {
            difficultyText = "Easy";
            player.setHealth(200);
            scoreMult = 1;
            player.setDamageTakenMult(1);
        } else if (difficulty == 1) {
            difficultyText = "Medium";
            player.setHealth(150);
            scoreMult = 2;
            player.setDamageTakenMult(1.5);
        } else {
            difficultyText = "Hard";
            scoreMult = 3;
            player.setHealth(100);
            player.setDamageTakenMult(2);
        }

        touchpad = new Touchpad(1, theme);
        healthText = new Label("Health: ", theme);
        healthText.setX(Gdx.graphics.getWidth() / 20.0f);
        healthText.setY(Gdx.graphics.getHeight() * 0.7f);
        healthText.setFontScale(2);

        shootButton = new TextButton("Pew!", theme);
        shootButton.setX(Gdx.graphics.getWidth() / 20.0f);
        shootButton.setY(Gdx.graphics.getHeight() * 0.1f);
        shootButton.getLabel().setFontScale(2);
        // Create the camera and set its position to center the tilemap on the screen
        camera.setToOrtho(false, w, h);
        camera.update();
        // Create a renderer for the TiledMap
        tiledMapRenderer = new OrthogonalTiledMapRenderer(super.tiledMap, 10f);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.getBatch().setProjectionMatrix(camera.combined);
        score = 1000 * scoreMult;
        scoreCounter = 0; //temporary
        MapObjects[] collisionLayers = new MapObjects[3];
        collisionLayers[0] = getCollisionLayer("RoomOneCollisions");
        collisionLayers[1] = getCollisionLayer("RoomTwoCollisions");
        collisionLayers[2] = getCollisionLayer("RoomThreeCollisions");

        //scaling the collision layers
        //the visual layers already get automatically scaled, so no need to scale those
        for (MapObjects mos  : collisionLayers) {
            for (MapObject object : mos) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    float scale = 10f;
                    rect.x *= scale;
                    rect.y *= scale;
                    rect.width *= scale;
                    rect.height *= scale;
                }
            }
        }
        renderedIndexes = new int[1];
        renderedIndexes[0] = 0;
        //renderedIndexes[1] = 1;
        super.setCollisionLayerName("RoomOneCollisions");

        Sprite tempSprite = new Sprite(new Texture(spritePathName));
        tempSprite.setScale(2f);
        playerView = new PlayerViewModel(tempSprite, this, touchpad);
        touchpad.setBounds(w - 600, 100, 500, 500); // Adjust the size and positioning as needed
        Gdx.input.setInputProcessor(screenStage);
        super.screenStage.addActor(touchpad);
        super.screenStage.addActor(healthText);
        super.screenStage.addActor(shootButton);

        watergun = new WatergunViewModel(this);
        watergun.setScale(2f);

        shootButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                watergun.attemptShoot();
            }
        });

        roomOne = new RoomOne(this);
        roomTwo = new RoomTwo(this);
        roomThree = new RoomThree(this);
        roomOne.setWestRoom(roomTwo);
        roomTwo.setEastRoom(roomOne);
        roomTwo.setNorthRoom(roomThree);
        roomThree.setSouthRoom(roomTwo);

        this.model = roomOne;
        playerView.setRoom(this.model);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(playerView.getX() + playerView.getWidth() * 0.50f, playerView.getY() + playerView.getHeight() * 0.50f, 0);
        camera.update();

        //System.out.println("time:" + delta);

        playerView.update(50);
        watergun.update();
        model.notifySubscribers();
        //model.notifyPowers();
        Player player = Player.getPlayer();


        if (playerView.getExitStatus() == 2 && playerView.getVelocity().x < 0) {
            //check if player is in west exit.
            model = model.getWestRoom();
            renderedIndexes[0] = model.getLayerIndex();
            player.setRoom(model);
            playerView.setRoom(model);
            playerView.setX(1500);
            playerView.refreshBounds();
            setCollisionLayerName(model.getCollisionLayerName());
            //System.out.println("Collision layer name: " + this.collisionLayerName);
        } else if (playerView.getExitStatus() == 4 && playerView.getVelocity().x > 0) {
            //Check if player is in east exit
            model = model.getEastRoom();
            renderedIndexes[0] = model.getLayerIndex();
            player.setRoom(model);
            playerView.setRoom(model);
            playerView.setX(75);
            playerView.refreshBounds();
            setCollisionLayerName(model.getCollisionLayerName());
        } else if (playerView.getExitStatus() == 1 && playerView.getVelocity().y > 0) {
            //Check if player is in north exit
            model = model.getNorthRoom();
            renderedIndexes[0] = model.getLayerIndex();
            player.setRoom(model);
            playerView.setRoom(model);
            playerView.setY(75);
            playerView.refreshBounds();
            setCollisionLayerName(model.getCollisionLayerName());
        } else if (playerView.getExitStatus() == 3 && playerView.getVelocity().y < 0) {
            //check if player is in south exit
            model = model.getSouthRoom();
            renderedIndexes[0] = model.getLayerIndex();
            player.setRoom(model);
            playerView.setRoom(model);
            playerView.setY(1400);
            playerView.refreshBounds();
            setCollisionLayerName(model.getCollisionLayerName());
        } else if (playerView.getExitStatus() == 5) {
            //check if player is in level exit
            Leaderboard.getBoard().addPlayerScore(player.getName(), score);
            game.setScreen(new LeaderboardScreen(game, null, "You Won! (Big Dubs)"));
        }

        String infoText = "Name: " + player.getName() + "\nHealth: "
                + player.getHealth() + "\nDifficulty: " + difficultyText + "\nScore: " + score + "\nStatus Effects: ";
        ArrayList<StatusEffect> statuses = player.getStatuses();
        for (StatusEffect status : statuses) {
            if (status.isActive()) {
                infoText += "\n" + status.toString();
            }
        }
        healthText.setText(infoText);


        if (player.getHealth() <= 0) {
            score = (score - 500 < 0) ? 0 : score - 500;
            Leaderboard.getBoard().addPlayerScore(player.getName(), score);
            game.setScreen(new LeaderboardScreen(game, null, "You Lost! (Skill Issue)"));
        }

        scoreCounter++;
        if(scoreCounter >= 100) {
            score = (score - scoreMult < 0) ? 0 : score - scoreMult;
            scoreCounter = 0;
        }

        tiledMapRenderer.getBatch().setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);


        tiledMapRenderer.setView(camera);
        //Since different layers represent different rooms, only some layers will be rendered.
        tiledMapRenderer.render(renderedIndexes);


        tiledMapRenderer.getBatch().begin();
        sr.setAutoShapeType(true);
        sr.begin();


        //float hitboxX = playerView.getBoundingRectangle().getX();
        //float hitboxY = playerView.getBoundingRectangle().getY();
        //float hitboxWidth = playerView.getBoundingRectangle().getWidth();
        //float hitboxHeight = playerView.getBoundingRectangle().getHeight();


        sr.setColor(Color.RED);
        //sr.rect(hitboxX - 10, hitboxY - 10, hitboxWidth + 20, hitboxHeight + 20);

        playerView.draw(tiledMapRenderer.getBatch());
        watergun.draw(tiledMapRenderer.getBatch());
        model.renderEnemies(tiledMapRenderer.getBatch());
        model.renderPowerups(tiledMapRenderer.getBatch());
        model.renderProjectiles(tiledMapRenderer.getBatch());

        sr.end();
        tiledMapRenderer.getBatch().end();

        screenStage.act();
        screenStage.draw();
    }



    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
