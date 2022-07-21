package com.vecl.vecl.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vecl.vecl.R;


public class CarDetailesFragment extends Fragment {
    TextView nametxt;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_detailes, container, false);
        nametxt = view.findViewById(R.id.nameDetails);
        imageView = view.findViewById(R.id.imageDetails);


            String name = getArguments().getString("name");
            String imageUrl = getArguments().getString("img");
            nametxt.setText(name);
            Glide.with(getActivity()).load(imageUrl).into(imageView);
            Log.d("image",imageUrl);



        return view;
    }
}