package com.vecl.vecl.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vecl.vecl.R;
import com.vecl.vecl.ui.activiy.MainActivity;
import com.vecl.vecl.utilites.Constants;
import com.vecl.vecl.model.User;


public class SignUpFragment extends Fragment {
    EditText usernameedt,emailedt,phoneedt,passwordedt;

    String name,password,email,phone;
    AppCompatButton signupbtn;
    ProgressBar bar;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        usernameedt = view.findViewById(R.id.name_edt_signup);
        passwordedt = view.findViewById(R.id.password_edt_signup);
        emailedt = view.findViewById(R.id.email_edt_signup);
        phoneedt = view.findViewById(R.id.phone_edt_signup);
        signupbtn = view.findViewById(R.id.signupbtn);
        bar = view.findViewById(R.id.progresssignup);
        mAuth =FirebaseAuth.getInstance();




        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intilaiztion();
                if (name.isEmpty()) {
                    usernameedt.setError(getString(R.string.usernameempty));
                    usernameedt.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    emailedt.setError(getString(R.string.emailempty));
                    emailedt.requestFocus();
                    return;
                } if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailedt.setError(getString(R.string.ErrorEmailLoginInvalid));
                    emailedt.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordedt.setError(getString(R.string.passwordempty));
                    passwordedt.requestFocus();
                    return;
                } if (password.length()<6){
                    passwordedt.setError(getString(R.string.passwordlen));
                    passwordedt.requestFocus();
                    return;
                }  if (phone.isEmpty()){
                    phoneedt.setError(getString(R.string.phoneempty));
                    phoneedt.requestFocus();
                    return;
                } if (phone.length() != 11){
                    phoneedt.setError(getString(R.string.phoneInvalid));
                    phoneedt.requestFocus();
                    return;
                }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            bar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {

                                FirebaseUser firebaseUser = task.getResult().getUser();
                                User user = new User(name, phone, email, firebaseUser.getUid());
                                putDB(user);

                            }
                        }
                    });

                }

        });




        return view;
    }

    private void putDB(User user) {
        FirebaseFirestore.getInstance().collection(Constants.COLLECTION_NAME)
                .document(user.getId())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(), getString(R.string.signupSucc), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().overridePendingTransition(R.anim.enter_right,R.anim.exit_right);
                getActivity().finish();
                bar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void intilaiztion(){
        name = usernameedt.getText().toString().trim();
        password = passwordedt.toString();
        email = emailedt.getText().toString().trim();
        phone = phoneedt.getText().toString().trim();

    }

}