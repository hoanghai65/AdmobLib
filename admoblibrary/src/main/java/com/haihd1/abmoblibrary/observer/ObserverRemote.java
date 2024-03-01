package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.utils.callback.RemoteConfigCallback;

public abstract class ObserverRemote extends Observer{
    public SubjectRemote subjectRemote;
    public abstract void onListenerRemote(Activity activity, RemoteConfigCallback remoteConfigCallback);
}
