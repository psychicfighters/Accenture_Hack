package com.example.hp.wecarenewedition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.R;

public class ChildLogin extends AppCompatActivity {
    private SharedPreferences savednotes;

    ProgressDialog progressDialog;
    private EditText signupInputName, signupInputToken;
    private Button btnEnter;
    private Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        signupInputName = (EditText) findViewById(R.id.signup_input_name);


        savednotes = getSharedPreferences("notes",MODE_PRIVATE);
        signupInputName.setText(savednotes.getString("tag", null));

        signupInputName = (EditText) findViewById(R.id.signup_input_email);




        btnEnter = (Button) findViewById(R.id.btn_link_Enter);


    }
}
