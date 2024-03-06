package com.bteam51.rgouelike.Views;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bteam51.rgouelike.R;
import com.bteam51.rgouelike.game_elements.viewmodels.LevelViewRenderer;


//import com.bteam51.rgouelike.Views.SettingsMenu;



//import com.bteam51.rgouelike.framework.EndActivity;

/**
 * The Main Activity of the game. This will
 * hold all details on game settings, graphics,
 * etc. Essentially the main menu
 */
public class MainActivity extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;
    private TextView titleText;
    private Button startButton;
    private Button exitButton;

    /**
     * Method runs on app instantiation.
     * @param savedInstanceState Saved Instance of the App
     */
    protected void onCreate(Bundle savedInstanceState) {
        //load any saved states
        super.onCreate(savedInstanceState);
        //TO DO: Create a menu
        setContentView(R.layout.main_menu);



        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        LevelViewRenderer lvr = LevelViewRenderer.getLevelViewRenderer();
        lvr.setScreenHeight(screenHeight);
        lvr.setScreenWidth(screenWidth);

        //titleText = findViewById(R.id.titleTextView);
        exitButton = findViewById(R.id.exitButton);
        startButton = findViewById(R.id.settingsButton);

        //titleText.setText("Welcome to Sandy Rgouelike");
        //titleText.setX((float) (screenWidth / 4.0));
        //titleText.setY((float) (screenHeight / 10.0));

        //exitButton.setText("Close App");
        //exitButton.setX((float) (screenWidth / 2.0));
        //exitButton.setY((float) (screenHeight / 2.0));

        //startButton.setText("Start Game");
        //startButton.setX((float) (screenWidth / 2.0));
        //startButton.setY((float) (screenHeight / 3.0));


        startButton.setOnClickListener(v -> {
            Intent settings = new Intent(this, SettingsMenu.class);
            startActivity(settings);
            finish();
        });

        exitButton.setOnClickListener(v -> {
            finish();
        });


    }
}
