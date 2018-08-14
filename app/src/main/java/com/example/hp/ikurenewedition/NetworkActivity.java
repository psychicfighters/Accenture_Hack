package com.example.hp.ikurenewedition;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hp.ikurenewedition.adapters.NotesAdapter;
import com.example.hp.ikurenewedition.dataclass.Data_class;
import com.example.hp.ikurenewedition.pojodatamodels.CardDetails;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface;
import com.example.hp.wecarenewedition.ChildView;
import com.example.hp.wecarenewedition.ChildViewActivity;
import com.example.hp.wecarenewedition.ParentLogin;
import com.example.hp.wecarenewedition.VolunteerActivity;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.root.ikure.pojo.earthquakeModel.Earthquake;
//import com.example.root.ikure.retrofit.RetrofitRepository;
//import com.example.root.ikure.retrofit.exception.BaseException;

/**
 * Created by Gyalpo on 1/3/2018.
 */

public class NetworkActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    // RetrofitRepository retrofitRepository;
    RelativeLayout relativeLayout;
    ListView NoteListView;
    ProgressDialog progressDialog;
    NotesAdapter notesAdapter;
    String patient;
    TextView card_no;
    int no_of_hits = 0;
    ArrayList<Data_class> dy = new ArrayList<Data_class>();
    private CardView mCardView;
    private boolean start;
    private String from = " ";
    private String m_Text;
    private String data;
    private String name;
    private String url;
    private String org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Intent i = getIntent();
        patient = i.getStringExtra("Patient_no");
        from  = i.getStringExtra("from");


        FloatingActionButton floatingActionButton;
        floatingActionButton = findViewById(R.id.floating1);
        if(Objects.equals(from, "child") || Objects.equals(from, "vol"))
            floatingActionButton.setVisibility(View.GONE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(NetworkActivity.this, ParentLogin.class);
                k.putExtra("val", 0);
                startActivity(k);
            }
        });
        card_no = (TextView) findViewById(R.id.serve1);
        card_no.setText(patient);
        //Toast.makeText(this, patient, Toast.LENGTH_LONG).show();
        //retrofitRepository = new RetrofitRepository();
        //callAPI();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout1);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorred,
                R.color.colorHeader3);

        init();
    }

    @Override
    public void onRefresh(){
        start = true;
        dy.clear();
        callAPI1();
    }

    private void init(){
        //retrofitRepository=new RetrofitRepository();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...."+'\n'+"We are figuring things out");
        progressDialog.setCancelable(false);
        callAPI1();
    }
    public void bullshit(){
//        Toast.makeText(NetworkActivity.this,"No Registered Patients found \nCheck Your Connection And Try again",Toast.LENGTH_LONG).show();
//        Intent i=new Intent(NetworkActivity.this,MainActivity.class);
        finish();
        //startActivity(i);
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        NoteListView= null;
        progressDialog = null;
        notesAdapter = null;
        patient = null;
        card_no = null;
        m_Text= null;
        data = null;
        name = null;
        url = null;
        org = null;
    }
    private void callAPI1(){
        if (!start)
            progressDialog.show();
        else
            swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CardDetails> call = apiService.getDetails(patient);
        call.enqueue(new Callback<CardDetails>() {
            @Override
            public void onResponse(Call<CardDetails> call, final Response<CardDetails> result) {
                if (!start)
                    progressDialog.dismiss();
                else
                    swipeRefreshLayout.setRefreshing(false);
                if (result.body().getError()) {
                    bullshit();
                }
                if (result.body().getPatientlist().size() == 0) {
                    //Toast.makeText(List_display.this,"Nothing to show",Toast.LENGTH_LONG).show();
                    //Intent i=new Intent(List_display.this,MainActivity.class);
                    //startActivity(i);
                    // return;
                    bullshit();

                } else {
                    if (result != null) {
                        if (result.body().getPatientlist().size() > 0) {
                            for (int i = 0; i < result.body().getPatientlist().size(); i++) {

                                data = result.body().getPatientlist().get(i).getPid();
                                if(from.equals("child") || from.equals("vol"))
                                    data = "---";
                                dy.add(i, new Data_class(data,
                                        result.body().getPatientlist().get(i).getName(),
                                        result.body().getPatientlist().get(i).getAge()));
                            }
                            notesAdapter = new NotesAdapter(getBaseContext(), dy);

                            NoteListView = (ListView) findViewById(R.id.listview);
                            try {
                                NoteListView.setAdapter(notesAdapter);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (result.body().getPatientlist().size() != 0) {
                        NoteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(List_display.this,"Hello",Toast.LENGTH_SHORT).show();
                                url = result.body().getPatientlist().get(position).getPid();
                                org = result.body().getPatientlist().get(position).getPid();
                                name = result.body().getPatientlist().get(position).getName();
                                Intent k = null;
                                if(from.equals("parent")) {
                                    k = new Intent(NetworkActivity.this, OnePerson.class);
                                    k.putExtra("patient", url);
                                    k.putExtra("card_no", patient);
                                    k.putExtra("patient_name", name);
                                    startActivity(k);
                                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                                }
                                //Toast.makeText(NetworkActivity.this, "here", Toast.LENGTH_LONG).show();
                                if(from.equals("child")) {


                                    AlertDialog.Builder builder = new AlertDialog.Builder(NetworkActivity.this);
                                    builder.setTitle("Enter PID");

// Set up the input
                                    final EditText input = new EditText(NetworkActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    builder.setView(input);

// Set up the buttons
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            m_Text = input.getText().toString();
                                            execute();
                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    builder.show();


                                    }
                                if(from.equals("vol")) {
                                    k = new Intent(NetworkActivity.this, VolunteerActivity.class);
                                    k.putExtra("patient", url);
                                    k.putExtra("card_no", patient);
                                    k.putExtra("patient_name", name);
                                    startActivity(k);
                                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                                }

                                //String str = Integer.toString(position);

                                // pass the intent here
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CardDetails> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                else
                    swipeRefreshLayout.setRefreshing(false);
                //Toast.makeText(NetworkActivity.this,"Network error",Toast.LENGTH_LONG).show();
                bullshit();
            }
        });
    }

void execute(){
    if(m_Text.equals(org)){

        Intent k;
        k = new Intent(NetworkActivity.this, ChildView.class);
        k.putExtra("patient", url);
        k.putExtra("card_no", patient);
        k.putExtra("patient_name", name);
        startActivity(k);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }
    else {

        Toast.makeText(NetworkActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
    }
}



//    @Override
//    public void onBackPressed() {
//        //finish();
//        if(no_of_hits == 0){
//            no_of_hits ++;
//            Toast.makeText(NetworkActivity.this,"Hit once again to Exit",Toast.LENGTH_SHORT).show();
//
//        }
//        else if(no_of_hits > 0) {
//            dy = null;
//            notesAdapter = null;
//            //startActivity(new Intent(this,MainActivity.class));
//            //finish();
//            super.onBackPressed();
//        }
//
//    }
    /*static  String getDateString(long millis){
        SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return format.format(millis);
    }*/


}