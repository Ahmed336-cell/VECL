package com.vecl.vecl.ui.activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vecl.vecl.R;
import com.vecl.vecl.ui.fragment.StartFragment;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                .replace(R.id.auth_container, new StartFragment())
                .commit();
    }
}