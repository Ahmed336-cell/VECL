package com.vecl.vecl.retrofit.Car;

import com.vecl.vecl.model.NewCarModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarClient {
    private static final String BASE_URL = "https://private-anon-7b63fed4db-carsapi1.apiary-mock.com/";
    private CarInterface carInterface;
    private static CarClient INSTANCE;


    public  CarClient()  {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        carInterface = retrofit.create(CarInterface.class);
    }
    public static CarClient getInstance(){
            if (null  == INSTANCE){
                INSTANCE = new CarClient();
            }
            return INSTANCE;
    }

    public Call<List<NewCarModel>> getNewCar(){
        return carInterface.getNewCar();
    }

}
