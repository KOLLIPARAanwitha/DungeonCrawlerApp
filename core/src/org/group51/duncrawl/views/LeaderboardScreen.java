package org.group51.duncrawl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Leaderboard;
import org.group51.duncrawl.viewmodels.LeaderViewModel;


public class LeaderboardScreen extends ScreenBase {
    Skin theme = new Skin(Gdx.files.internal("skins/pixthulhu-ui.json"));
    private Table menuTable = new Table(theme);
    private LeaderViewModel viewModel = new LeaderViewModel(this);

    private String winMessage;

    public LeaderboardScreen(GameManager game, AssetManager manager, String winMessage) {
        super(game, manager);
        this.winMessage = winMessage;

        Gdx.input.setInputProcessor(super.screenStage);
    }

    @Override
    public void show() {
        Image background = new Image(new Texture("main_menu_background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.screenStage.addActor(background);

        Table table = new Table(theme);
        table.setBounds(0,0, game.getWidth(), game.getHeight());

        Label leaderText = new Label(winMessage + "\nLeaderboard", theme);
        leaderText.setFontScale(5);
        leaderText.setAlignment(Align.center);

        Table leaderEntries = new Table(theme);
        ScrollPane scroll = new ScrollPane(leaderEntries);

        viewModel.handleLeaderBoard(leaderEntries);

        Label bottomText = new Label("-Bottom Of the List-", theme);
        bottomText.setFontScale(2);
        leaderEntries.add(bottomText);

        TextButton contButton = new TextButton("Continue?", theme);
        contButton.setPosition((float)(game.getWidth() * 0.15 ), (float) (game.getHeight() * 0.5));
        contButton.getLabel().setFontScale(2.0f);
        table.setFillParent(true);

        table.add(leaderText).expand().fillX().height(game.getHeight() / 4).width(game.getWidth() / 3);
        table.row();
        table.add(scroll).expand().fillX().height(game.getHeight() / 3).width(game.getWidth() / 2);
        table.row();
        table.add(contButton).expand().fillX().height(game.getHeight() / 7).width(game.getWidth() / 3);

        contButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(LeaderboardScreen.super.game, null));
            }
        });

        super.screenStage.addActor(table);
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
    }
}
