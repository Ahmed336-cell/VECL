package com.vecl.vecl.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.vecl.vecl.R;


public class forgetPasswordFragment extends Fragment {
    EditText email;
    AppCompatButton send;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_forget_password, container, false);
        email = view.findViewById(R.id.email_edt_forget);
        send = view.findViewById(R.id.forgetbtn);
        mAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()){
                    email.setError(getString(R.string.ErrorEmailLogin));
                    email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError(getString(R.string.ErrorEmailLoginInvalid));
                    email.requestFocus();
                }else{
                    mAuth.sendPasswordResetEmail(email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), getString(R.string.reset), Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                                            .replace(R.id.auth_container, new LoginFragment())
                                            .addToBackStack(null)
                                            .commit();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return view;
    }
}