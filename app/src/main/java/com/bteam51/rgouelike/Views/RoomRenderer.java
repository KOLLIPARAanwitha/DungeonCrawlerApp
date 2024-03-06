package com.bteam51.rgouelike.Views;

import android.content.Intent;
//import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bteam51.rgouelike.R;

import com.bteam51.rgouelike.game_elements.models.Difficulty;
//import com.bteam51.rgouelike.game_elements.models.Player;
//import com.bteam51.rgouelike.game_elements.models.RoomTypeTwo;
import com.bteam51.rgouelike.game_elements.viewmodels.LevelViewRenderer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * View for the first room type.
 */
public class RoomRenderer extends AppCompatActivity {

    private TextView playerHealthView;
    private TextView playerNameView;
    private TextView difficultyView;
    private TextView scoreView;
    private ImageView playerImageView;
    private ImageView background;
    private LevelViewRenderer levelViewRenderer;

    private Button roomTwoButton; //temp button
    private int screenWidth;
    private int screenHeight;
    private Timer gameTimer;
    //Temporary variables until central game object is in place


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TO DO: Make rendering screen
        setContentView(R.layout.game_screen);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        levelViewRenderer = LevelViewRenderer.getLevelViewRenderer();
        background = findViewById(R.id.background);
        setBackground();




        Difficulty diff = levelViewRenderer.getDifficulty();
        String playerName = levelViewRenderer.getPlayerName();
        String difficultyText = "Difficulty: ";
        int modelId = getIntent().getIntExtra("model", 100);
        playerImageView = findViewById(R.id.playerImageView);
        scoreView = findViewById(R.id.scoreLabel);

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


        playerHealthView = findViewById(R.id.playerHealthView);
        playerNameView = findViewById(R.id.playerNameView);
        difficultyView = findViewById(R.id.difficultyView);
        roomTwoButton = findViewById(R.id.roomTwoButton);

        playerHealthView.setX((float) (screenWidth / 10.0));
        playerHealthView.setY((float) (screenHeight / 10.0 - 10.0));

        playerNameView.setX((float) (screenWidth / 10.0));
        playerNameView.setY((float) (screenHeight / 10.0 * 0.3 - 10.0));

        difficultyView.setX((float) (screenWidth / 10.0 * 8));
        difficultyView.setY((float) (screenHeight / 10.0 - 10.0));

        scoreView.setX((float) (screenWidth / 10.0 * 8));
        scoreView.setY((float) (screenHeight / 10.0 * 0.3 - 10.0));

        playerImageView.setX((float) (screenWidth / 10.0 * 4));
        playerImageView.setY((float) (screenHeight / 10.0 * 3.0));

        roomTwoButton.setX((float) (screenWidth / 5.0 * 2));
        roomTwoButton.setY((float) (screenHeight / 5.0 * 3));

        playerHealthView.setText("Health: "
                + Integer.toString(levelViewRenderer.getPlayerHealth()));
        playerNameView.setText(playerName);
        difficultyView.setText(difficultyText);
        //roomOneButton.setText("Press me!");
        roomTwoButton.setVisibility(View.INVISIBLE);


        roomTwoButton.setOnClickListener(l -> {
            /**
            android.util.Log.d("GameRenderer", "Room Two button clicked");
            Intent roomTwoIntent = new Intent(this, RoomTwo.class);
            startActivity(roomTwoIntent);
            finish();
             **/
            //levelViewRenderer.setCurRoom(new RoomTypeTwo());
            //levelViewRenderer.setRoomChange(true);
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
                        playerImageView.setX((float) levelViewRenderer.playerViewX());
                        playerImageView.setY((float) levelViewRenderer.playerViewY());
                        if (levelViewRenderer.isRoomChange()) {
                            setBackground();
                            levelViewRenderer.setRoomChange(false);
                        }
                    }
                });
            }
        }, 0, 50);

    }

    public void setBackground() {
        int roomId = levelViewRenderer.getRoomId();
        if (roomId == 1) {
            background.setImageDrawable(getResources().getDrawable(R.drawable.room_one, null));
        } else if (roomId == 2) {
            background.setImageDrawable(getResources().getDrawable(R.drawable.room_two, null));
        } else if (roomId == 3) {
            background.setImageDrawable(getResources().getDrawable(R.drawable.room_three, null));
        } else if (roomId == 4) {
            android.util.Log.d("GameRenderer", "Room Two button clicked");
            Intent roomTwoIntent = new Intent(this, EndActivity.class);
            startActivity(roomTwoIntent);
            finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LevelViewRenderer lvr = LevelViewRenderer.getLevelViewRenderer();
        if (keyCode == KeyEvent.KEYCODE_S || keyCode == KeyEvent.KEYCODE_W
                || keyCode == KeyEvent.KEYCODE_A || keyCode == KeyEvent.KEYCODE_D) {
            lvr.updateLastDir(keyCode);
        }
        return true;

    }

}
