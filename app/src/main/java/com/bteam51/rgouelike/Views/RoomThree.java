package com.bteam51.rgouelike.Views;

import android.content.Intent;
//import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bteam51.rgouelike.R;
import com.bteam51.rgouelike.game_elements.models.Difficulty;
import com.bteam51.rgouelike.game_elements.viewmodels.LevelViewRenderer;

import java.util.Timer;
import java.util.TimerTask;
//import java.util.logging.Level;

/**
 The first room the player enters.
 */
public class RoomThree extends AppCompatActivity {

    private Button endButton;
    private int screenWidth;
    private int screenHeight;
    private Timer gameTimer;
    private LevelViewRenderer levelViewRenderer;
    private TextView scoreView;
    private TextView playerHealthView;
    private TextView playerNameView;
    private TextView difficultyView;
    private ImageView playerImageView;

    /**
     * Method runs on app instantiation.
     * @param savedInstanceState Saved Instance of the App
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_three);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        levelViewRenderer = LevelViewRenderer.getLevelViewRenderer();
        Difficulty diff = levelViewRenderer.getDifficulty();
        String playerName = levelViewRenderer.getPlayerName();
        String difficultyText = "Difficulty: ";
        int modelId = levelViewRenderer.getCharSprite();

        scoreView = findViewById(R.id.scoreLabelRoom3);
        playerImageView = findViewById(R.id.playerImageRoomThree);
        playerHealthView = findViewById(R.id.healthTextRoomThree);
        playerNameView = findViewById(R.id.nameTextRoomThree);
        difficultyView = findViewById(R.id.difficultyTextRoomThree);

        switch (modelId) {
        case 0:
            playerImageView.setImageDrawable(getResources().getDrawable(R.drawable.red_sprite,
                    null));
            break;
        case 1:
            playerImageView.setImageDrawable(getResources().getDrawable(R.drawable.lime_sprite,
                    null));
            break;
        case 2:
            playerImageView.setImageDrawable(getResources().getDrawable(R.drawable.purple_sprite,
                    null));
            break;
        default:
        }
        switch (diff) {
        case EASY:
            difficultyText += "Easy";
            break;
        case MEDIUM:
            difficultyText += "Medium";
            break;
        case HARD:
            difficultyText += "Hard";
            break;
        case ROGUELIKE:
            difficultyText += "Rgouelike";
            break;
        default:
        }

        playerHealthView.setX((float) (screenWidth / 10.0));
        playerHealthView.setY((float) (screenHeight / 10.0));

        playerNameView.setX((float) (screenWidth / 10.0));
        playerNameView.setY((float) (screenHeight / 10.0 * 0.3));

        difficultyView.setX((float) (screenWidth / 10.0 * 8));
        difficultyView.setY((float) (screenHeight / 10.0));

        scoreView.setX((float) (screenWidth / 10.0 * 8));
        scoreView.setY((float) (screenHeight / 10.0 * 0.3));

        playerImageView.setX((float) (screenWidth / 10.0 * 4));
        playerImageView.setY((float) (screenHeight / 10.0 * 3.0));

        playerHealthView.setText("Health: "
                + Integer.toString(levelViewRenderer.getPlayerHealth()));
        playerNameView.setText(playerName);
        difficultyView.setText(difficultyText);

        endButton = findViewById(R.id.endButton);
        endButton.setOnClickListener(l -> {
            android.util.Log.d("GameRenderer", "End button clicked");
            Intent endIntent = new Intent(this, EndActivity.class);
            startActivity(endIntent);
            finish();
        });
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        levelViewRenderer.updateFrame();
                        scoreView.setText("Score: "
                                + Integer.toString(levelViewRenderer.getScore()));
                    }
                });
            }
        }, 0, 50);
    }
}