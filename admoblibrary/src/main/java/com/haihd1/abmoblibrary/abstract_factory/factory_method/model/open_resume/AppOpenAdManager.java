package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume;

import android.app.Activity;
import android.content.Context;

import com.haihd1.abmoblibrary.admob_builder.ActionCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobCallBack;
import com.haihd1.abmoblibrary.observer.Subject;

public class AppOpenAdManager {


    public AppOpenAbstract getAppOpen() {
        return mAppOpen;
    }

    private static AppOpenAbstract mAppOpen;

    private static AppOpenAdManager instance;

    public static AppOpenAdManager getInstance() {
        synchronized (AppOpenAdManager.class) {
            if (instance == null) {
                instance = new AppOpenAdManager();
                mAppOpen = new AppOpenAdModel();
            }
        }
        return instance;
    }

    public void setSubject(Subject subject) {
       mAppOpen.setSubject(subject);
    }



    public void setActivity(Activity activity) {
        mAppOpen.setActivity(activity);
    }

    public void initId(String id) {
//        AD_UNIT_ID = id;
        mAppOpen.initId(id);
    }

    public void loadAdmob(Activity activity) {
        loadAds(activity);
    }


    public void setAdmobCallBack(AdmobCallBack admobCallBack) {
        mAppOpen.setAdmobCallBack(admobCallBack);
    }

    public void setActionCallBack(ActionCallBack actionCallBack) {
        mAppOpen.setActionCallBack(actionCallBack);
    }

    public AppOpenAdManager() {
    }

    public void loadAds(Context context) {
        mAppOpen.loadAds(context);
    }

    public void loadAppOpenSplash(Context context, String id){
        mAppOpen.loadAppOpenSplash(context,id);
    }


    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the activity that shows the app open ad
     */
    public void showAdIfAvailable(Activity activity) {
        mAppOpen.showAdIfAvailable(activity);
    }

    public void showAdIfAvailable(Activity activity, AdsApplication.OnShowAdCompleteListener onShowAdCompleteListener) {
        mAppOpen.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    public void disableAppOpenResume() {
        mAppOpen.disableAppOpenResume();
    }

    public void disableAppOpenResumeActivity(Activity activity) {
        mAppOpen.disableAppOpenResumeActivity(activity);
    }

    public void enableAppOpenResume() {
        mAppOpen.enableAppOpenResume();
    }

    public void enableAppOpenResumeActivity(Activity activity) {
        mAppOpen.enableAppOpenResumeActivity(activity);
    }

}
