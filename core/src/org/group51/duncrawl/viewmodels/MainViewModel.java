package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Leaderboard;
import org.group51.duncrawl.views.LeaderboardScreen;
import org.group51.duncrawl.views.MainMenu;
import org.group51.duncrawl.views.SettingsScreen;

public class MainViewModel extends MasterViewModel {
    private ScreenBase linkedView;
    private GameManager game;
    public MainViewModel(ScreenBase linkedView, GameManager game) {
        this.linkedView = linkedView;
        this.game = game;
    }

    public void handleStartButton(Button startBtn) {
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new SettingsScreen(game, null));
            }
        });
    }

    public void handleQuitButton(Button quitBtn) {
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }
}
