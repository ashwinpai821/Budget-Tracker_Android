package com.example.smartbudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
import android.widget.ArrayAdapter;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private AlertDialog.Builder dialogBuilder;
    private Dialog dialog;
    private Button I_E;
    static String amount = "0";

    TextView income,expense;
    private ConstraintLayout add_amount_layout,date_layout,selectcat_layout;
    private TextView add_amount_text,date_text,selectcat_text;
    Spinner selectcat_spinner;
    AppCompatButton income_expense_add_button;

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
         income_expense_add_button=Popup.findViewById(R.id.income_expense_add_button);
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
         Date date = new Date();
         dateFormat.format(date);
         date_text.setText(dateFormat.format(date));
         selectcat_layout = Popup.findViewById(R.id.selectcat);
         selectcat_spinner=(Spinner) Popup.findViewById(R.id.category_spinner);
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Popup.getContext(), R.layout.spinner_items, getResources().getStringArray(R.array.Categories));
         selectcat_spinner.setAdapter(arrayAdapter);
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
        income_expense_add_button.setOnClickListener(new View.OnClickListener() {
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
        Button add_10,add_15,add_20,add_50,add_100,add_500,add_1000,add_5000,add_10000;
        add_10=Popup.findViewById(R.id.add_10);
        add_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_10=(Button)view;
                String add_10_string = add_10.getText().toString();
                enter_amount.setText(add_10_string);
            }
        });
        add_15=Popup.findViewById(R.id.add_15);
        add_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_15=(Button)view;
                String add_15_string = add_15.getText().toString();
                enter_amount.setText(add_15_string);
            }
        });
        add_20=Popup.findViewById(R.id.add_20);
        add_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_20=(Button)view;
                String add_20_string = add_20.getText().toString();
                enter_amount.setText(add_20_string);
            }
        });
        add_50=Popup.findViewById(R.id.add_50);
        add_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_50=(Button)view;
                String add_50_string = add_50.getText().toString();
                enter_amount.setText(add_50_string);
            }
        });
        add_100=Popup.findViewById(R.id.add_100);
        add_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_100=(Button)view;
                String add_100_string = add_100.getText().toString();
                enter_amount.setText(add_100_string);
            }
        });
        add_500=Popup.findViewById(R.id.add_500);
        add_500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_500=(Button)view;
                String add_500_string = add_500.getText().toString();
                enter_amount.setText(add_500_string);
            }
        });
        add_1000=Popup.findViewById(R.id.add_1000);
        add_1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_1000=(Button)view;
                String add_1000_string = add_1000.getText().toString();
                enter_amount.setText(add_1000_string);
            }
        });
        add_5000=Popup.findViewById(R.id.add_5000);
        add_5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_5000=(Button)view;
                String add_5000_string = add_5000.getText().toString();
                enter_amount.setText(add_5000_string);
            }
        });
        add_10000=Popup.findViewById(R.id.add_10000);
        add_10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_10000=(Button)view;
                String add_10000_string = add_10000.getText().toString();
                enter_amount.setText(add_10000_string);
                //hi
            }
        });

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
                month++;
                String day="0",mon="0";
                if(day_of_month<10)
                    day+=day_of_month;
                else
                    day=Integer.toString(day_of_month);
                if(month<10)
                    mon+=Integer.toString(month);
                else
                    mon =Integer.toString(month);
                date_text.setText(day+"/"+mon+"/"+year+" ");
            }
        }, Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datepicker.setCancelable(true);
        datepicker.show();
        }

    // Add button/ data to database
    public void addToDatabase(String category, String date, String amount)
    {
        if(this.amount.equals("0"))
        {
            Toast.makeText(this, "Amount not Selected!", Toast.LENGTH_SHORT).show();
        }
    }
}
