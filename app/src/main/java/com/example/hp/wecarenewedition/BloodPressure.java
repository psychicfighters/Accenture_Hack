package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.ikurenewedition.R;

public class BloodPressure extends AppCompatActivity {
    private EditText systolicInput, diastolicInput;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure2);


        systolicInput = (EditText) findViewById(R.id.systolic);
        diastolicInput = (EditText) findViewById(R.id.diastolic);

        btnEnter = (Button) findViewById(R.id.btnEnter);
    }
}
