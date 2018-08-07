package com.example.hp.ikurenewedition.pojodatamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BpUpload {
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("sys")
    @Expose
    private String sys;
    @SerializedName("dia")
    @Expose
    private String dia;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

}
