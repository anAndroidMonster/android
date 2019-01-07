package com.example.android.function.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.R;

public class PermissionActivity extends AppCompatActivity {


    public static void enterActivity(Context context){
        Intent intent = new Intent(context, PermissionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
    }

    private void initView(){

    }

    private void initEvent(){

    }

    private void initData(){
        if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PermissionActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("8899", "权限" + permissions[i] + "申请成功");
                } else {
                    Log.d("8899", "权限" + permissions[i] + "申请失败");
                    boolean show = ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,
                            permissions[i]);
                    if(show){
                        ActivityCompat.requestPermissions(PermissionActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }else{
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", PermissionActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                }
            }
        }
    }
}
