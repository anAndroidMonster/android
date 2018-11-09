package com.example.android.function.constraintlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.R;

public class ConstraintLayoutActivity extends AppCompatActivity {

    public static void enterActivity(Context context){
        Intent intent = new Intent(context, ConstraintLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }
}
