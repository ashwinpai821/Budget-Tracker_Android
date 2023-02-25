package com.example.smartbudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbudgettracker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    private AlertDialog.Builder dialogBuilder;
    private Dialog dialog;
    private Button I_E;
    static String amount = "0";
    TextView income,expense;
    private ConstraintLayout add_amount_layout,date_layout,selectcat_layout;
    private TextView add_amount_text,date_text,selectcat_text;
    Spinner selectcat_spinner;

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

    // Main Popup Page
    public void createDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View Popup=getLayoutInflater().inflate(R.layout.popup,null);
         dialogBuilder.setView(Popup);
         dialog=dialogBuilder.create();
         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         dialog.show();
         income = Popup.findViewById(R.id.incometext);
         expense=Popup.findViewById(R.id.expensetext);
         add_amount_layout = Popup.findViewById(R.id.add_amount);
         add_amount_text = Popup.findViewById(R.id.add_amount_text);
         date_layout = Popup.findViewById(R.id.date_time);
         date_text=Popup.findViewById(R.id.date_time_text);
         selectcat_layout = Popup.findViewById(R.id.selectcat);
//         selectcat_text=Popup.findViewById(R.id.selectcategory);
         selectcat_spinner=Popup.findViewById(R.id.category_spinner);


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
        add_amount_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAmountDialog();
            }
        });

        date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDateDialog();
            }
        });

        selectcat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    // Amount add Popup page
    public void createAmountDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View Popup=getLayoutInflater().inflate(R.layout.add_popup,null);
        dialogBuilder.setView(Popup);
        dialog=dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        Button add_button = Popup.findViewById(R.id.add_money_button);
        EditText enter_amount = Popup.findViewById(R.id.enter_amount);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enter_amount.getText().toString().equals(""))
                    Toast.makeText(view.getContext(),"Amount not added!",Toast.LENGTH_SHORT).show();
                else{
                    amount = enter_amount.getText().toString();
                    add_amount_text.setText(amount);
                    dialog.dismiss();
                }
            }
        });
    }
    // Calendar Popup page
    public void createDateDialog() {
        DatePickerDialog datepicker = new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
                date_text.setText(day_of_month + "/" + month + "/" + year + " ");
            }
        }, Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datepicker.show();
        }
    }