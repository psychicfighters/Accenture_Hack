package com.example.hp.ikurenewedition.pojodatamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EcgUpload {

    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
