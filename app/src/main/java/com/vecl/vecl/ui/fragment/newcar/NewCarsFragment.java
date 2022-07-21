package com.vecl.vecl.ui.fragment.newcar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vecl.vecl.R;
import com.vecl.vecl.adapter.NewCarAdapter;
import com.vecl.vecl.model.NewCarModel;

import java.util.ArrayList;
import java.util.List;


public class NewCarsFragment extends Fragment {
    NewCarViewModel newCarViewModel;
    RecyclerView cars;
    List<NewCarModel>carModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_cars, container, false);
        cars = view.findViewById(R.id.newcar_rv);
        newCarViewModel= ViewModelProviders.of(getActivity()).get(NewCarViewModel.class);
        newCarViewModel.getNewCar();
        final NewCarAdapter carAdapter = new NewCarAdapter(carModelList,getActivity());
        cars.setLayoutManager(new LinearLayoutManager(getActivity()));
        cars.setAdapter(carAdapter);
        newCarViewModel.carmodelMutable.observe(this, new Observer<List<NewCarModel>>() {
            @Override
            public void onChanged(List<NewCarModel> newCarModels) {
                carAdapter.setList(newCarModels);

            }

        });



        return view;
    }
}