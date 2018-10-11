package com.example.hp.wecarenewedition;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.NetworkActivity;
import com.example.hp.ikurenewedition.R;

public class ChildLogin extends AppCompatActivity {
    private SharedPreferences savednotes;

    ProgressDialog progressDialog;
    private EditText signupInputName, signupInputToken;
    private Button btnEnter;
    private Button btnSkip;
    private String child_token;
    private String val;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        child_token = pref.getString("child_token", null);
        setContentView(R.layout.activity_child_login);


        if(child_token == null){
            display();
        }

        else{
            Intent i = new Intent(ChildLogin.this, NetworkActivity.class);
            i.putExtra("Patient_no", child_token);
            i.putExtra("from", "child");
            startActivity(i);
        }





    }
    void display(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        signupInputName = (EditText) findViewById(R.id.signup_input_token);


        savednotes = getSharedPreferences("notes",MODE_PRIVATE);
        signupInputName.setText(savednotes.getString("tag", null));

        //signupInputName = (EditText) findViewById(R.id.signup_input_email);




        btnEnter = (Button) findViewById(R.id.btn_link_Enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val = signupInputName.getText().toString();

                if (!val.equals("")) {


                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ChildLogin.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(ChildLogin.this);
                    }
                    builder.setTitle("Are you sure to add this ID?")
                            .setMessage("You cannot change this id once you enter!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("child_token", val);
                                    editor.apply();
                                    Intent i = new Intent(ChildLogin.this, NetworkActivity.class);
                                    i.putExtra("Patient_no", val);
                                    i.putExtra("from", "child");
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }
        });

    }

    private void save() {

    }
}
