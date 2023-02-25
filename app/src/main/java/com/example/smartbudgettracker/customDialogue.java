package com.example.smartbudgettracker;

import android.app.Dialog;
import android.content.Context;

import com.example.smartbudgettracker.R;

public class customDialogue extends Dialog {
    public customDialogue(Context context)
    {
        super(context, R.style.CustomDialogTheme);
        setContentView(R.layout.popup);
    }
}
