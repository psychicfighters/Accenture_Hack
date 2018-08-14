package com.example.hp.ikurenewedition.pojodatamodels;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProbSugar {

    @SerializedName("probability")
    @Expose
    private String probab;


    public String getSugar() {



        return probab;

    }

}
