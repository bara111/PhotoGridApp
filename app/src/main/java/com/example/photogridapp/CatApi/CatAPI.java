package com.example.photogridapp.CatApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatAPI {
    @GET("/v1/images/search")
    Call<CatResponse> get();
}
