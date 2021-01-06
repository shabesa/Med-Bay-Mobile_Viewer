package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigureIP extends AppCompatActivity {

    public static String IP = "";
    TextView Desc;
    EditText IPinput;
    Button Submit1;
    TextView Status;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_i_p);

        Desc = (TextView) findViewById(R.id.textView4);
        IPinput = (EditText) findViewById(R.id.FirstIP);
        Submit1 = (Button) findViewById(R.id.SubmitButtonMain);
        Status = (TextView) findViewById(R.id.StatMessage);

        sharedPreferences = getSharedPreferences(IP, MODE_PRIVATE);

        Submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(IP, IPinput.getText().toString());
                editor.apply();
                Desc.setVisibility(View.INVISIBLE);
                IPinput.setVisibility(View.INVISIBLE);
                Submit1.setVisibility(View.INVISIBLE);
                Status.setVisibility(View.VISIBLE);
                Status.setText("IP has been set");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(ConfigureIP.this, WebPage.class));


            }
        });

    }
}