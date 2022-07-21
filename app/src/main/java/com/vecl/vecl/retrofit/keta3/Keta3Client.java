package com.vecl.vecl.retrofit.keta3;

import com.vecl.vecl.model.Keta3Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//https://run.mocky.io/v3/1dd9d1f1-307e-4e3d-ac29-2d061ff2467c
public class Keta3Client {
    private static final String BASE_URL = "https://run.mocky.io/";
    private Keta3Interface keta3Interface;
    private static Keta3Client INSTANCE;


    public  Keta3Client()  {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        keta3Interface = retrofit.create(Keta3Interface.class);
    }
    public static Keta3Client getInstance(){
        if (null  == INSTANCE){
            INSTANCE = new Keta3Client();
        }
        return INSTANCE;
    }

    public Call<List<Keta3Model>> getKat3(){
        return keta3Interface.getKeta3();
    }
}
