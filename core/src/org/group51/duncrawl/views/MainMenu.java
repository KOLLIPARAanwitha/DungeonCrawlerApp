package org.group51.duncrawl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.viewmodels.MainViewModel;


public class MainMenu extends ScreenBase {

    private Table menuTable = new Table();
    private Label text;
    private MainViewModel viewModel;
    private TextButton startButton;
    private Music backgroundMusic;

     Skin theme = new Skin(Gdx.files.internal("skins/pixthulhu-ui.json"));


    public MainMenu(GameManager game, AssetManager manager) {
        super(game, manager);
        this.viewModel = new MainViewModel(this, game);
        Gdx.input.setInputProcessor(super.screenStage);
        /*
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu_music.ogg"));
        backgroundMusic.play();
        backgroundMusic.setVolume(1f);
        backgroundMusic.setLooping(true);

         */
    }

    @Override
    public void show() {
        Image background = new Image(new Texture("main_menu_background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.screenStage.addActor(background);

        Label test = new Label("Sandy Rgouelike", theme);
        test.setFontScale(10);
        test.setAlignment(Align.center);
        startButton = new TextButton("Enter The Dungeon...", theme);
        TextButton quitButton = new TextButton("Quit", theme);

        menuTable.setFillParent(true);
        startButton.getLabel().setFontScale(2.0f);
        menuTable.add(startButton).pad(50);
        menuTable.row();
        quitButton.getLabel().setFontScale(2.0f);
        menuTable.add(quitButton).pad(50);
        menuTable.row();

        viewModel.handleStartButton(startButton);
        viewModel.handleQuitButton(quitButton);
        test.setPosition(game.getWidth() / 2, (float)(game.getHeight() * 0.9), Align.top);

        this.screenStage.addActor(menuTable);
        this.screenStage.addActor(test);
        Gdx.input.setInputProcessor(super.screenStage);
        background.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(1.0F)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.screenStage.act();
        super.screenStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        //backgroundMusic.stop();
    }
}
