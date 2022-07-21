package com.vecl.vecl.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vecl.vecl.R;
import com.vecl.vecl.ui.activiy.MainActivity;


public class LoginFragment extends Fragment {
    EditText password,email;
    Button loginbtn;
    TextView signup,forget;
    FirebaseAuth mAuth;
    ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        password = view.findViewById(R.id.password_edt_login);
        email = view.findViewById(R.id.email_edt_login);
        signup = view.findViewById(R.id.signup);
        bar = view.findViewById(R.id.progresslogin);
        forget = view.findViewById(R.id.forgetpass);

        loginbtn = view.findViewById(R.id.loginbtn);
        mAuth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()){
                    email.setError(getString(R.string.ErrorEmailLogin));
                    email.requestFocus();
                }else if (password.getText().toString().isEmpty()){
                    password.setError(getString(R.string.ErrorPasswordLogin));
                    password.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError(getString(R.string.ErrorEmailLoginInvalid));
                    email.requestFocus();
                }else {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    bar.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        bar.setVisibility(View.GONE);
                                        Intent fo = new Intent(getActivity(), MainActivity.class);
                                        startActivity(fo);
                                        getActivity().overridePendingTransition(R.anim.enter_right,R.anim.exit_right);
                                        getActivity().finish();

                                    } else {
                                        Toast.makeText(getActivity(), getText(R.string.ErrorLogin), Toast.LENGTH_SHORT).show();
                                        bar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.auth_container, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });



        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.auth_container, new forgetPasswordFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}