package com.example.hp.wecarenewedition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.BpUpload;
import com.example.hp.ikurenewedition.pojodatamodels.BpUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.Register;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface1;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodPressure extends AppCompatActivity {
    private EditText systolicInput, diastolicInput;
    private Button btnEnter;
    private String diastolic;
    private String systolic;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure2);

        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");
        systolicInput = (EditText) findViewById(R.id.systolic);
        diastolicInput = (EditText) findViewById(R.id.diastolic);

        btnEnter = (Button) findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                systolic = systolicInput.getText().toString();
                diastolic = diastolicInput.getText().toString();
                if(Objects.equals(systolic, "") || Objects.equals(diastolic, "")){
                    Toast.makeText(BloodPressure.this, "Enter both the values", Toast.LENGTH_LONG).show();

                }
                else {
                    callAPI();
                }
            }
        });
    }

    private void callAPI() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        BpUpload sendData = new BpUpload();
        sendData.setPid(pid);
        sendData.setTimestamp(ts);
        sendData.setDia(diastolic);
        sendData.setSys(systolic);

        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        Call<BpUploadResult> call = apiService.bpupload(sendData);

        call.enqueue(new Callback<BpUploadResult>() {
            @Override
            public void onResponse(Call<BpUploadResult> call, Response<BpUploadResult> response) {
                if(!response.body().getError())
                    Toast.makeText(BloodPressure.this, "Uploaded Successfully!", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<BpUploadResult> call, Throwable t) {
                Toast.makeText(BloodPressure.this, "Sorry Couldn't be Uploaded!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
