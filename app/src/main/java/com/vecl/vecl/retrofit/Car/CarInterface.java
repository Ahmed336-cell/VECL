package com.vecl.vecl.retrofit.Car;

import com.vecl.vecl.model.NewCarModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarInterface {
    //https://run.mocky.io/v3/f1b44084-2c8f-4bc3-b461-850a5dde8a6e
    @GET("manufacturers")
    public Call<List<NewCarModel>>getNewCar();
}
