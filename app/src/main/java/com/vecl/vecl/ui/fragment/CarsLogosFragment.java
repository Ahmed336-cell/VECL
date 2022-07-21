package com.vecl.vecl.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vecl.vecl.R;

public class CarsLogosFragment extends Fragment {
    RecyclerView cars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cars_logos, container, false);
        cars = view.findViewById(R.id.carlogosrv);


        return view;
    }

}