package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.viewmodels.components.Popup;
import org.group51.duncrawl.views.FirstLevel;
import org.group51.duncrawl.views.LeaderboardScreen;
import org.group51.duncrawl.views.SettingsScreen;

public class SettingsViewModel extends MasterViewModel {
    private ScreenBase linkedView;
    private GameManager game;
    private TextField nameField;
    private ButtonGroup spriteGroup;
    private ButtonGroup difficultyGroup;

    private Player p = Player.getPlayer();
    public SettingsViewModel(ScreenBase linkedView, GameManager game, TextField nameField,
         ButtonGroup spriteGroup, ButtonGroup difficultyGroup) {
        this.linkedView = linkedView;
        this.game = game;
        this.nameField = nameField;
        this.spriteGroup = spriteGroup;
        this.difficultyGroup = difficultyGroup;
    }


    public void handleRadioSelection(ButtonGroup group, int max) {
        group.setMaxCheckCount(max);
        group.setMinCheckCount(0);
        group.setUncheckLast(true);
    }

    public Button getSelected(ButtonGroup group, String message) {
        if (group.getChecked() == null) {
            throw new IllegalArgumentException(message);
        } else {
            return group.getChecked();
        }
    }

    public void handleSubmitButton(Button contButton) {
        contButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    p.setName(nameField.getText());
                    Popup popup = new Popup();
                    popup.show(linkedView.getStage());
                    if (spriteGroup.getChecked() == null) {
                        popup.setText("Must Choose a Sprite");
                        popup.show(linkedView.getStage());
                        throw new IllegalArgumentException("Must Choose a Sprite");
                    }
                    if (difficultyGroup.getChecked() == null) {
                        popup.setText("Must Choose a Difficulty");
                        popup.show(linkedView.getStage());
                        throw new IllegalArgumentException("Must Choose a Difficulty");
                    }
                    Player player = Player.getPlayer();
                    player.setName(nameField.getText());
                    int difficulty = difficultyGroup.getCheckedIndex();
                    String spritePathName;
                    if (spriteGroup.getCheckedIndex() == 0) {
                        spritePathName = "sprites/characters/lime_player.png";
                    } else if (spriteGroup.getCheckedIndex() == 1) {
                        spritePathName = "sprites/characters/purple_player.png";
                    } else {
                        spritePathName = "sprites/characters/red_player.png";
                    }

                    game.setScreen(new FirstLevel(game, null, difficulty, spritePathName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void handleRadioButton(final Button btn, ButtonGroup group) {
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (btn.isChecked()) {
                    System.out.println("Button 1 is checked");
                } else {
                    System.out.println("Button 1 is unchecked");
                }
            }
        });
    }
}