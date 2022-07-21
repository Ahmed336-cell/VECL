package com.vecl.vecl.ui.activiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vecl.vecl.R;
import com.vecl.vecl.ui.fragment.ChatFragment;
import com.vecl.vecl.ui.fragment.HomeFragment;
import com.vecl.vecl.ui.fragment.SettingFragment;
import com.vecl.vecl.ui.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    VideoFragment videoFragment = new VideoFragment();
    SettingFragment settingFragment = new SettingFragment();
    ChatFragment chatFragment = new ChatFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.container, settingFragment).commit();
                return true;
            case R.id.video:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.container, videoFragment).commit();
                return true;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right,R.anim.exit_right,R.anim.enter_right,R.anim.exit_right)
                        .replace(R.id.container, chatFragment).commit();
                return true;
        }


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}