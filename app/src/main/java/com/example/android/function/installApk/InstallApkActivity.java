package com.example.android.function.installApk;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.R;
import com.example.android.utils.ToastHelper;

import java.io.File;

public class InstallApkActivity extends AppCompatActivity {


    public static void enterActivity(Context context){
        Intent intent = new Intent(context, InstallApkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_apk);
        initView();
        initEvent();
        initData();
    }

    private void initView(){

    }

    private void initEvent(){
        findViewById(R.id.tv_install).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInstall();
            }
        });
    }

    private void initData(){

    }

    private void doInstall(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                doNext();
            }
        }else{
            doNext();
        }
    }

    private void doNext(){
        String sDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "Download/1.apk";
        install(InstallApkActivity.this, sDir, "com.example.android.provider");
    }

    private void install(Context mContext,String filePath,String authority){
        if(filePath == null || mContext == null)return;

        File apkFile = new File(filePath);
        if(!apkFile.exists()){
            ToastHelper.toast("安装包不存在");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(mContext,authority,apkFile);
        }else{
            uri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(uri,"application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}
