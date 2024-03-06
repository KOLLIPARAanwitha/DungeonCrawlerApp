package org.group51.duncrawl.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.GameManager;
import org.group51.duncrawl.models.Player;
import org.group51.duncrawl.viewmodels.SettingsViewModel;

public class SettingsScreen extends ScreenBase {
    Skin theme = new Skin(Gdx.files.internal("skins/pixthulhu-ui.json"));
    private Table menuTable = new Table(theme);
    private Label text;
    private TextButton startButton;

    private TextField nameField = new TextField("Name", theme);
    private ButtonGroup spriteGroup = new ButtonGroup();
    private ButtonGroup difficultyGroup = new ButtonGroup();
    private SettingsViewModel viewModel;

    private Player p = Player.getPlayer();


    public SettingsScreen(GameManager game, AssetManager manager) {
        super(game, manager);
        this.viewModel = new SettingsViewModel(this, game, nameField,
            spriteGroup, difficultyGroup);
        Gdx.input.setInputProcessor(super.screenStage);
    }

    @Override
    public void show() {
        Image background = new Image(new Texture("main_menu_background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.screenStage.addActor(background);

        Table table = new Table(theme);
        table.setBounds(0,0, game.getWidth(), game.getHeight());
        table.debug();

        Label leaderText = new Label("Player Settings", theme);
        leaderText.setFontScale(4);
        leaderText.setAlignment(Align.center);

        Table leaderEntries = new Table(theme);
        ScrollPane scroll = new ScrollPane(leaderEntries);

        buildSettingsMenu(leaderEntries);

        TextButton contButton = new TextButton("Start", theme);
        contButton.setPosition((float)(game.getWidth() * 0.15 ), (float) (game.getHeight() * 0.5));
        contButton.getLabel().setFontScale(2.0f);
        table.setFillParent(true);

        table.add(leaderText).expand().fillX().height(game.getHeight() / 5).width(game.getWidth() / 3);
        table.row();
        table.add(scroll).expand().fillX().height(game.getHeight() * 0.6f).width(game.getWidth() * 0.7f);
        table.row();
        table.add(contButton).expand().fillX().height(game.getHeight() / 7).width(game.getWidth() / 3);

        viewModel.handleSubmitButton(contButton);
        viewModel.handleRadioSelection(spriteGroup, 1);
        viewModel.handleRadioSelection(difficultyGroup, 1);
        this.screenStage.addActor(table);
    }

    public void buildSettingsMenu(Table table) {
        TextField.TextFieldStyle textFieldStyle = theme.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().scale(3.0f);
        nameField.setStyle(textFieldStyle);
        table.add("Player Name:").expand().fillX().height(game.getHeight() / 8).width(game.getWidth() / 3);
        table.add(nameField).expand().fillX().height(game.getHeight() / 8).width(game.getWidth() / 3);
        table.row();
        table.add("Select Sprite:").expand().fillX().height(game.getHeight() / 8)
            .width(game.getWidth() / 3).align(Align.center);
        table.row();

        Texture limeSprite = new Texture("sprites/characters/lime_sprite.png");
        Texture purpleSprite = new Texture("sprites/characters/purple_sprite.png");
        Texture redSprite = new Texture("sprites/characters/red_sprite.png");

        //Button Group
        ImageButton sprite1Button = new ImageButton(theme, "toggle");
        ImageButton sprite2Button = new ImageButton(theme, "toggle2");
        ImageButton sprite3Button = new ImageButton(theme, "toggle3");

        spriteGroup.add(sprite1Button, sprite2Button, sprite3Button);

        sprite1Button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(limeSprite));
        sprite2Button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(purpleSprite));
        sprite3Button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(redSprite));


        table.add(sprite1Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center);
        table.add(sprite2Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center).row();
        table.add(sprite3Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center).row();
        table.add("Select Difficulty:").expand().fillX().height(game.getHeight() / 8)
                .width(game.getWidth() / 3).align(Align.center).row();

        //Difficulty
        TextButton diff1Button = new TextButton("Easy", theme, "toggle");
        TextButton diff2Button = new TextButton("Medium", theme, "toggle2");
        TextButton diff3Button = new TextButton("Hard", theme, "toggle3");

        table.add(diff1Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center);
        table.add(diff2Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center).row();
        table.add(diff3Button).expand().pad(10).fillX().height(game.getHeight() / 4)
                .width(game.getWidth() / 10).align(Align.center);

        difficultyGroup.add(diff1Button, diff2Button, diff3Button);

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
        super.screenStage.dispose();
        super.dispose();
        this.nameField.remove();
        this.nameField.setDisabled(true);
    }

    @Override
    public void hide() {
        super.screenStage.dispose();
        super.dispose();
        this.nameField.remove();
        this.nameField.setDisabled(true);
    }
}
