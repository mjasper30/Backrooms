package com.macaraeg_jasper.backrooms;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Glitch {
    Bitmap[] glitch = new Bitmap[4];
    int glitchFrame = 0;
    int glitchX, glitchY;

    public Glitch(Context context){
        glitch[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.destroy_1);
        glitch[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.destroy_2);
        glitch[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.destroy_3);
        glitch[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.destroy_3);
    }

    public Bitmap getGlitch(int glitchFrame){
        return glitch[glitchFrame];
    }
}
