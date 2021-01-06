package com.example.med_bay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlLoginPage extends AppCompatActivity {

    EditText UsernameInput;
    EditText PasswordInput;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_login_page);

        UsernameInput = (EditText) findViewById(R.id.UsernameField);
        PasswordInput = (EditText) findViewById(R.id.PasswordField);
        Login = (Button) findViewById(R.id.LoginButton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UsernameInput.getText().toString().equals("Admin") && PasswordInput.getText().toString().equals("Admin!23")){
                    startActivity(new Intent(UrlLoginPage.this, ChangeIPPage.class));
                    finish();
                }else{
                    Toast.makeText(UrlLoginPage.this,"Invalid Password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}