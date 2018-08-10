package com.example.hp.wecarenewedition;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.dataclass.Data_class_four;
import com.example.hp.ikurenewedition.pojodatamodels.BPDetails;
import com.example.hp.ikurenewedition.pojodatamodels.SugarDetail;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildViewActivity extends AppCompatActivity {
    HorizontalBarChart mFastingBarChat;
    HorizontalBarChart mPpBarChat;
    HorizontalBarChart mRandomBarChat;
    HorizontalBarChart mSystolicBarChat;
    HorizontalBarChart mDiastolicBarChart;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");


        mFastingBarChat = (HorizontalBarChart)findViewById(R.id.sugar_fasting);

        mPpBarChat = (HorizontalBarChart)findViewById(R.id.sugar_pp);

        mRandomBarChat = (HorizontalBarChart)findViewById(R.id.sugar_random);

        mSystolicBarChat = (HorizontalBarChart)findViewById(R.id.pressure_systolic);

        mDiastolicBarChart = (HorizontalBarChart)findViewById(R.id.pressure_diastolic);

        callAPI1();
        callAPI2();
        progressDialog.dismiss();

    }
    /*private void setFastingData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<String> sugarFasting = new ArrayList<String>();
        sugarFasting.add("100");
        BarDataSet barDataSet = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(sugarFasting,);


    }*/

    private void setFastingData(int count,int range, int value) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        //for (int i = 0; i <count; i++) {
        //    float val = (float) (Math.random() * 50);
         //   sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        //}
        sugarFasting.add(new BarEntry(value, 0));
        ArrayList<String> labels = new ArrayList<>();
        labels.add(String.valueOf(value));
        BarDataSet set2;
        set2 = new BarDataSet(sugarFasting,"mm/dl");
        if(value > 110)
            set2.setColor(Color.parseColor("#c62828"));
        else
            set2.setColor(Color.parseColor("#2e7d32"));

        BarData data = new BarData(labels,set2);
        //data.setBarWidth(barWidth);
        mFastingBarChat.setData(data);
        mFastingBarChat.animateY(600);


    }

    private void setPpData(int count,int range, int value) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarpp = new ArrayList<>();
//        for (int i = 0; i <count; i++) {
//            float val1 = (float) (Math.random() * 70);
//            sugarpp.add(new BarEntry(i*spaceforBar, (int) val1));
//        }
        sugarpp.add(new BarEntry(value, 0));
        ArrayList<String> labels = new ArrayList<>();
        labels.add(String.valueOf(value));
        BarDataSet set21;
        set21 = new BarDataSet(sugarpp,"mm/dl");
        //set21.setBarSpacePercent(100f);
        if(value > 150)
            set21.setColor(Color.parseColor("#c62828"));
        else
            set21.setColor(Color.parseColor("#2e7d32"));
        BarData data1 = new BarData(labels,set21);
        //data.setBarWidth(barWidth);
        mPpBarChat.setData(data1);

        mPpBarChat.animateY(1000);


    }
    private void setRandomData(int count,int range, int value) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarRandom = new ArrayList<>();
        /*for (int i = 0; i <count; i++) {
            float val = (float) (Math.random() * 80);
            sugarRandom.add(new BarEntry(i*spaceforBar, (int) val));
        }*/
        sugarRandom.add(new BarEntry(value, 0));
        ArrayList<String> labels1 = new ArrayList<>();
        labels1.add(String.valueOf(value));
        BarDataSet set2;
        set2 = new BarDataSet(sugarRandom,"mm/dl");
        if(value > 135)
            set2.setColor(Color.parseColor("#c62828"));
        else
            set2.setColor(Color.parseColor("#2e7d32"));

        BarData data = new BarData(labels1,set2);
        //data.setBarWidth(barWidth);
        mRandomBarChat.setData(data);
        mRandomBarChat.animateY(1200);
        //set2.setColor(Color.parseColor("#FFFF05F7"));



    }
    private void setSystolicData(int count,int range, int value) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarSystolic = new ArrayList<>();
       /* for (int i = 0; i <count; i++) {
            float val = (float) (Math.random() * 40);
            sugarSystolic.add(new BarEntry(i*spaceforBar, (int) val));
        }*/
        sugarSystolic.add(new BarEntry(value, 0));
        ArrayList<String> labels = new ArrayList<>();
        labels.add(String.valueOf(value));
        BarDataSet set3;
        set3 = new BarDataSet(sugarSystolic,"mm/dl");
        BarData data = new BarData(labels,set3);
        //data.setBarWidth(barWidth);
        mSystolicBarChat.setData(data);
        mSystolicBarChat.animateY(1700);
        set3.setColor(Color.parseColor("#FFFFCC00"));
    }
    private void setDiastlicData(int count,int range, int value) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarDiastolic = new ArrayList<>();
        /*for (int i = 0; i <count; i++) {
            float val3 = (float) (Math.random() * 20);
            sugarDiastolic.add(new BarEntry(i*spaceforBar, (int) val3));
        }*/

        sugarDiastolic.add(new BarEntry(value, 0));
        ArrayList<String> labels = new ArrayList<>();
        labels.add(String.valueOf(value));
        BarDataSet set2;
        set2 = new BarDataSet(sugarDiastolic,"mm/dl");
        BarData data = new BarData(labels,set2);
        //data.setBarWidth(barWidth);
        mDiastolicBarChart.setData(data);
        mDiastolicBarChart.animateY(2000);
        set2.setColor(Color.parseColor("#FF34AE00"));
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
                    setFastingData(1, 250, 0);
                }
                else{
                    setFastingData(1, 250, Integer.parseInt(diab_fasting.get(0)));
                }

                if(diab_pp.get(0) == null){
                    setPpData(1, 350, 0);
                }
                else{
                    setPpData(1, 350, Integer.parseInt(diab_pp.get(0)));
                }

                if(diab_random.get(0) == null){
                    setRandomData(1, 300, 0);
                }
                else{
                    setRandomData(1, 300, Integer.parseInt(diab_random.get(0)));
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
                    setSystolicData(1, 200, 0);
                }
                else{
                    setSystolicData(1, 200, Integer.parseInt(syslist.get(0)));
                }

                if(dialist.get(0) == null){
                    setDiastlicData(1, 200, 0);
                }
                else{
                    setDiastlicData(1, 200, Integer.parseInt(dialist.get(0)));
                }
            }

            @Override
            public void onFailure(Call<BPDetails> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private String convert(Float time) {
        float tim = time;
        tim = tim * 1000;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("d MMM HH:mm");
        return formatter.format(tim);

        //return date;
    }
}