package com.example.mvvmudemy01.model.part05;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoNoListForRestResponse {

    @SerializedName("RestResponse")
    @Expose
    private RestResponseNoList restResponse;

    public RestResponseNoList getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponseNoList restResponse) {
        this.restResponse = restResponse;
    }

}
