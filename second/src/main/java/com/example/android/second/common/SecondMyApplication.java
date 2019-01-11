package com.example.android.second.common;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.android.lib.common.BaseApplication;
import com.example.android.lib.common.IApplicationInit;
import com.example.android.second.BuildConfig;

public class SecondMyApplication extends BaseApplication implements IApplicationInit {
    private final String Tag = "myApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    @Override
    public void initModule() {
        Log.d(Tag, "初始化secondApplication");
    }

    @Override
    public void initApp() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
