package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigureIP extends AppCompatActivity {

    TextView Desc;
    EditText IPinput;
    Button Submit1;
    TextView Status;

    private Boolean firstTime;
    String Check;

    SharedPreferences sharedPreferences;
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_i_p);

        Desc = (TextView) findViewById(R.id.textView4);
        IPinput = (EditText) findViewById(R.id.FirstIP);
        Submit1 = (Button) findViewById(R.id.SubmitButtonMain);
        Status = (TextView) findViewById(R.id.StatMessage);


        sharedPreferences = getSharedPreferences("IP_address", MODE_PRIVATE);
        mPreferences = this.getSharedPreferences("first_time",MODE_PRIVATE);
        firstTime = mPreferences.getBoolean("firstTime", true);


        Submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = IPinput.getText().toString();
                if (Check.equals("")){
                    Toast.makeText(ConfigureIP.this, "Please enter IP to start app",
                            Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    SharedPreferences.Editor editor2 = mPreferences.edit();

                    String Data = "http://" + IPinput.getText().toString() + ":8000/";
                    editor1.putString("IP", Data);
                    editor1.commit();
                    editor2.putBoolean("firstTime", false);
                    editor2.commit();
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
                    finish();
                }
            }
        });
    }
}