package com.macaraeg_jasper.backrooms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

public class Jumpscare extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpscare);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Jumpscare.this,GameOver.class));
                finish();
            }
        }, 2000);
    }
}
