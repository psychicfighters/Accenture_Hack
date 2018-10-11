package com.example.hp.wecarenewedition;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.ikurenewedition.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarGraph extends BottomSheetDialogFragment {
    private BottomSheetListener bottomSheetListener;
    private int type = 0;
    private ArrayList<String> diab_random = new ArrayList<>();
    private ArrayList<String> diab_random_date = new ArrayList<>();


    public BarGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_graph, container, false);
        BarChart mbarChart = view.findViewById(R.id.barchart);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i< diab_random.size(); i++){
            entries.add(new BarEntry(Float.parseFloat(diab_random.get(i)), i));
        }


        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        for(int i = 0; i< diab_random.size(); i++) {
           labels.add(diab_random_date.get(i));

        }

        BarData data = new BarData(labels, bardataset);
        mbarChart.setData(data); // set the data and list of lables into chart

        mbarChart.setDescription(null);
        // set the description
        mbarChart.getAxisRight().setDrawGridLines(false);
        mbarChart.getAxisLeft().setDrawGridLines(false);
        mbarChart.getXAxis().setDrawGridLines(false);
        mbarChart.getAxisLeft().setDrawLabels(false);
        mbarChart.getAxisRight().setDrawLabels(false);
        mbarChart.getXAxis().setDrawLabels(false);
        mbarChart.setDrawBorders(false);
        mbarChart.setDrawGridBackground(false);
        mbarChart.setTouchEnabled(false);
        mbarChart.setDragEnabled(false);
        mbarChart.setScaleEnabled(false);
        mbarChart.setPinchZoom(false);
        mbarChart.setAutoScaleMinMaxEnabled(true);
        Legend legend = mbarChart.getLegend();
        legend.setEnabled(false);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        mbarChart.animateY(700);
        return view;
    }
    public interface BottomSheetListener{
        void onOptionClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            bottomSheetListener = (BottomSheetListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.getPackageName());
        }
    }
    public void set(ArrayList<String> diab_random1, ArrayList<String> diab_random_date1){
        diab_random = diab_random1;
        diab_random_date = diab_random_date1;
    }
}
