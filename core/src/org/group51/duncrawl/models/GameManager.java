package org.group51.duncrawl.models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import org.group51.duncrawl.views.MainMenu;

public class GameManager extends Game {

    private int width;
    private int height;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    AssetManager am;
    Leaderboard currLeader = Leaderboard.getBoard();

    @Override
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        am = new AssetManager();
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        setScreen(new MainMenu(this, am));
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public int getHeight() {
        return height;
    }

    public Leaderboard getInstanceBoard() {
        return Leaderboard.getBoard();
    }
    public int getWidth() {
        return width;
    }

}
