package com.example.hp.wecarenewedition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.NetworkActivity;
import com.example.hp.ikurenewedition.R;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.File;

public class VolunteerActivity extends AppCompatActivity {
    boolean status = false;
    private String pid;
    private File savefiledir;
    private Uri photoUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");

        //capture ecg
        Button buttonecgCaptur = findViewById(R.id.capture1);
        buttonecgCaptur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });


        Button buttonecg = findViewById(R.id.submit1);
        buttonecg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status) {

                } else {
                    Toast.makeText(VolunteerActivity.this, "Please Select A Photo First", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button buttonbp = findViewById(R.id.submit2);
        buttonbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerActivity.this, BloodPressure.class);
                intent.putExtra("patient", pid);
                startActivity(intent);

            }
        });
        Button buttonsugar = findViewById(R.id.submit3);
        buttonsugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerActivity.this, BloodSugar.class);
                intent.putExtra("patient", pid);
                startActivity(intent);

            }
        });
        Button buttonvital = findViewById(R.id.submit4);
        buttonvital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerActivity.this, VitalReports.class);
                intent.putExtra("patient", pid);
                startActivity(intent);

            }
        });

    }


    private void dispatchTakePictureIntent() {

        //imageView.setImageResource(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }
        }


    }


    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    Long yis = System.currentTimeMillis() / 1000;
                    savefiledir = new File(getExternalFilesDir(null) + File.separator + "PresandECGReports" + pid + yis.toString() + ".jpeg");
                    // String picname = getPicname();
                    //File imageFile = new File(savefiledir, picname);
                    photoUri = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".com.example.hp.wecarenewedition.provider", savefiledir);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                } else {

                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();

                }

            }

        }
    }
            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    status = true;
                    //


                   // status = false;  // to be done
                }
            }


        }

