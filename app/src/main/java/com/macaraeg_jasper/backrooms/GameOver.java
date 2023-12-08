package com.macaraeg_jasper.backrooms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    TextView tvPoints;
    TextView tvHighest;
    TextView tvScores;
    TableLayout tlScoresNButtons;
    ImageView ivSmiler;
    SharedPreferences sharedPreferences;
    private static MediaPlayer mediaPlayer;
    int player1, player2, player3, player4, player5, player6, player7, player8, player9, player10;
    int highest;
    int points;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        GameOver.mediaPlayer = MediaPlayer.create(this, R.raw.smiley);
        GameOver.mediaPlayer.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        tvPoints = findViewById(R.id.tvPoints);
        tvHighest = findViewById(R.id.tvHighest);
        tvScores = findViewById(R.id.tvScores);
        ivSmiler = findViewById(R.id.smiler);
        tlScoresNButtons = findViewById(R.id.scoreNbuttons);
        points = getIntent().getExtras().getInt("points");
        tvPoints.setText("" + points);
        sharedPreferences = getSharedPreferences("my_pref", 0);
        highest = sharedPreferences.getInt("highest", 0);
        player1 = sharedPreferences.getInt("player1", 0);
        player2 = sharedPreferences.getInt("player2", 0);
        player3 = sharedPreferences.getInt("player3", 0);
        player4 = sharedPreferences.getInt("player4", 0);
        player5 = sharedPreferences.getInt("player5", 0);
        player6 = sharedPreferences.getInt("player6", 0);
        player7 = sharedPreferences.getInt("player7", 0);
        player8 = sharedPreferences.getInt("player8", 0);
        player9 = sharedPreferences.getInt("player9", 0);
        player10 = sharedPreferences.getInt("player10", 0);

        //replace if there is a high score
        if(points > player10){
            player10 = points;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player10", player10);
            editor.apply();
        }
        if(points > player9){
            int temp = player9;
            player9 = points;
            player10 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player10", player10);
            editor.putInt("player9", player10);
            editor.apply();
        }
        if(points > player8){
            int temp = player8;
            player8 = points;
            player9 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player9", player9);
            editor.putInt("player8", player8);
            editor.apply();
        }
        if(points > player7){
            int temp = player7;
            player7 = points;
            player8 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player8", player8);
            editor.putInt("player7", player7);
            editor.apply();
        }
        if(points > player6){
            int temp = player6;
            player6 = points;
            player7 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player7", player7);
            editor.putInt("player6", player6);
            editor.apply();
        }
        if(points > player5){
            int temp = player5;
            player5 = points;
            player6 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player6", player6);
            editor.putInt("player5", player5);
            editor.apply();
        }
        if(points > player4){
            int temp = player4;
            player4 = points;
            player5 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player5", player5);
            editor.putInt("player4", player4);
            editor.apply();
        }
        if(points > player3){
            int temp = player3;
            player3 = points;
            player4 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player4", player4);
            editor.putInt("player3", player3);
            editor.apply();
        }
        if(points > player2){
            int temp = player2;
            player2 = points;
            player3 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player3", player3);
            editor.putInt("player2", player2);
            editor.apply();
        }
        if(points > player1){
            int temp = player1;
            player1 = points;
            player2 = temp;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("player2", player2);
            editor.putInt("player1", player1);
            editor.apply();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
            //display scores
                tvScores.setText("Top 1 - " + player1 + "\n" +
                        "Top 2 - " + player2 + "\n" +
                        "Top 3 - " + player3 + "\n" +
                        "Top 4 - " + player4 + "\n" +
                        "Top 5 - " + player5 + "\n" +
                        "Top 6 - " + player6 + "\n" +
                        "Top 7 - " + player7 + "\n" +
                        "Top 8 - " + player8 + "\n" +
                        "Top 9 - " + player9 + "\n" +
                        "Top 10 - " + player10 + "\n");

                tlScoresNButtons.setVisibility(View.VISIBLE);
                ivSmiler.setVisibility(View.GONE);

                if (points > highest){
                    highest = points;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("highest", highest);
                    editor.commit();
                }
                tvHighest.setText("" + highest);
            }
        }, 6000);


    }

    public void restart(View view){
        mediaPlayer.stop();
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        mediaPlayer.stop();
        finish();
    }
}
