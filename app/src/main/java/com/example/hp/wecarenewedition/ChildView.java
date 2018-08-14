package com.example.hp.wecarenewedition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hp.ikurenewedition.MainActivity;
import com.example.hp.ikurenewedition.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class ChildView extends AppCompatActivity implements BarGraph.BottomSheetListener {
    private String pid;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<ChildViewData> childViewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view2);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ChildViewData> childViewData = new ArrayList<>();
        childViewData.add(new ChildViewData(R.drawable.checkup1,R.string.checkup_req,R.string.time,R.string.last_checkup,R.string.time,R.string.service_req,
                R.string.remarks,R.string.good,R.string.predicted_random_value,R.string.time,R.string.random_hint,R.string.time));
        childViewData.add(new ChildViewData(R.drawable.sugar5,R.string.checkup_req,R.string.time,R.string.last_checkup,R.string.time,R.string.service_req,
                R.string.remarks,R.string.good,R.string.predicted_random_value,R.string.time,R.string.random_hint,R.string.time));
        childViewData.add(new ChildViewData(R.drawable.pressure1,R.string.checkup_req,R.string.time,R.string.last_checkup,R.string.time,R.string.service_req,
                R.string.remarks,R.string.good,R.string.predicted_random_value,R.string.time,R.string.random_hint,R.string.time));

        ChildViewAdapter adapter = new ChildViewAdapter(this, childViewData);
        recyclerView.setAdapter(adapter);

        Button btnChildView = findViewById(R.id.sugar_level);
        btnChildView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildView.this,PredictedSugar.class);
                startActivity(intent);
            }
        });
        Button btnBarGraph = findViewById(R.id.popup);
        btnBarGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarGraph barGraph = new BarGraph();
                barGraph.show(getSupportFragmentManager(),"BarGraph");

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.recyclerView) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onOptionClick() {
        recyclerView.setAdapter(adapter);

    }
}
