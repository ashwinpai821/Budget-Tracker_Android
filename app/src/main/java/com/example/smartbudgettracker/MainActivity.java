package com.example.smartbudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartbudgettracker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private AlertDialog.Builder dialogBuilder;
    private Dialog dialog;
    private Button I_E;
    TextView income,expense;
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
        FloatingActionButton addB = findViewById(R.id.add);
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });




    }private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


    public void createDialog(){
        dialogBuilder = new AlertDialog.Builder(new customDialogue(this).getContext());
        final View Popup=getLayoutInflater().inflate(R.layout.popup,null);
         dialogBuilder.setView(Popup);
         dialog=dialogBuilder.create();
         dialog.show();
         income = Popup.findViewById(R.id.incometext);
         expense=Popup.findViewById(R.id.expensetext);
         income.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 income.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.our_green));
                 income.setText(R.string.IncomeUnderline);
                 expense.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                 expense.setText(R.string.Expense);
             }
         });
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expense.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.our_red));
                expense.setText(R.string.ExpneseUnderline);
                income.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                income.setText(R.string.Income);
            }
        });


    }
}