package com.macaraeg_jasper.backrooms;

import androidx.appcompat.app.AppCompatActivity;

//import android.media.MediaPlayer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    static MediaPlayer mediaPlayer;
    ImageButton on, off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on = findViewById(R.id.on_sound);
        off = findViewById(R.id.off_sound);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        MainActivity.mediaPlayer = MediaPlayer.create(this, R.raw.guiding_light);

        MainActivity.mediaPlayer.setLooping(true);
        MainActivity.mediaPlayer.start();
    }

    public void startGame(View view) {
        mediaPlayer.stop();
        GameView gameView = new GameView(this);
        GameView.mediaPlayer = MediaPlayer.create(this, R.raw.salad);
        GameView.mediaPlayer.setLooping(true);
        GameView.mediaPlayer.start();
        setContentView(gameView);
    }

    public void play(View v) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.guiding_light);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        off.setVisibility(View.GONE);
        on.setVisibility(View.VISIBLE);
        mediaPlayer.start();
    }

    public void pause(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            on.setVisibility(View.GONE);
            off.setVisibility(View.VISIBLE);
        }
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
//
//    public void jumpScare(View view) {
//        mediaPlayer.stop();
//        JumpScare jumpScareView = new JumpScare(this);
//        GameView.mediaPlayer = MediaPlayer.create(this, R.raw.music);
//        GameView.mediaPlayer.setLooping(true);
//        GameView.mediaPlayer.start();
//        setContentView(jumpScareView);
//    }
}