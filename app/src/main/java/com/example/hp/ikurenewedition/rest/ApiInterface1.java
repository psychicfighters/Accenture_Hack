package com.example.hp.ikurenewedition.rest;

import com.example.hp.ikurenewedition.pojodatamodels.BpUpload;
import com.example.hp.ikurenewedition.pojodatamodels.BpUploadResult;
import com.example.hp.ikurenewedition.pojodatamodels.ConfirmService;
import com.example.hp.ikurenewedition.pojodatamodels.DataUpload;
import com.example.hp.ikurenewedition.pojodatamodels.Register;
import com.example.hp.ikurenewedition.pojodatamodels.SendData;
import com.example.hp.ikurenewedition.pojodatamodels.SendRegister;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUpload;
import com.example.hp.ikurenewedition.pojodatamodels.SugarUploadResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Gyalpo on 1/5/2018.
 */

public interface ApiInterface1 {
    @Headers({"Content-Type: application/json"})
    @POST("register")
    Call<Register> regnew(@Body SendRegister data);

    @POST("bpupload")
    Call<BpUploadResult> bpupload(@Body BpUpload data);

    @POST("uploadsugar")
    Call<SugarUploadResult> sugarupload(@Body SugarUpload data);


    @POST("checkuprequest")
    Call<DataUpload> savePost(@Body SendData data);

    @POST("checkupdone")
    Call<DataUpload> savePost2(@Body ConfirmService data);

}
