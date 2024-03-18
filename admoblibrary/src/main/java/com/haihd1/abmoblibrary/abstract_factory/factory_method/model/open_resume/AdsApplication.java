package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.google.android.gms.ads.AdActivity;

public abstract class AdsApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private Activity currentActivity;
    private AppOpenAbstract appOpenAdManager;
    private boolean conditionShowAds = false;

    protected abstract String initAppOpenResume();
    public abstract String getAppTokenAdjust();

    public abstract String getFacebookID();
    protected abstract boolean isSetUpAdjust();
//    public abstract void initId(String id);

    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = AppOpenAdManager.getInstance().getAppOpen();
        if (isSetUpAdjust()){
            setUpAdjust();
        }
        Log.e("aaaaaaaaaaaaa", "onCreate: " + this.getClass().getSimpleName());
//        AdmobManager.getInstance().initializeMobileAdsSdk(this);

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        Log.e("aaaaaaaaaaaaa", "onStart: ");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
        Log.e("aaaaaaaaaaaaa", "onResume: ");


    }
    public void setUpAdjust() {
        String environment;
        environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        AdjustConfig config = new AdjustConfig(this, getAppTokenAdjust(), environment);
        config.setLogLevel(LogLevel.VERBOSE);
        config.setFbAppId(getFacebookID());
        config.setDefaultTracker(getAppTokenAdjust());
        config.setSendInBackground(true);
        Adjust.onCreate(config);
        // Enable the SDK
        Adjust.setEnabled(true);
    }
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        appOpenAdManager.initId(initAppOpenResume());
        Log.e("aaaaaaaaaaaaa", "onActivityCreated: " + (activity.getClass().getSimpleName().equals("AdActivity")) + "   " + (activity instanceof AdActivity));
        if (!(activity instanceof AdActivity)) {
            if (!appOpenAdManager.isShowingAd) {
                currentActivity = activity;
            }
            if (!appOpenAdManager.isLoadingAd && !appOpenAdManager.isAdAvailable()) {
                appOpenAdManager.loadAdmob(currentActivity);
            }
            conditionShowAds = false;
        }

    }


    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        // An ad activity is started when an ad is showing, which could be AdActivity class from Google
        // SDK or another activity class implemented by a third party mediation partner. Updating the
        // currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
        // one that shows the ad.
        Log.e("aaaaaaaaaaaaa", "onActivityStarted: " + currentActivity.hashCode() + "   " + activity.hashCode());
        Log.e("aaaaaaaaaaaaa", "onActivityStarted: " + appOpenAdManager + "   " + conditionShowAds + " " + appOpenAdManager.enableAdsOpenResume + !appOpenAdManager.isShowingAd);

        if (!(activity instanceof AdActivity)) {
            if (currentActivity != activity) {
                currentActivity = activity;
            } else {
                if (appOpenAdManager != null && conditionShowAds && appOpenAdManager.enableAdsOpenResume && !appOpenAdManager.isShowingAd) {
                    if (!appOpenAdManager.listActivity.contains(activity)) {
                        appOpenAdManager.showAdIfAvailable(currentActivity);
                    }
                }
            }
        }

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityResumed: ");
        Adjust.onResume();

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityPaused: ");
        Adjust.onPause();
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityStopped: ");
        if (!(activity instanceof AdActivity)) {
            conditionShowAds = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.e("aaaaaaaaaaaaa", "onActivitySaveInstanceState: ");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityDestroyed: ");
    }

}
