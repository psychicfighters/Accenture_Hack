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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.MainActivity;
import com.example.hp.ikurenewedition.NetworkActivity;
import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.RequestService;
import com.example.hp.ikurenewedition.pojodatamodels.DataUpload;
import com.example.hp.ikurenewedition.pojodatamodels.Register;
import com.example.hp.ikurenewedition.pojodatamodels.SendData;
import com.example.hp.ikurenewedition.pojodatamodels.SendRegister;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentLogin extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private SharedPreferences savednotes;

    ProgressDialog progressDialog;

    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputAge;
    private Button btnSignUp;
    private Button btnLinkLogin;
    private RadioGroup genderRadioGroup;
    private String name;
    private String age;
    private String email;
    private String addr;
    private String gender;
    String token;
    private boolean status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_get = getIntent();
        int ext = intent_get.getIntExtra("val", 100);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
////        editor.putString("token", "PA7646");
////        editor.apply();
//        editor.clear();
//        editor.apply();
         token = pref.getString("token", null);

        setContentView(R.layout.activity_parent_login);
        Intent i = getIntent();
        if(token == null){
            status = true;
            display();
        }
        else if(token != null && ext == 100){
            Intent intent = new Intent(ParentLogin.this,NetworkActivity.class);
            intent.putExtra("Patient_no", token);
            startActivity(intent);
        }

        if(ext == 0){
            Toast.makeText(ParentLogin.this, "here", Toast.LENGTH_LONG).show();

            status = false;
            display();
            intent_get = null;

        }

    }

    private void display() {



        //Skip button to be removed

//        Button btnParentLoginToHome = findViewById(R.id.btn_skip);
//        btnParentLoginToHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ParentLogin.this,AfterSplash.class);
//                startActivity(intent);
//            }
//        });

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        signupInputName = (EditText) findViewById(R.id.signup_input_name);


//        savednotes = getSharedPreferences("notes",MODE_PRIVATE);
//        signupInputName.setText(savednotes.getString("tag", null));

        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_address);
        signupInputAge = (EditText) findViewById(R.id.signup_input_age);

        btnSignUp = (Button) findViewById(R.id.btn_signup);

        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = signupInputName.getText().toString();
                age = signupInputAge.getText().toString();
                email = signupInputEmail.getText().toString();
                addr = signupInputPassword.getText().toString();
                int id = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                gender = radioButton.getText().toString();
                callAPI(name, addr, age, email, gender, token);
            }
        });
    }

    private void callAPI(String name, String addr, String age, String email, String gender, String token) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...."+'\n'+"We are figuring things out");
        progressDialog.setCancelable(false);
        //cardView1.setVisibility(View.GONE);
        progressDialog.show();
        SendRegister snd = new SendRegister();
        snd.setName(name);
        snd.setAddr(addr);
        snd.setAge(age);
        snd.setEmail(email);
        snd.setGender(gender);
        snd.setToken(token);

        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        Call<Register> call = apiService.regnew(snd);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
//                if(response.body().getError()){
//                    progressDialog.dismiss();
//                    Toast.makeText(RequestService.this,"Couldn't be uploaded Try again",Toast.LENGTH_LONG).show();
//                }

                    progressDialog.dismiss();
                    Toast.makeText(ParentLogin.this, "Uploaded successfully", Toast.LENGTH_LONG).show();
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("token", response.body().getResultToken());
                    editor.apply();
                    if(status) {
                        Intent i = new Intent(ParentLogin.this, NetworkActivity.class);
                        //finish();
                        String token = pref.getString("token", null);
//                Toast.makeText(ParentLogin.this, token, Toast.LENGTH_LONG).show();
                        i.putExtra("Patient_no", token);
                        startActivity(i);
                    }
                    else{
                        finish();
                    }


            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ParentLogin.this,"Couldn't register try again!",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void makeTag(String tag){
        String or = savednotes.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = savednotes.edit();
        preferencesEditor.putString("tag",tag); //change this line to this
        preferencesEditor.apply();
    }



}
