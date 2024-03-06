package com.bteam51.rgouelike.game_elements.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.bteam51.rgouelike.game_elements.models.Player;
//import com.bteam51.rgouelike.utils.Animatable;
import com.bteam51.rgouelike.utils.Sprite;

/**
 * The active player view.
 * Handles the UI components
 * of the player.
 */
public class PlayerView extends View {
    private Player player;
    private Sprite playerSprite;
    private float x;
    private float y;

    public PlayerView(Context context, Player pl) {
        super(context);
        player = pl;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(playerSprite.getFrame(1, 1), this.x, this.y, null);
    }
    public void updatePos(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return x;
    }
}
