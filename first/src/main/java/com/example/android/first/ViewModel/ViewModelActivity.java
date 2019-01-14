package com.example.android.first.ViewModel;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.android.first.LifeCycler.LifeCyclerActObserver;
import com.example.android.first.R;

@Route(path = "/first/ViewModelActivity")
public class ViewModelActivity extends AppCompatActivity {
    private TextView mTvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_viewmodel);

        mTvName = findViewById(R.id.first_tv_name);
        ViewModelProvider vmProvider = new ViewModelProvider(ViewModelActivity.this, new ViewModelProvider.NewInstanceFactory());
        final StudentModel student = vmProvider.get(StudentModel.class);
        Log.d("viewModel", "学生" + student.getName());
        mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName("李明");
                Log.d("viewModel", "学生" + student.getName());
            }
        });


        LifeCyclerActObserver observer = new LifeCyclerActObserver();
        getLifecycle().addObserver(observer);
    }
}
