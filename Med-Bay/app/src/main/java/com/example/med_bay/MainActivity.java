package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private Boolean firstTime = null;

    ImageView MBLogo;
    ImageView Box;
    ImageView TeamLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        MBLogo = (ImageView) findViewById(R.id.MedBayLogoMain);
        Box = (ImageView) findViewById(R.id.Box);
        TeamLogo = (ImageView) findViewById(R.id.TeamLogoImage);

        if (firstTime == null){

            SharedPreferences mPreferences = this.getSharedPreferences("first_time",MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);

            if (firstTime) {
                MBLogo.animate().alpha(1).setDuration(2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MBLogo.animate().alpha(0).setDuration(2000);

                    }
                }, 2500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Box.animate().alpha(1).setDuration(2000);
                        TeamLogo.animate().alpha(1).setDuration(2000);
                    }
                }, 5000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Box.animate().alpha(0).setDuration(2000);
                        TeamLogo.animate().alpha(0).setDuration(2000);
                    }
                }, 7000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, ConfigureIP.class));
                        //invoke the SecondActivity.

                        finish();
                        //the current activity will get finished.
                    }
                }, 9000);
            }else{
                startActivity(new Intent(MainActivity.this, WebPage.class));
                finish();
                System.exit(0);
            }
        }
    }
}