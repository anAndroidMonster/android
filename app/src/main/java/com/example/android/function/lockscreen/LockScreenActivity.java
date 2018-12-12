package com.example.android.function.lockscreen;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.R;
import com.example.android.utils.CmdHelper;

public class LockScreenActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private DevicePolicyManager dpm;
    private ComponentName componentName;

    public static void enterActivity(Context context){
        Intent intent = new Intent(context, LockScreenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setKeyguard(false);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lock();
            }
        }, 1000*5);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                unLock();
            }
        }, 1000*10);
    }

    private void lock(){
        if(CmdHelper.checkRoot()) {
            CmdHelper.execRootCmd("input keyevent 223");
            return;
        }
        dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(getApplicationContext(), AdminReceiver.class);
        if (dpm.isAdminActive(componentName)) {
            dpm.lockNow();// 直接锁屏
        }else{
            activeManager();//激活设备管理器获取权限
        }
    }

    private void activeManager() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivityForResult(intent, 0);
    }

    private void unLock(){
        if(CmdHelper.checkRoot()) {
            CmdHelper.execRootCmd("input keyevent 224");
            return;
        }

        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        final PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        mWakeLock.acquire(1000);
    }

    private void setKeyguard(boolean enable){
        KeyguardManager km= (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

        KeyguardManager.KeyguardLock lock = km.newKeyguardLock("unLock");
        if(enable){
            lock.reenableKeyguard();
        }else {
            lock.disableKeyguard();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                dpm.lockNow();// 直接锁屏
            }
        }
    }
}
