package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.RemoteConfigCallback;

import java.util.ArrayList;
import java.util.List;

public class SubjectRemote {
    private final List<ObserverRemote> observerRemotes = new ArrayList<>();
    public void setState(Activity activity, RemoteConfigCallback actionCallBack) {
        notifyAllObservers(activity,actionCallBack);
    }

    public void attach(ObserverRemote observer) {
        observerRemotes.add(observer);
    }

    public void remove(ObserverRemote observer) {
        observerRemotes.remove(observer);
    }

    public void notifyAllObservers(Activity activity, RemoteConfigCallback actionCallBack) {
        for (ObserverRemote observer : observerRemotes) {
            observer.onListenerRemote(activity,actionCallBack);
        }
    }
}
