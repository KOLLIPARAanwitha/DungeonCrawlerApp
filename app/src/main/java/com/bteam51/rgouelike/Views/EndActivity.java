package com.bteam51.rgouelike.Views;

import android.content.Intent;
//import android.content.res.Configuration;
import android.os.Bundle;

//import android.view.View;

import android.util.Log;

import android.widget.Button;
import android.widget.LinearLayout;
//import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bteam51.rgouelike.R;
import com.bteam51.rgouelike.game_elements.models.LeaderBoard;
import com.bteam51.rgouelike.game_elements.models.LevelRenderer;
import com.bteam51.rgouelike.game_elements.models.Player;

//import org.w3c.dom.Text;

import java.util.Arrays;

/**
The end screen of the game when gameplay is complete.
 */
public class EndActivity extends AppCompatActivity {

    private Button endButton;

    private int screenWidth;
    private int screenHeight;

    private LeaderBoard board = LeaderBoard.getBoard();

    private Player player = Player.getPlayer();

    private LevelRenderer levRender = LevelRenderer.getLevelRenderer();

    /**
     * Method runs on app instantiation.
     * @param savedInstanceState Saved Instance of the App
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        endButton = findViewById(R.id.endButton);

        endButton.setText("Play Again");


        LeaderBoard.PlayerScore curr = board.addPlayerScore(player.getName(), levRender.getScore());
        String[] strings = new String[5];
        LeaderBoard.PlayerScore[] topPlayers = board.getTopK(5);

        LinearLayout leaderLay = (LinearLayout) findViewById(R.id.LeaderBoardLayout);
        TextView currAttpText = (TextView) findViewById(R.id.CurrAttemptText);
        currAttpText.setText("Your attempt: " + curr.toString());
        Log.i("Debug", Arrays.toString(topPlayers));
        for (LeaderBoard.PlayerScore score : topPlayers) {
            Log.i("Curr Score:", "Test");
            if (score == null) {
                TextView leaderText = new TextView(this);
                leaderText.setText("...");
                leaderLay.addView(leaderText);
                break;
            }
            Log.d("Test", "Test");

            TextView leaderText = new TextView(this);
            leaderText.setText(score.toString());
            leaderText.setTextSize(10);
            leaderLay.addView(leaderText);
        }

        endButton.setOnClickListener(l -> {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        });
    }
}
