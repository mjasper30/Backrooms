package com.macaraeg_jasper.backrooms;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Enemy {
    Bitmap[] enemy = new Bitmap[3];
    int enemyFrame = 0;
    int enemyX, enemyY, enemyVelocity;
    Random random;

    public Enemy(Context context){
        enemy[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bacteria_1);
        enemy[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bacteria_2);
        enemy[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bacteria_1);
        random = new Random();
        resetPosition();
    }

    public Bitmap getEnemy(int enemyFrame){
        return enemy[enemyFrame];
    }

    public int getEnemyWidth(){
        return enemy[0].getWidth();
    }

    public int getEnemyHeight(){
        return enemy[0].getHeight();
    }

    public void resetPosition() {
        enemyX = random.nextInt(GameView.dWidth - getEnemyWidth());
        enemyY = -200 + random.nextInt(600) * -1;
        enemyVelocity = 35 + random.nextInt(16);
    }
}
