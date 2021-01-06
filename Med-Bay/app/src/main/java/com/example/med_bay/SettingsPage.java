package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SettingsPage extends AppCompatActivity {

    Button ChangeUrl;
    Button About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        ChangeUrl = (Button) findViewById(R.id.ChangeUrlButton);
        About = (Button) findViewById(R.id.AboutButton);

        ChangeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsPage.this, UrlLoginPage.class));
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsPage.this, AboutPage.class));
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}