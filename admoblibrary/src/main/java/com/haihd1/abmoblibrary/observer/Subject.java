package com.haihd1.abmoblibrary.observer;

import android.app.Activity;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private final List<Observer> observers = new ArrayList<>();

    public void setState(Activity activity,ActionCallBack actionCallBack,TYPE type) {
        notifyAllObservers(activity,actionCallBack,type);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers(Activity activity, ActionCallBack actionCallBack, TYPE type) {
        for (Observer observer : observers) {
            observer.onListenerLoadedAds(activity,actionCallBack,type);
        }
    }
}