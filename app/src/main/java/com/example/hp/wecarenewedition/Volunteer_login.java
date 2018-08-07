package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.ikurenewedition.R;

public class Volunteer_login extends AppCompatActivity {
    private EditText signupInputToken;
    private Button btnSignUp;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);

        signupInputToken = (EditText) findViewById(R.id.signup_input_token2);
        token = signupInputToken.getText().toString();


    }
}
