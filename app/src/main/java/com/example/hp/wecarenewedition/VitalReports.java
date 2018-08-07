package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.ikurenewedition.R;

public class VitalReports extends AppCompatActivity {

    private EditText heightInput, weightInput,tempInput;
    private Button btnEnter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_reports);

        heightInput = (EditText) findViewById(R.id.height);
        weightInput = (EditText) findViewById(R.id.weight);
        tempInput = (EditText) findViewById(R.id.temp);

        btnEnter3 = (Button) findViewById(R.id.btnEnter3);
    }
}
