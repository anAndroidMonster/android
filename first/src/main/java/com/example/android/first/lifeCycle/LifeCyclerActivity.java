package com.example.android.first.lifeCycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.android.first.R;

@Route(path = "/first/LifeCyclerActivity")
public class LifeCyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_main);

        LifeCyclerActObserver observer = new LifeCyclerActObserver();
        getLifecycle().addObserver(observer);
    }
}
