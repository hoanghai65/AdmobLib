package com.haihd1.admoblib.abstract_factory.factory_method.model.open_resume;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdActivity;
import com.haihd1.admoblib.admob_builder.AdmobManager;

public  class AdsApplication extends Application implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private Activity currentActivity;
    private AppOpenAbstract appOpenAdManager;
    private boolean conditionShowAds = false;
//    public abstract void initId(String id);

    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = AppOpenAdManager.getInstance();
//        Log.e("initializeMobileAdsSdk", "onCreate: " );
//        AdmobManager.getInstance().initializeMobileAdsSdk(this);

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        Log.e("aaaaaaaaaaaaa", "onStart: " );
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
        Log.e("aaaaaaaaaaaaa", "onResume: " );



    }
    /**
     * Load an app open ad.
     *
     * @param activity the activity that shows the app open ad
     */
    public void loadAd(@NonNull Activity activity) {
        // We wrap the loadAd to enforce that other classes only interact with MyApplication
        // class.
        appOpenAdManager.loadAdmob(activity);
    }

    /**
     * Shows an app open ad.
     *
     * @param activity the activity that shows the app open ad
     * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
     */
    public void showAdIfAvailable(
            @NonNull Activity activity, @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        // We wrap the showAdIfAvailable to enforce that other classes only interact with MyApplication
        // class.
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }





    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.e("aaaaaaaaaaaaa", "onActivityCreated: " + (activity.getClass().getSimpleName().equals("AdActivity"))  + "   " + ( activity instanceof  AdActivity));
        if (activity instanceof  AdActivity) return;

        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity;
        }
        if (!appOpenAdManager.isLoadingAd && !appOpenAdManager.isAdAvailable()){
            loadAd(currentActivity);
        }
        conditionShowAds = false;

    }


    @Override
    public void onActivityStarted(@NonNull Activity activity) {
    // An ad activity is started when an ad is showing, which could be AdActivity class from Google
        // SDK or another activity class implemented by a third party mediation partner. Updating the
        // currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
        // one that shows the ad.
        Log.e("aaaaaaaaaaaaa", "onActivityStarted: " + currentActivity.hashCode()  + "   " + activity.hashCode());
        Log.e("aaaaaaaaaaaaa", "onActivityStarted: " + appOpenAdManager  + "   " + conditionShowAds + " " + appOpenAdManager.enableAdsOpenResume + !appOpenAdManager.isShowingAd);

        if (activity instanceof  AdActivity) return;
        if (currentActivity != activity){
            currentActivity = activity;
            return;
        }
        if (appOpenAdManager != null && conditionShowAds && appOpenAdManager.enableAdsOpenResume && !appOpenAdManager.isShowingAd){
            if (!appOpenAdManager.listActivity.contains(activity)) {
                appOpenAdManager.showAdIfAvailable(currentActivity);
            }
        }

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityResumed: " );

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityPaused: " );
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityStopped: " );
        if (activity instanceof  AdActivity) return;
        conditionShowAds = true;
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.e("aaaaaaaaaaaaa", "onActivitySaveInstanceState: " );
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.e("aaaaaaaaaaaaa", "onActivityDestroyed: " );
    }
}
