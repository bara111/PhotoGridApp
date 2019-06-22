package com.example.photogridapp.CatApi;

import com.google.gson.annotations.SerializedName;

public class CatResponse {
    @SerializedName("url")
private String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
