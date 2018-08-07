package com.example.hp.wecarenewedition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ikurenewedition.NetworkActivity;
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

        btnSignUp = findViewById(R.id.btn_link_Enter2);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token = signupInputToken.getText().toString();
                if(token == null)
                    Toast.makeText(Volunteer_login.this, "Enter the unique id",
                            Toast.LENGTH_LONG).show();
                else{
                    Intent intent = new Intent(Volunteer_login.this, NetworkActivity.class);
                    intent.putExtra("from", "vol");
                    intent.putExtra("Patient_no", token);
                    startActivity(intent);

                }
            }
        });

    }
}
