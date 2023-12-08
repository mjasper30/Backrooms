package com.macaraeg_jasper.backrooms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import androidx.core.content.res.ResourcesCompat;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    public static MediaPlayer mediaPlayer;
    Bitmap background, ground, character;
    Rect rectBackground, rectGround;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 120;
    int points = 0;
    int life = 3;
    static int dWidth, dHeight;
    Random random;
    float characterX, characterY;
    float oldX;
    float oldCharacterX;
    ArrayList<Enemy> enemies;
    ArrayList<Glitch> glitches;

    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.floor);
        character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0,0,dWidth,dHeight);
        rectGround = new Rect(0, dHeight - (ground.getHeight() + 90), dWidth, dHeight);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        textPaint.setColor(Color.rgb(255, 165, 0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.zero));
        healthPaint.setColor(Color.GREEN);
        random = new Random();
        characterX = dWidth/2 - character.getWidth()/2;
        characterY = dHeight - ground.getHeight() - character.getHeight() - 92;
        enemies = new ArrayList<>();
        glitches = new ArrayList<>();
        for (int i=0; i<5; i++){
            Enemy enemy = new Enemy(context);
            enemies.add(enemy);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(ground, null, rectGround, null);
        canvas.drawBitmap(character, characterX, characterY, null);
        for (int i=0; i<enemies.size();i++){
            canvas.drawBitmap(enemies.get(i).getEnemy(enemies.get(i).enemyFrame),enemies.get(i).enemyX,enemies.get(i).enemyY,null);
            enemies.get(i).enemyFrame++;
            if (enemies.get(i).enemyFrame > 2){
                enemies.get(i).enemyFrame = 0;
            }
            enemies.get(i).enemyY += enemies.get(i).enemyVelocity;
            if (enemies.get(i).enemyY + enemies.get(i).getEnemyHeight() >= dHeight - ground.getHeight()){
                points += 10;
                Glitch glitch = new Glitch(context);
                glitch.glitchX = enemies.get(i).enemyX;
                glitch.glitchY = enemies.get(i).enemyY;
                glitches.add(glitch);
                enemies.get(i).resetPosition();
            }
        }
        for(int i=0; i < enemies.size(); i++){
            if(enemies.get(i).enemyX + enemies.get(i).getEnemyWidth() >= characterX
                    && enemies.get(i).enemyX <= characterX + character.getWidth()
                    && enemies.get(i).enemyY + enemies.get(i).getEnemyWidth() >= characterY
                    && enemies.get(i).enemyY + enemies.get(i).getEnemyWidth() >= characterY + character.getHeight()){
                life--;
                enemies.get(i).resetPosition();
                if (life == 0){
                    mediaPlayer.stop();
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        }

        for(int i=0; i<glitches.size();i++){
            canvas.drawBitmap(glitches.get(i).getGlitch(glitches.get(i).glitchFrame), glitches.get(i).glitchX, glitches.get(i).glitchY, null);
            glitches.get(i).glitchFrame++;
            if (glitches.get(i).glitchFrame > 3){
                glitches.remove(i);
            }
        }
        
        if(life == 2){
            healthPaint.setColor(Color.YELLOW);
        }else if(life == 1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30, dWidth-200+60*life, 80, healthPaint);
        canvas.drawText("" + points, 20, TEXT_SIZE, textPaint);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (touchY >= characterY){
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldCharacterX = characterX;
            }
            if (action == MotionEvent.ACTION_MOVE){
                float shift = oldX - touchX;
                float newCharacterX = oldCharacterX - shift;
                if (newCharacterX <= 0){
                    characterX = 0;
                }else if(newCharacterX >= dWidth - character.getWidth()){
                    characterX = dWidth - character.getWidth();
                }else{
                    characterX = newCharacterX;
                }
            }
        }
        return true;
    }
}
