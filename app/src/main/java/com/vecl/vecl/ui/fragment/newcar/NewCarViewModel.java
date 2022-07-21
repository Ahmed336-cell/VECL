package com.vecl.vecl.ui.fragment.newcar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vecl.vecl.retrofit.Car.CarClient;
import com.vecl.vecl.model.NewCarModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCarViewModel extends ViewModel {
    MutableLiveData<List<NewCarModel>> carmodelMutable = new MutableLiveData<>();
    MutableLiveData<String>cars = new MutableLiveData<>();
    public void getNewCar(){
        CarClient.getInstance().getNewCar().enqueue(new Callback<List<NewCarModel>>() {
            @Override
            public void onResponse(Call<List<NewCarModel>> call, Response<List<NewCarModel>> response) {
                carmodelMutable.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NewCarModel>> call, Throwable t) {
                cars.setValue("error" + t.getMessage());
            }
        });
    }

}
