package com.vecl.vecl.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vecl.vecl.R;
import com.vecl.vecl.adapter.Home;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    List<String>title;
    List<Integer>image;
    Home homeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        rv= view.findViewById(R.id.homerv);
        title = new ArrayList<>();
        image = new ArrayList<>();

        title.add(getString(R.string.newcar));
        title.add(getString(R.string.usedcar));
        title.add(getString(R.string.spareparts));
        title.add(getString(R.string.wench));
        title.add(getString(R.string.fastrescue));

        image.add(R.drawable.newcars);
        image.add(R.drawable.usedcars);
        image.add(R.drawable.ghiar);
        image.add(R.drawable.wnch);
        image.add(R.drawable.fastrescue);

        homeAdapter = new Home(getActivity(),title,image);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(homeAdapter);

        return view;
    }
}