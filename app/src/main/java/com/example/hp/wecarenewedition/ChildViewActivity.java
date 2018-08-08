package com.example.hp.wecarenewedition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.ikurenewedition.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

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
        ArrayList<String>sugarFastingChart = new ArrayList<String>();
        //setFastingData();
        mPpBarChat = (HorizontalBarChart)findViewById(R.id.sugar_pp);
        //setPpData();
        mRandomBarChat = (HorizontalBarChart)findViewById(R.id.sugar_random);
        //setRandomData();
        mSystolicBarChat = (HorizontalBarChart)findViewById(R.id.pressure_systolic);
        //setSystolicData();
        mDiastolicBarChart = (HorizontalBarChart)findViewById(R.id.pressure_diastolic);
        //setDiastolicData();

    }
    /*private void setFastingData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<String> sugarFasting = new ArrayList<String>();
        sugarFasting.add("100");
        BarDataSet barDataSet = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(sugarFasting,);


    }
    private void setPpData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            float val = (float) (Math.random() * 70);
            sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        }
        BarDataSet set2;
        set2 = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(set2);
        data.setBarWidth(barWidth);
        mPpBarChat.setData(data);
    }
    private void setRandomData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            float val = (float) (Math.random() * 40);
            sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        }
        BarDataSet set3;
        set3 = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(set3);
        data.setBarWidth(barWidth);
        mRandomBarChat.setData(data);
    }
    private void setSystolicData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            float val = (float) (Math.random() * 60);
            sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        }
        BarDataSet set4;
        set4 = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(set4);
        data.setBarWidth(barWidth);
        mSystolicBarChat.setData(data);
    }
    private void setDiastolicData() {
        float barWidth = 9f;
        float spaceforBar = 10f;
        ArrayList<BarEntry> sugarFasting = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            float val = (float) (Math.random() * 75);
            sugarFasting.add(new BarEntry(i*spaceforBar, (int) val));
        }
        BarDataSet set5;
        set5 = new BarDataSet(sugarFasting,"mm/dl");
        BarData data = new BarData(set5);
        data.setBarWidth(barWidth);

        mDiastolicBarChart.setData(data);

    }*/

}
