package com.example.hp.wecarenewedition;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hp.ikurenewedition.AfterSplash;
import com.example.hp.ikurenewedition.NetworkActivity;
import com.example.hp.ikurenewedition.R;
import com.example.hp.ikurenewedition.pojodatamodels.BpUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.EcgUpload;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUploadResult;
import com.example.hp.ikurenewedition.rest.ApiClient;
import com.example.hp.ikurenewedition.rest.ApiInterface1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//;

import java.io.File;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolunteerActivity extends AppCompatActivity {
    boolean status = false;
    private String pid;
    private File savefiledir;
    private Uri photoUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private View buttonecgCaptur;
    private StorageReference mStorageRef;
    private Uri downloadUrl;
    private RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Intent intent = getIntent();
        pid = intent.getStringExtra("patient");

        mStorageRef = FirebaseStorage.getInstance().getReference();

        radiogroup = findViewById(R.id.image_radio_group);
        //capture ecg
        buttonecgCaptur = findViewById(R.id.capture1);
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
                    uploadimage();

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
            } else {
                takeimage();
            }
        }
                else {
            takeimage();
        }


    }


    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

               takeimage();

                } else {

                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();

                }

            }

        }

            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    status = true;
                    Toast.makeText(VolunteerActivity.this, "Photo Saved", Toast.LENGTH_SHORT).show();
                    buttonecgCaptur.setVisibility(View.INVISIBLE);
                    //


                   // status = false;  // to be done
                }
            }
   public void takeimage(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                Long yis = System.currentTimeMillis() / 1000;
                savefiledir = new File(getExternalFilesDir(null) + File.separator + "PresandECGReports" + pid + yis.toString() + ".jpeg");
                // String picname = getPicname();
                //File imageFile = new File(savefiledir, picname);
                photoUri = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".com.example.hp.wecarenewedition.provider", savefiledir);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }



    public void uploadimage(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Uri file = Uri.fromFile(savefiledir);
       // Toast.makeText(VolunteerActivity.this, file.toString(), Toast.LENGTH_LONG).show();
        StorageReference riversRef = mStorageRef.child("images").child(ts);

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        progressDialog.dismiss();
                        downloadUrl = taskSnapshot.getDownloadUrl();
                        calltheapi(Objects.requireNonNull(downloadUrl).toString());
                        status = false;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        progressDialog.dismiss();
                        Toast.makeText(VolunteerActivity.this, "Failed To upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //displaying the upload progress
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
            }
        });

    }

    private void calltheapi(String s) {
        int selectedId = radiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String type = radioButton.getText().toString();
        if(Objects.equals(type, "Prescription")){
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            EcgUpload sendData = new EcgUpload();
            sendData.setPid(pid);
            sendData.setTimestamp(ts);
            sendData.setType("Prescription");
            sendData.setUrl(s);
            ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
            Call<SugarUploadResult> call = apiService.presupload(sendData);
            call.enqueue(new Callback<SugarUploadResult>() {
                @Override
                public void onResponse(Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                    Toast.makeText(VolunteerActivity.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                    buttonecgCaptur.setVisibility(View.VISIBLE);
                    savefiledir.delete();
                }

                @Override
                public void onFailure(Call<SugarUploadResult> call, Throwable t) {
                    Toast.makeText(VolunteerActivity.this, "Failed To upload Sorry!!", Toast.LENGTH_LONG).show();
                    buttonecgCaptur.setVisibility(View.VISIBLE);
                }
            });
        }
        else {

            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            EcgUpload sendData = new EcgUpload();
            sendData.setPid(pid);
            sendData.setTimestamp(ts);
            sendData.setType("ECG");
            sendData.setUrl(s);
            ApiInterface1 apiService = ApiClient.getClient().create(ApiInterface1.class);
            Call<SugarUploadResult> call = apiService.ecgupload(sendData);
            call.enqueue(new Callback<SugarUploadResult>() {
                @Override
                public void onResponse(Call<SugarUploadResult> call, Response<SugarUploadResult> response) {
                    Toast.makeText(VolunteerActivity.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                    buttonecgCaptur.setVisibility(View.VISIBLE);
                    savefiledir.delete();
                }

                @Override
                public void onFailure(Call<SugarUploadResult> call, Throwable t) {
                    Toast.makeText(VolunteerActivity.this, "Failed To upload", Toast.LENGTH_LONG).show();
                    buttonecgCaptur.setVisibility(View.VISIBLE);
                }
            });



        }
    }


}

