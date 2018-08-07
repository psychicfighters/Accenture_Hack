package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.ikurenewedition.R;

public class BloodSugar extends AppCompatActivity {

    private EditText fastingInput, ppInput,randomInput;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        fastingInput = (EditText) findViewById(R.id.fasting);
        ppInput = (EditText) findViewById(R.id.pp);
        randomInput = (EditText) findViewById(R.id.random);

        btnEnter = (Button) findViewById(R.id.btnEnter1);
    }
}
