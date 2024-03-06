package com.bteam51.rgouelike.Views;

import android.content.Intent;
import android.os.Bundle;
//import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bteam51.rgouelike.R;
import com.bteam51.rgouelike.game_elements.models.Difficulty;
import com.bteam51.rgouelike.game_elements.models.Room;
import com.bteam51.rgouelike.game_elements.models.RoomTypeOne;
import com.bteam51.rgouelike.game_elements.models.RoomTypeThree;
import com.bteam51.rgouelike.game_elements.models.RoomTypeTwo;
import com.bteam51.rgouelike.game_elements.viewmodels.LevelViewRenderer;
//import com.bteam51.rgouelike.game_elements.Difficulty;
//import com.bteam51.rgouelike.utils.Sprite;

/**
 * The Game Settings Menu. Ideally will be a popup
 * instead of a screen.
 */
public class SettingsMenu extends AppCompatActivity {
    private Difficulty difficulty; //temporarily an int
    private int charSprite; //temporarily an int


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Room temp = new RoomTypeTwo();
        temp = new RoomTypeThree();
        temp = new RoomTypeOne();
        setContentView(R.layout.settings);
        EditText playerNameField = findViewById(R.id.playerNameField);
        Button confirmation = findViewById(R.id.startButton);

        confirmation.setOnClickListener(e -> {
            String name = playerNameField.getText().toString();
            RadioGroup spriteSelection = findViewById(R.id.spriteSelector);
            RadioGroup difficultySettings = findViewById(R.id.difficultyGroup);
            LevelViewRenderer levelViewRenderer = LevelViewRenderer.getLevelViewRenderer();
            try {
                if (name.trim().isEmpty()) {
                    playerNameField.setError("Make sure your name isn't just Spaces");
                    throw new IllegalArgumentException("Make sure your name isn't just Spaces");
                }
                if (name.isEmpty()) {
                    System.out.println("Empty Name");
                    playerNameField.setError("Remember to Set a Character Name");
                    throw new IllegalArgumentException("Make sure your name isn't just Spaces");
                }
                int idDiff = difficultySettings.getCheckedRadioButtonId();
                if (idDiff == R.id.difficultyEasy) {
                    difficulty = Difficulty.EASY;
                } else if (idDiff == R.id.difficultyMed) {
                    difficulty = Difficulty.MEDIUM;
                } else if (idDiff == R.id.difficultyHard) {
                    difficulty = Difficulty.HARD;
                } else {
                    difficulty = Difficulty.ROGUELIKE;
                }
                int idChar = spriteSelection.getCheckedRadioButtonId();
                if (idChar == R.id.charSprite1Radio) {
                    charSprite = 0;
                } else if (idChar == R.id.charSprite2Radio) {
                    charSprite = 1;
                } else {
                    charSprite = 2;
                }
                Intent game = new Intent(this, RoomRenderer.class);
                levelViewRenderer.setCurRoom(new RoomTypeThree());
                levelViewRenderer.resetLevel(difficulty);
                levelViewRenderer.setPlayerName(name);
                levelViewRenderer.setCharSprite(charSprite);
                game.putExtra("model", charSprite);
                startActivity(game);
                finish();
            } catch (IllegalArgumentException excep) {
                confirmation.setError("Please Fix Above Fields");
            }
        });
    }
}