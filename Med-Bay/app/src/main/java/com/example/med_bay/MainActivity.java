package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;
    private Boolean firstTime = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (firstTime == null){

            SharedPreferences mPreferences = this.getSharedPreferences("first_time",MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);

            if (firstTime) {

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //This method is used so that your splash activity
                //can cover the entire screen.

                setContentView(R.layout.activity_main);
                //this will bind your MainActivity.class file with activity_main.

                SharedPreferences.Editor edit = mPreferences.edit();
                edit.putBoolean("firstTime", false);
                edit.apply();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, ConfigureIP.class));
                        //invoke the SecondActivity.

                        finish();
                        //the current activity will get finished.
                    }
                }, SPLASH_SCREEN_TIME_OUT);
            }else{
                startActivity(new Intent(MainActivity.this, WebPage.class));
                finish();
            }
        }
    }
}