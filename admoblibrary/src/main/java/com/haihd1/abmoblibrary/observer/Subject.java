package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.callback.UMPResultListener;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();
    private int state;
    public int getState() {
        return state;
    }

    public void setState(Activity activity,UMPResultListener umpResultListener) {
        notifyAllObservers(activity,umpResultListener);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers(Activity activity, UMPResultListener umpResultListener) {
        for (Observer observer : observers) {
            observer.update(activity,umpResultListener);
        }
    }
}