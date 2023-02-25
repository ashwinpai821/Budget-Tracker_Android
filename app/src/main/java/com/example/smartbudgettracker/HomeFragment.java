package com.example.smartbudgettracker;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    LineChart lineChart;
    ArrayList<Entry> dataSet;
    CardView add_new_goal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dataSet = new ArrayList<Entry>();
        dataSet.add(new Entry(1,10000));
        dataSet.add(new Entry(2,9800));
        dataSet.add(new Entry(4,9500));
        dataSet.add(new Entry(5,9000));
        dataSet.add(new Entry(6,8700));
        dataSet.add(new Entry(8,8200));
        dataSet.add(new Entry(10,9000));
        dataSet.add(new Entry(12,7000));
        dataSet.add(new Entry(15,7500));
        dataSet.add(new Entry(17,7250));
        dataSet.add(new Entry(18,7200));
        dataSet.add(new Entry(20,7000));
        dataSet.add(new Entry(22,6000));
        dataSet.add(new Entry(25,5500));
        dataSet.add(new Entry(27,5250));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        lineChart=(LineChart)v.findViewById(R.id.linechart);
        LineDataSet lineDataSet = new LineDataSet(dataSet,"Balance");
        lineDataSet.setValueTextColor(R.color.black);
        lineDataSet.setCircleColor(R.color.black);
        lineDataSet.setFillColor(R.color.our_purple);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setColor(R.color.black,255);
        LineData lineData=new LineData(lineDataSet);
        lineData.setValueTextSize(14);
        lineChart.setData(lineData);
        lineChart.invalidate();
        add_new_goal = v.findViewById(R.id.add_new_goal);
        add_new_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(view);
            }
        });
        return v;

    }
    public void createDialog(View view){
        dialogBuilder = new AlertDialog.Builder(view.getContext());
        final View Popup=getLayoutInflater().inflate(R.layout.add_popup,null);
        dialogBuilder.setView(Popup);
        dialog=dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        String a="";
//         dialog.getButton(R.id.add_money_button).setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                dialog.
//             }
//         });

    }
}