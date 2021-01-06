package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebPage extends AppCompatActivity {

    public static String IP = "";
    int Back = 0;
    Button MoreButton;
    ProgressBar AppBar;
    WebView AppPage;
    Button RefreshButton;
    Button SettingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        AppPage = (WebView) findViewById(R.id.AppWebView);
        AppBar = (ProgressBar) findViewById(R.id.AppProgressBar);
        MoreButton = (Button) findViewById(R.id.MoreButton);
        RefreshButton = (Button) findViewById(R.id.RefreshButton);
        SettingsButton = (Button) findViewById(R.id.SettingsButtton);

        SharedPreferences sharedPreferences = getSharedPreferences("IP_address", MODE_PRIVATE);
        IP = sharedPreferences.getString("IP","http://0.0.0.0:8000/");

        if (savedInstanceState != null) {
            AppPage.restoreState(savedInstanceState);
        } else {
            AppPage.getSettings().setJavaScriptEnabled(true);
            AppPage.getSettings().setUseWideViewPort(true);
            AppPage.getSettings().setLoadWithOverviewMode(true);
            AppPage.getSettings().setSupportZoom(true);
            AppPage.getSettings().setBuiltInZoomControls(true);
            AppPage.getSettings().setSupportMultipleWindows(true);
            AppPage.getSettings().setDisplayZoomControls(false);
            AppPage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            AppPage.setBackgroundColor(Color.WHITE);
            AppPage.loadUrl(IP);

            AppPage.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    AppBar.setProgress(newProgress);
                    if (newProgress < 100 && AppBar.getVisibility() == ProgressBar.GONE) {
                        AppBar.setVisibility(ProgressBar.VISIBLE);
                    }
                    if (newProgress == 100){
                        AppBar.setVisibility(ProgressBar.GONE);
                    }else{
                        AppBar.setVisibility(ProgressBar.VISIBLE);
                    }
                }
            });
        }

        AppPage.setWebViewClient(new WebPage.MyWebViewClient());

        MoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RefreshButton.getVisibility() == View.INVISIBLE && SettingsButton.getVisibility() == View.INVISIBLE){
                    RefreshButton.setVisibility(View.VISIBLE);
                    SettingsButton.setVisibility(View.VISIBLE);
                }else{
                    RefreshButton.setVisibility(View.INVISIBLE);
                    SettingsButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPage.reload();
                RefreshButton.setVisibility(View.INVISIBLE);
                SettingsButton.setVisibility(View.INVISIBLE);
            }
        });

        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshButton.setVisibility(View.INVISIBLE);
                SettingsButton.setVisibility(View.INVISIBLE);
                startActivity(new Intent(WebPage.this, SettingsPage.class));
            }
        });

    }
    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }
    }

    @Override
    public void onBackPressed(){
        Back++;
        if (Back == 1){
            Toast.makeText(WebPage.this, "Press back again to quit", Toast.LENGTH_SHORT).show();
        }else if (Back == 2){
            super.onBackPressed();
            return;
        }
    }

}