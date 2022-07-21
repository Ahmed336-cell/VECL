package com.vecl.vecl.retrofit.keta3;

import com.vecl.vecl.model.Keta3Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Keta3Interface {
    @GET("v3/23300cc9-ff58-49f6-864b-ba6ab19cbaa7")
    public Call<List<Keta3Model>>getKeta3();
}
