package com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;
import android.util.Log;

import com.haihd1.admoblib.abstract_factory.AdmobHelper;
import com.haihd1.admoblib.abstract_factory.factory_method.AdmobCreator;
import com.haihd1.admoblib.abstract_factory.factory_method.AdmobFactory;
import com.haihd1.admoblib.abstract_factory.factory_method.model.TYPE;
import com.haihd1.admoblib.admob_builder.AdmobCallBack;

public class InterstitialManager {

    private InterstitialAbstract mInterAds;

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
