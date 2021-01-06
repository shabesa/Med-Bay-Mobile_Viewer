package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeIPPage extends AppCompatActivity {

    String Check;
    String IP;
    EditText ChangeIP;
    Button Submit2;
    TextView CurrentIP;
    SharedPreferences sharedPreferences;
    String DataNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_i_p_page);

        ChangeIP = (EditText) findViewById(R.id.ChangeIP);
        Submit2 = (Button) findViewById(R.id.SubmitSec);
        CurrentIP = (TextView) findViewById(R.id.DisplayCurrentIP);
        sharedPreferences = getSharedPreferences("IP_address", MODE_PRIVATE);
        IP = sharedPreferences.getString("IP", "http://0.0.0.0:8000");

        CurrentIP.setText("Current:" + IP);
        Submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = ChangeIP.getText().toString();
                if (Check.equals("")) {
                    Toast.makeText(ChangeIPPage.this, "Please enter the IP", Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    DataNew = "http://" + ChangeIP.getText().toString() + ":8000/";
                    if (DataNew.equals(IP)){
                        Toast.makeText(ChangeIPPage.this, "Please enter new IP", Toast.LENGTH_LONG).show();
                    }else {
                        editor.putString("IP", DataNew);
                        editor.apply();
                        Intent intent = new Intent(ChangeIPPage.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this will always start your activity as a new task
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
}