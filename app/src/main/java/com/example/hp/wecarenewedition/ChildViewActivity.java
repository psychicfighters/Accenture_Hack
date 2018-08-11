package com.example.hp.wecarenewedition;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.dataclass.Data_class_four;
import com.example.hp.ikurenewedition.pojodatamodels.BPDetails;
import com.example.hp.ikurenewedition.pojodatamodels.CheckupDetails;
import com.example.hp.ikurenewedition.pojodatamodels.SugarDetail;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildViewActivity extends AppCompatActivity {
    HorizontalBarChart mSugarBarChat;
    HorizontalBarChart mPressureChat;

    private ProgressDialog progressDialog;
    private ArrayList<Data_class_four> dy;
    int k1, k2, k3 = 0;
    private ArrayList<String> diab_fasting = new ArrayList<>();
    private ArrayList<String> diab_fasting_date = new ArrayList<>();
    private ArrayList<String> diab_pp = new ArrayList<>();
    private ArrayList<String> diab_pp_date = new ArrayList<>();
    private ArrayList<String> diab_random = new ArrayList<>();
    private ArrayList<String> diab_random_date = new ArrayList<>();
    private String pid;

    private ArrayList<String> syslist = new ArrayList<>();
    private ArrayList<String> dialist = new ArrayList<>();
    private TextView textview;
    private ArrayList<Float> timelist = new ArrayList<Float>();
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");
        textview = findViewById(R.id.last_checkup);
        textView2 = findViewById(R.id.timetilllast_checkup);
        mSugarBarChat = (HorizontalBarChart)findViewById(R.id.sugar_barchart);
        mPressureChat = (HorizontalBarChart)findViewById(R.id.pressure_barchart);
        setSugarBarChart(0,250,70);
        setSugarBarChart(1,350,120);
        setSugarBarChart(2,250,110);
        setPressureBarChart(0,200,90);
        setPressureBarChart(1,200,110);

        //callAPI1();
        //callAPI2();
        callAPI3();
        //progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPressureChat=null;
        mSugarBarChat =null;
        progressDialog =null;
        dy =null;
        diab_fasting=null;
        diab_fasting_date =null;
        diab_pp = null;
        diab_pp_date =null;
        diab_random = null;
        diab_random_date = null;
        pid = null;
        syslist = null;
        dialist = null;
        textview = null;
        textView2 = null;
        timelist = null;
    }

    private void setSugarBarChart(int count, int range, float value){
        float spaceforBar = 10f;

        ArrayList<BarEntry> sugarBar = new ArrayList<>();
        for (int i = 0;i<=count;i++){
            float val = (float)(Math.random()*range);
            sugarBar.add(new BarEntry(i*spaceforBar, (int) val));
        }
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Fasting");
        labels.add("PP");
        labels.add("Random");
        BarDataSet set1;
        set1 = new BarDataSet(sugarBar,"mm/dl");
        if(value > 110)
            set1.setColor(Color.parseColor("#c62828"));
        else
            set1.setColor(Color.parseColor("#2e7d32"));
        BarData data = new BarData(labels,set1);
        //data.setBarWidth(barWidth);
        mSugarBarChat.setData(data);
        mSugarBarChat.animateY(600);
        mSugarBarChat.invalidate();
    }
    private void setPressureBarChart(int count,int range,float value){
        float spaceforBar = 10f;
        ArrayList<BarEntry> pressureBar = new ArrayList<>();
        for (int i =0; i<=count;i++){
            float val = (float)(Math.random()*range);
            pressureBar.add(new BarEntry(i*spaceforBar, (int) val));
        }
        ArrayList<String>labels = new ArrayList<>();
        labels.add("Systolic");
        labels.add("Diastolic");
        BarDataSet set2 = new BarDataSet(pressureBar,"mm/dl");
        BarData data = new BarData(labels,set2);
        //data.setBarWidth(barWidth);
        mPressureChat.setData(data);
        mPressureChat.animateY(1700);
        set2.setColor(Color.parseColor("#FFFFCC00"));
        mPressureChat.invalidate();

    }


    private void callAPI1() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SugarDetail> call = apiService.getDetails9(pid);
        call.enqueue(new Callback<SugarDetail>() {
            @Override
            public void onResponse(Call<SugarDetail> call, final Response<SugarDetail> result) {
                    progressDialog.dismiss();
                if (result.body().getError()) {
                    //bullshit();
                } else {
                    if (result.body().getSugarlist().size() == 0) {
                        //bullshit();
                    }
                    if (result != null) {
                        if (result.body().getSugarlist().size() > 0) {
                            for (int i = 0; i < result.body().getSugarlist().size(); i++) {
//                                dy.add(i, new Data_class_four(result.body().getSugarlist().get(i).getSugarFirst(),
//                                        result.body().getSugarlist().get(i).getSugarPp(),
//                                        result.body().getSugarlist().get(i).getSugarRandom(),
//                                        convert(Float.valueOf(result.body().getSugarlist().get(i).getTimestamp()))));

                                if (!Objects.equals(result.body().getSugarlist().get(i).getSugarFirst(), "NIL")) {
                                    diab_fasting.add(k1, result.body().getSugarlist().get(i).getSugarFirst());
                                    diab_fasting_date.add(k1, result.body().getSugarlist().get(i).getTimestamp());
                                    k1++;
                                }
                                if (!Objects.equals(result.body().getSugarlist().get(i).getSugarPp(), "NIL")) {
                                    diab_pp.add(k2, result.body().getSugarlist().get(i).getSugarPp());
                                    diab_pp_date.add(k2, result.body().getSugarlist().get(i).getTimestamp());
                                    k2++;
                                }
                                if (!Objects.equals(result.body().getSugarlist().get(i).getSugarRandom(), "NIL")) {
                                    diab_random.add(k3, result.body().getSugarlist().get(i).getSugarRandom());
                                    diab_random_date.add(k3, result.body().getSugarlist().get(i).getTimestamp());
                                    k3++;
                                }

                            }


                        }
                    }
                }
                if(diab_fasting.get(0) == null){
                    setSugarBarChart(0, 250, 0);
                }
                else{
                    setSugarBarChart(0, 250, Float.parseFloat(diab_fasting.get(0)));
                }

                if(diab_pp.get(0) == null){
                    setSugarBarChart(1, 350, 0);
                }
                else{
                    setSugarBarChart(1, 350, Float.parseFloat(diab_pp.get(0)));
                }

                if(diab_random.get(0) == null){
                    setSugarBarChart(2, 300, 0);
                }
                else{
                    setSugarBarChart(1, 300, Float.parseFloat(diab_random.get(0)));
                }
            }

            @Override
            public void onFailure(Call<SugarDetail> call, Throwable t) {
                    progressDialog.dismiss();

            }
        });
    }
    private void callAPI2(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BPDetails> call = apiService.getDetails10(pid);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        call.enqueue(new Callback<BPDetails>() {
            @Override
            public void onResponse(Call<BPDetails> call, Response<BPDetails> response) {
                progressDialog.dismiss();
                if (response.body().getError()) {
                    //bullshit();
                } else {
                    if (response.body().getBplist().size() == 0) {
                        //bullshit();
                    }
                    if (response != null) {
                        for (int i = 0; i < response.body().getBplist().size(); i++) {
                            syslist.add( response.body().getBplist().get(i).getSys());
                            dialist.add(response.body().getBplist().get(i).getDia());
                        }
                    }

                }
                if(syslist.get(0) == null){
                    setPressureBarChart(0, 200, 0);
                }
                else{
                    setPressureBarChart(0, 200, Float.parseFloat(syslist.get(0)));
                }

                if(dialist.get(0) == null){
                    setPressureBarChart(1, 200, 0);
                }
                else{
                    setPressureBarChart(1, 200, Float.parseFloat(dialist.get(0)));
                }
            }

            @Override
            public void onFailure(Call<BPDetails> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void callAPI3() {

            progressDialog.show();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CheckupDetails> call = apiService.getDetails11(pid);
            call.enqueue(new Callback<CheckupDetails>() {
            @Override
            public void onResponse(Call<CheckupDetails> call, final Response<CheckupDetails> result) {

                    progressDialog.dismiss();
                if(result.body().getError()){
                    //bullshit();
                } else {
                    if (result.body().getCheckupreqlist().size() == 0) {
                        //bullshit();
                    }
                    if (result != null) {
                        if (result.body().getCheckupreqlist().size() > 0) {
                            for (int i = 0; i < result.body().getCheckupreqlist().size(); i++) {
                                timelist.add(Float.parseFloat(result.body().getCheckupreqlist().get(i).getTimestamp()));

                            }
                        }
                    }
                }
                if(timelist.get(0) == null)
                    textview.setText("Yet To Take");
                else{
                    float val = Collections.max(timelist);
                    textview.setText(convert(val));
                    double time= System.currentTimeMillis();
                    if((time - val) > 0 )
                        textView2.setText(String.valueOf(((time - val) / (1000*60*60*24))));
                    else
                        textView2.setText("Scheduled Acurately");

                }

            }

                @Override
                public void onFailure(Call<CheckupDetails> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
    }

    private String convert(float time) {

        float tim = time;
        tim = tim;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        return formatter.format(tim);

        //return date;
    }
}