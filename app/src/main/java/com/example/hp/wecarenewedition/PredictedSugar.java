package com.example.hp.wecarenewedition;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.ProbSugar;
import com.example.hp.ikurenewedition.pojodatamodels.SugarDetail;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PredictedSugar extends AppCompatActivity {
    EditText editText1, editText2, editText3, editText4;
    String sucrose;
    private String dias;
    private String bmi;
    private String age;
    private ProgressDialog progressDialog;
    private ImageView imageview;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predicted_sugar);
        editText1 = findViewById(R.id.sucrose_levelmg_dl);
        editText2 = findViewById(R.id.diastolic_level);
        editText3 = findViewById(R.id.bmi_level);
        editText4 = findViewById(R.id.age_level);
        imageview = findViewById(R.id.sugarLevelImage);
        hideSoftKeyboard();
        Button btnChildView = findViewById(R.id.checking_diabetic);
        btnChildView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucrose = editText1.getText().toString();
                dias = editText2.getText().toString();
                bmi = editText3.getText().toString();
                age = editText4.getText().toString();
                if(Objects.equals(sucrose, "") || Objects.equals(dias, "") ||
                        Objects.equals(bmi, "") || Objects.equals(age, "")){
                    Toast.makeText(PredictedSugar.this, "Please Enter all the Values", Toast.LENGTH_LONG).show();
                }
                else{
                    callAPI();
                }
            }
        });
    }
    private void hideSoftKeyboard(){
        if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText4.getWindowToken(), 0);
        }
    }
    private void callAPI() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
        progressDialog.setCancelable(false);
        progressDialog.show();
        imageview.setVisibility(View.INVISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProbSugar> call = apiService.getDetails13(sucrose, dias, bmi, age);
        call.enqueue(new Callback<ProbSugar>() {
            @Override
            public void onResponse(Call<ProbSugar> call, Response<ProbSugar> response) {
                progressDialog.dismiss();
                if(Integer.parseInt(response.body().getSugar()) == 1)
                    imageview.setImageResource(R.drawable.ifsugartrue);
                else
                    imageview.setImageResource(R.drawable.ifsugarfalse);
                imageview.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call<ProbSugar> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

}
