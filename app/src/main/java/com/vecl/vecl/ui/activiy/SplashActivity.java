package com.vecl.vecl.ui.activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.vecl.vecl.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(getApplicationContext());
        new Handler().postDelayed(()->{
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.enter_right,R.anim.exit_right);
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, StartActivity.class));
                overridePendingTransition(R.anim.enter_right,R.anim.exit_right);
                finish();

            }



            finish();

        }, 3 * 1000);
    }
}