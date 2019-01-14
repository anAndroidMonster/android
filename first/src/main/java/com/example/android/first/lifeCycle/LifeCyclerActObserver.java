package com.example.android.first.lifeCycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class LifeCyclerActObserver implements LifecycleObserver {
    private final String Tag = "lifeObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        //todo 具体逻辑，不写activity中
        Log.d(Tag, "onDestroy");
    }
}
