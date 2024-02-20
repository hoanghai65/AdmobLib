package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.callback.UMPResultListener;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;

public abstract class Observer {
    public Subject subject;
    public abstract void update(Activity activity, UMPResultListener umpResultListener);
}
