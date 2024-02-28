package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;

public abstract class Observer {
    public Subject subject;
    public abstract void onListenerLoadedAds(Activity activity, ActionCallBack actionCallBack, TYPE type);
}
