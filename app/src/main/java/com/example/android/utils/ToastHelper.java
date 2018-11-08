package com.example.android.utils;

import android.widget.Toast;

import com.example.android.common.MyApplication;

public class ToastHelper {

    public static void toast(String msg){
        if(msg == null || msg.length() <= 0) return;
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}
