package com.example.smartbudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.smartbudgettracker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        binding.bottomnav.setOnItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.bnav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.bnav_logs:
                    replaceFragment(new LogsFragment());
                    break;
                case R.id.bnav_settings:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.bnav_statistics:
                    replaceFragment(new StatisticsFragment());
                    break;
            }
            return true;
        });
        bottomNavigationView.setBackground(null);

    }private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}