package com.bteam51.rgouelike.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * A generic sprite.
 * TO DO: Add Animation functionality
 */
public class Sprite {
    private Bitmap sheet;
    private int frameWidth;
    private int frameHeight;

    private Bitmap[][] frames;
    private int[] frameCount;

    // sprite constructor
    public Sprite(Context context, int rID, int frameWidth, int frameHeight, int[] frameCount) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.sheet = BitmapFactory.decodeResource(context.getResources(), rID, options);
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.frameCount = frameCount.clone();
        for (int currAnim = 0; currAnim < frameCount.length; currAnim++) {
            for (int i = 0; i < frameCount[currAnim]; i++) {
                frames[currAnim][i] = Bitmap.createBitmap(sheet, (frameHeight * currAnim),
                    (i * frameWidth), frameHeight, frameWidth);
            }
        }
    }

    public Bitmap getFrame(int anim, int frameNum) {
        return frames[anim][frameNum];
    }

}
