package com.example.hp.wecarenewedition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.BpUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.VitalUpload;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface1;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VitalReports extends AppCompatActivity {

    private EditText heightInput, weightInput,tempInput;
    private Button btnEnter3;
    private String pid;
    private String height;
    private String weight;
    private String temperature;
    private ProgressDialog progressDialog;
    static int count = 0 ;
    private VitalUpload sendData2;
    private VitalUpload sendData3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_reports);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");

        heightInput = (EditText) findViewById(R.id.height);
        weightInput = (EditText) findViewById(R.id.weight);
        tempInput = (EditText) findViewById(R.id.temp);

        btnEnter3 = (Button) findViewById(R.id.btnEnter3);
        progressDialog = new ProgressDialog(VitalReports.this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
        progressDialog.setCancelable(false);


        btnEnter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = heightInput.getText().toString();
                weight = weightInput.getText().toString();
                temperature = tempInput.getText().toString();

                if(Objects.equals(height, "") || Objects.equals(weight, "")
                        || Objects.equals(temperature, "")){
                    Toast.makeText(VitalReports.this, "Enter all the values", Toast.LENGTH_LONG).show();

                }
                else {

                    callAPI();
                    callAPI2();
                    callAPI3();
                }
            }
        });


    }

    private void callAPI() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        VitalUpload sendData1 = new VitalUpload();
        sendData1.setPid(pid);
        sendData1.setTimestamp(ts);
        sendData1.setType("HEIGHT");
        sendData1.setValue(height);

        sendData2 = new VitalUpload();
        sendData2.setPid(pid);
        sendData2.setTimestamp(ts);
        sendData2.setType("WEIGHT");
        sendData2.setValue(weight);

        sendData3 = new VitalUpload();
        sendData3.setPid(pid);
        sendData3.setTimestamp(ts);
        sendData3.setType("TEMPERATURE");
        sendData3.setValue(height);

        progressDialog.show();

        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        Call<SugarUploadResult> call = apiService.vitalupload(sendData1);
        call.enqueue(new Callback<SugarUploadResult>() {
            @Override
            public void onResponse(Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                count = count + 1;
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SugarUploadResult> call, Throwable t) {
                progressDialog.dismiss();
            }
        });








    }

    void callAPI2(){
        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        //Call<SugarUploadResult> call = apiService.vitalupload(sendData2);
        progressDialog.show();
        Call<SugarUploadResult> call = apiService.vitalupload(sendData2);
        call.enqueue(new Callback<SugarUploadResult>() {
            @Override
            public void onResponse(Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                count  = count + 1;
                progressDialog.dismiss();
//                if(!response.body().getError()) {
//                    //Toast.makeText(VitalReports.this, "Uploaded Successfully!", Toast.LENGTH_LONG).show();
//                    //finish();
//                }
                //callAPI3();
            }

            @Override
            public void onFailure(Call<SugarUploadResult> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }

    void callAPI3(){
        ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
        //Call<SugarUploadResult> call = apiService.vitalupload(sendData3);

        Call<SugarUploadResult> call = apiService.vitalupload(sendData3);
        call.enqueue(new Callback<SugarUploadResult>() {
            @Override
            public void onResponse(Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                count ++;

//                if(!response.body().getError()) {
//                    //Toast.makeText(VitalReports.this, "Uploaded Successfully!", Toast.LENGTH_LONG).show();
//                    //finish();
//
//                }

                    progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<SugarUploadResult> call, Throwable t) {

                    progressDialog.dismiss();

            }
        });

        if(count == 3){
            Toast.makeText(VitalReports.this, "Uploaded Successfully!", Toast.LENGTH_LONG).show();
            count = 0;
        }
        else{
            Toast.makeText(VitalReports.this, "Sorry Couldn't Upload", Toast.LENGTH_LONG).show();
             count = 0;
        }
    }
}
