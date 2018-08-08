package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.ikurenewedition.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChildViewActivity extends AppCompatActivity {
    HorizontalBarChart mFastingBarChat;
    HorizontalBarChart mPpBarChat;
    HorizontalBarChart mRandomBarChat;
    HorizontalBarChart mSystolicBarChat;
    HorizontalBarChart mDiastolicBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view);


        mFastingBarChat = (HorizontalBarChart)findViewById(R.id.sugar_fasting);
        setFastingData(1,70);
        mPpBarChat = (HorizontalBarChart)findViewById(R.id.sugar_pp);
        setPpData(1,10);
        mRandomBarChat = (HorizontalBarChart)findViewById(R.id.sugar_random);
        setRandomData(1,45);
        mSystolicBarChat = (HorizontalBarChart)findViewById(R.id.pressure_systolic);
        setSystolicData(1,55);
        mDiastolicBarChart = (HorizontalBarChart)findViewById(R.id.pressure_diastolic);
        setDiastlicData(1,85);

    }
    /*private void setFastingData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<String> sugarFasting = new ArrayList<String>();
        sugarFasting.add("100");
        BarDataSet barDataSet = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(sugarFasting,);


    }*/
    private void setPpData(int count,int range) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarpp = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            float val1 = (float) (Math.random() * 70);
            sugarpp.add(new BarEntry(i*spaceforBar, (int) val1));
        }
        ArrayList<String> labels = new ArrayList<>();
        labels.add("100");
        BarDataSet set21;
        set21 = new BarDataSet(sugarpp,"mm/dl");
        BarData data1 = new BarData(labels,set21);
        //data.setBarWidth(barWidth);
        mPpBarChat.setData(data1);

    }
    private void setFastingData(int count,int range) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            float val = (float) (Math.random() * 70);
            sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        }
        ArrayList<String> labels = new ArrayList<>();
        labels.add("100");
        BarDataSet set2;
        set2 = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(labels,set2);
        //data.setBarWidth(barWidth);
        mFastingBarChat.setData(data);

    }
    private void setRandomData(int count,int range) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarRandom = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            float val = (float) (Math.random() * 70);
            sugarRandom.add(new BarEntry(i*spaceforBar, (int) val));
        }
        ArrayList<String> labels1 = new ArrayList<>();
        labels1.add("100");
        BarDataSet set2;
        set2 = new BarDataSet(sugarRandom,"mm/dl");
        BarData data = new BarData(labels1,set2);
        //data.setBarWidth(barWidth);
        mRandomBarChat.setData(data);

    }
    private void setSystolicData(int count,int range) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarSystolic = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            float val = (float) (Math.random() * 70);
            sugarSystolic.add(new BarEntry(i*spaceforBar, (int) val));
        }
        ArrayList<String> labels = new ArrayList<>();
        labels.add("100");
        BarDataSet set3;
        set3 = new BarDataSet(sugarSystolic,"mm/dl");
        BarData data = new BarData(labels,set3);
        //data.setBarWidth(barWidth);
        mSystolicBarChat.setData(data);

    }
    private void setDiastlicData(int count,int range) {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarDiastolic = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            float val3 = (float) (Math.random() * 70);
            sugarDiastolic.add(new BarEntry(i*spaceforBar, (int) val3));
        }
        ArrayList<String> labels = new ArrayList<>();
        labels.add("100");
        BarDataSet set2;
        set2 = new BarDataSet(sugarDiastolic,"mm/dl");
        BarData data = new BarData(labels,set2);
        //data.setBarWidth(barWidth);
        mDiastolicBarChart.setData(data);

    }


}
