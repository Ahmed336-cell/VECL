package com.vecl.vecl.ui.fragment.keta3Car;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vecl.vecl.retrofit.keta3.Keta3Client;
import com.vecl.vecl.model.Keta3Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Keta3ViewModel extends ViewModel {
    MutableLiveData<List<Keta3Model>>keta3Mutaple = new MutableLiveData<>();
    MutableLiveData<String>keta3 = new MutableLiveData<>();

    public void getKat3(){
        Keta3Client.getInstance().getKat3().enqueue(new Callback<List<Keta3Model>>() {
            @Override
            public void onResponse(Call<List<Keta3Model>> call, Response<List<Keta3Model>> response) {
                keta3Mutaple.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Keta3Model>> call, Throwable t) {
                keta3.setValue(t.getMessage());
            }
        });
    }


}
