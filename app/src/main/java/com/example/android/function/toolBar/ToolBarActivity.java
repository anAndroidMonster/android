package com.example.android.function.toolBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.R;
import com.example.android.utils.ToastHelper;

public class ToolBarActivity extends AppCompatActivity {

    public static void enterActivity(Context context){
        Intent intent = new Intent(context, ToolBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
    }

    private void initActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setSubtitle("子标题");

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.icon_return);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ToastHelper.toast(item.getTitle().toString());
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
