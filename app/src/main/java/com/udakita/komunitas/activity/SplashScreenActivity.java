package com.udakita.komunitas.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.SessionManager;

public class SplashScreenActivity extends SessionManager {

    RelativeLayout mylayout;
    AnimationDrawable animationDrawable;

    private final int SPLASH_DISPLAY_LENGTH = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mylayout = (RelativeLayout) findViewById(R.id.mylayout);


        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(3500);
        animationDrawable.setExitFadeDuration(3500);
        animationDrawable.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionManager.checkLogin();
                finish();
            }
        },10000);

    }
}
