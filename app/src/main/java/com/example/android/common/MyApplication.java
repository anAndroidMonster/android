package com.example.android.common;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.android.BuildConfig;
import com.example.android.lib.common.BaseApplication;
import com.example.android.lib.common.IApplicationInit;

public class MyApplication extends BaseApplication implements IApplicationInit {
    private final String Tag = "myApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
        initModule();
    }

    @Override
    public void initModule(){
        String[] modules = {"com.example.android.first.common.FirstMyApplication", "com.example.android.second.common.SecondMyApplication"};
        for(String module: modules) {
            try {
                Class clazz = Class.forName(module);
                IApplicationInit app = (IApplicationInit) clazz.newInstance();
                app.initModule();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initApp() {
        Log.d(Tag, "初始化myApplication");
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
