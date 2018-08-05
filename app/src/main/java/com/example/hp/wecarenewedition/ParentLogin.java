package com.example.hp.wecarenewedition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.MainActivity;
import com.example.hp.ikurenewedition.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParentLogin extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private SharedPreferences savednotes;

    ProgressDialog progressDialog;

    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputAge;
    private Button btnSignUp;
    private Button btnLinkLogin;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);


        //Skip button to be removed

        Button btnParentLoginToHome = findViewById(R.id.btn_skip);
        btnParentLoginToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentLogin.this,AfterSplash.class);
                startActivity(intent);
            }
        });

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        signupInputName = (EditText) findViewById(R.id.signup_input_name);


        savednotes = getSharedPreferences("notes",MODE_PRIVATE);
        signupInputName.setText(savednotes.getString("tag", null));

        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_password);
        signupInputAge = (EditText) findViewById(R.id.signup_input_age);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLinkLogin = (Button) findViewById(R.id.btn_link_login);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);





        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),ParentLogin.class);
                startActivity(i);
            }
        });
    }
    private void makeTag(String tag){
        String or = savednotes.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = savednotes.edit();
        preferencesEditor.putString("tag",tag); //change this line to this
        preferencesEditor.commit();
    }



}
