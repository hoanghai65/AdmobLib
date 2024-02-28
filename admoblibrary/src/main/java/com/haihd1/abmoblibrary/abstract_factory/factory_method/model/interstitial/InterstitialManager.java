package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;
import android.util.Log;

import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobCreator;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobFactory;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.observer.Subject;

public class InterstitialManager {

    private  InterstitialAbstract mInterAds;
    private static InterstitialManager instance;

    public static InterstitialManager getInstance() {
        synchronized (InterstitialManager.class) {
            if (instance == null) {
                instance = new InterstitialManager();

            }
        }
        return instance;
    }

    public void loadInter(Activity activity) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.INTER);
            if (admob != null) {
                mInterAds = (InterstitialAbstract) admob;
                mInterAds.initId("ca-app-pub-3940256099942544/1033173712");
                mInterAds.loadAdmob(activity);
                Log.e("zzzzzzzzzzzz", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());

        }
    }

    public void loadInter(Activity activity, String id) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.INTER);
            if (admob != null) {
                mInterAds = (InterstitialAbstract) admob;
                mInterAds.initId(id);
                mInterAds.loadAdmob(activity);
                Log.e("zzzzzzzzzzzz", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());

        }
    }

    public void loadInterSplash(Activity activity, String id) {
        try {
            if (mInterAds != null) {
                mInterAds.initId(id);
                mInterAds.isNotInterSplash = false;
                mInterAds.loadAdmob(activity);
                Log.e("zzzzzzzzzzzz", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());

        }
    }

    public void initInter(){
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.INTER);
        if (admob != null) {
            mInterAds = (InterstitialAbstract) admob;
        }
    }
    public void setSubject(Subject subject){
        mInterAds.setSubject(subject);
    }
    public void setActivity(Activity activity){
        mInterAds.setActivity(activity);
    }
    public void setActionCallBack(ActionCallBack actionCallBack){
        mInterAds.setActionCallBack(actionCallBack);
    }

    public void showInter(Activity activity) {
        if (mInterAds != null) {
            mInterAds.showInter(activity);
        }
    }
    public void showInter(Activity activity, AdmobCallBack admobCallBack) {
        if (mInterAds != null) {
            mInterAds.setAdmobCallBack(admobCallBack);
            mInterAds.showInter(activity);
        }
    }
    public void showInter(Activity activity, ActionCallBack actionCallBack) {
        if (mInterAds != null) {
            mInterAds.setActionCallBack(actionCallBack);
            mInterAds.showInter(activity);
        }
    }

    public void showInter(Activity activity, ActionCallBack actionCallBack,AdmobCallBack admobCallBack) {
        if (mInterAds != null) {
            mInterAds.setActionCallBack(actionCallBack);
            mInterAds.setAdmobCallBack(admobCallBack);
            mInterAds.showInter(activity);
        }
    }
}
