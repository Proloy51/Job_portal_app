package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;
    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        lottie = findViewById(R.id.lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        }, 3000);

//        progressBar = findViewById(R.id.progressBarId);
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doWork();
//                startActivity(new Intent(SplashScreen.this,MainActivity.class));
//
//            }
//        });
//        thread.start();
//    }

//    private void doWork() {
//        for (progress = 20;progress<=100; progress = progress+20){
//            try {
//                Thread.sleep(1000);
//                progressBar.setProgress(progress);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
    }
}