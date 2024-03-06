package org.group51.duncrawl.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.group51.duncrawl.models.GameManager;

public abstract class LevelBase implements Screen {

    protected Stage screenStage;
    protected final GameManager game;
    protected final AssetManager manager;
    protected Viewport viewport;
    protected TiledMap tiledMap;
    public OrthographicCamera camera;

    protected String collisionLayerName;
    protected String exitLayerName;

    protected int score;


    public LevelBase(GameManager game, AssetManager manager, TiledMap tiledMap) {
        this.camera = new OrthographicCamera();
        this.game = game;
        this.tiledMap = tiledMap;
        this.manager = manager;
        this.screenStage = new Stage(
            new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        collisionLayerName = "RoomTwoCollisions";
        //exitLayerName = "RoomOneExits";
    }

    public void setCollisionLayerName(String gName) {
        collisionLayerName = gName;
    }

    public String getCollisionLayerName() {
        return collisionLayerName;
    }

    public void setExitLayerName(String gName) {
        exitLayerName = gName;
    }

    public void setScore(int gScore) {
        score = gScore;
    }
    public int getScore() {
        return score;
    }

    @Override
    public void show() {
        game.getBatch().end();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }

    public MapObjects getCollisionLayer() {
        return tiledMap.getLayers().get(collisionLayerName).getObjects();
    }

    public MapObjects getExitLayer() {
        return tiledMap.getLayers().get(exitLayerName).getObjects();
    }

    public MapObjects getCollisionLayer(String name) {
        return tiledMap.getLayers().get(name).getObjects();
    }
}
