package com.example.hp.wecarenewedition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.BpUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUpload;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUploadResult;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface1;

import java.util.Objects;

import retrofit2.Callback;
import retrofit2.Response;

public class BloodSugar extends AppCompatActivity {

    private EditText fastingInput, ppInput,randomInput;
    private Button btnEnter;
    private String pid;
    private String random;
    private String pp;
    private String fasting;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");


        fastingInput = findViewById(R.id.fasting);
        ppInput =  findViewById(R.id.pp);
        randomInput =findViewById(R.id.random);

        btnEnter = findViewById(R.id.btnEnter1);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fasting = fastingInput.getText().toString();
                pp = ppInput.getText().toString();
                random = randomInput.getText().toString();

                if(Objects.equals(fasting, "") && Objects.equals(pp, "")&& Objects.equals(random, "")){
                    Toast.makeText(BloodSugar.this, "Enter at least one value ...", Toast.LENGTH_LONG).show();

                }
                else {
                    if(Objects.equals(fasting, ""))
                        fasting = "NIL";
                    if(Objects.equals(pp, ""))
                        pp = "NIL";
                    if(Objects.equals(random, ""))
                        random = "NIL";
                    callAPI();
                }
            }
        }

    );
    }
    private void callAPI() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        SugarUpload sendData = new SugarUpload();
        sendData.setPid(pid);
        sendData.setTimestamp(ts);
        sendData.setSugarFirst(fasting);
        sendData.setSugarPp(pp);
        sendData.setSugarRandom(random);

        progressDialog = new ProgressDialog(BloodSugar.this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out this");
        progressDialog.setCancelable(false);


        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        retrofit2.Call<SugarUploadResult> call = apiService.sugarupload(sendData);
        call.enqueue(new Callback<SugarUploadResult>() {
            @Override
            public void onResponse(retrofit2.Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                progressDialog.dismiss();
                if(!response.body().getError())
                    Toast.makeText(BloodSugar.this, "Uploaded Successfully!", Toast.LENGTH_LONG).show();
                    finish();
            }

            @Override
            public void onFailure(retrofit2.Call<SugarUploadResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BloodSugar.this, "Sorry Couldn't be Uploaded!!!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
