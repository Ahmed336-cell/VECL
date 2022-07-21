package com.vecl.vecl.ui.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vecl.vecl.R;

public class StartFragment extends Fragment {

   AppCompatButton nextbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        nextbtn = view.findViewById(R.id.startbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.auth_container, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}