package org.group51.duncrawl.abstracts;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.group51.duncrawl.models.GameManager;

public abstract class ScreenBase implements Screen {

    protected Stage screenStage;
    protected final GameManager game;
    protected final AssetManager manager;
    protected Viewport viewport;

    public ScreenBase(GameManager game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        this.screenStage = new Stage(
            new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
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

    public Stage getStage() {
        return screenStage;
    }
}
