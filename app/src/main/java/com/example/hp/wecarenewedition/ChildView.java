package com.example.hp.wecarenewedition;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.hp.ikurenewedition.MainActivity;
import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.BPDetails;
import com.example.hp.ikurenewedition.pojodatamodels.CheckupDetails;
import com.example.hp.ikurenewedition.pojodatamodels.SugarDetail;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface;
import com.github.mikephil.charting.charts.BarChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildView extends AppCompatActivity implements BarGraph.BottomSheetListener {
    private String pid;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    private ProgressDialog progressDialog;
    private ArrayList<String> diab_fasting = new ArrayList<>();
    private ArrayList<String> diab_fasting_date = new ArrayList<>();
    private ArrayList<String> diab_pp = new ArrayList<>();
    private ArrayList<String> diab_pp_date = new ArrayList<>();
    private ArrayList<String> diab_random = new ArrayList<>();
    private ArrayList<String> diab_random_date = new ArrayList<>();
    private ArrayList<String> syslist = new ArrayList<>();
    private ArrayList<String> dialist = new ArrayList<>();

    private ArrayList<Float> timelist = new ArrayList<Float>();
    int k1, k2, k3 = 0;
    ArrayList<ChildViewData> childViewData = new ArrayList<>();
    RecyclerClickListener listener;
    BarGraph barGraph = new BarGraph();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view2);

        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        callAPI1();
        callAPI2();
        callAPI3();


        adapter = new ChildViewAdapter(this, childViewData, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(childViewData.get(position).mImageIdl == R.drawable.sugar5){
                    ((BarGraph)barGraph).set(diab_random,diab_random_date);
                    barGraph.show(getSupportFragmentManager(),"BarGraph");

                }
                else if(childViewData.get(position).mImageIdl == R.drawable.pressure1){
                    ((BarGraph)barGraph).set(dialist,syslist);
                    barGraph.show(getSupportFragmentManager(),"BarGraph");

                }


            }
        });
        recyclerView.setAdapter(adapter);

        Button btnChildView = findViewById(R.id.sugar_level);
        btnChildView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildView.this,PredictedSugar.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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


    private void callAPI1() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
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

                            } }
                            if(diab_fasting.get(0) == null)
                                diab_fasting.add("N.A");
                            if(diab_pp.get(0) == null)
                                diab_pp.add("N.A");
                            if(diab_random.get(0) == null)
                                diab_random.add("N.A");

                        childViewData.add(new ChildViewData(R.drawable.sugar5, "Fasting",
                                diab_fasting.get(0),"PP",diab_pp.get(0),"Last Blood Sugar Reports",
                                "Random",diab_random.get(0),"Predicted Random","167","*Unit(mg/dl)",""));
                        adapter.notifyDataSetChanged();
                    }
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
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
        progressDialog.setCancelable(false);
        progressDialog.show();
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

                        if(syslist.get(0) == null)
                            syslist.add("N.A");
                        if(dialist.get(0) == null)
                            dialist.add("N.A");

                        childViewData.add(new ChildViewData(R.drawable.pressure1,"Systolic",
                                syslist.get(0),"","","Last Blood Pressure Report",
                                "Diastolic",dialist.get(0),"","","*Unit(mm of Hg)",""));
                        adapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onFailure(Call<BPDetails> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void callAPI3() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...." + '\n' + "We are figuring things out");
        progressDialog.setCancelable(false);
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
                        String var, sch;
                        float val = Collections.max(timelist);
                        double time= System.currentTimeMillis();
                        if((time - val) > 0 ) {
                            var = String.valueOf(((time - val) / (1000 * 60 * 60 * 24)));
                            sch="Schedule a Checkup";
                        }
                        else {
                            var = "0";
                            sch = "Perfect";
                        }
                        childViewData.add(new ChildViewData(R.drawable.checkup1,"Last Service",convert(val),"","",
                                "Services Provided",
                                "Days from Last Checkup",var,"Remarks",sch,"",""));
                        adapter.notifyDataSetChanged();
                    }
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
