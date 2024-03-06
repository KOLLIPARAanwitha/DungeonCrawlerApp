package org.group51.duncrawl.viewmodels.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Popup extends Table {

    private Label label;
    private Skin theme = new Skin(Gdx.files.internal("skins/pixthulhu-ui.json"));
    public Popup() {
        setBackground(theme.getDrawable("window"));
        pad(10f);
        this.label = new Label("", theme);
        label.setFontScale(4.0f);
        add(label).pad(10f);
        Button closeButton = new TextButton("X", theme);
        setWidth(label.getWidth() + 100);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });
        row();
        add(closeButton).padTop(10f);
        pack();
    }

    public void setText(String s) {
        label.setText(s);
    }

    public void show(Stage stage) {
        stage.addActor(this);
        setPosition((stage.getWidth() - getWidth()) / 2, (stage.getHeight() - getHeight()) / 2);
        setWidth(stage.getWidth() / 2);
        setHeight(stage.getHeight() / 2);
    }

    public void hide() {
        remove();
    }
}
