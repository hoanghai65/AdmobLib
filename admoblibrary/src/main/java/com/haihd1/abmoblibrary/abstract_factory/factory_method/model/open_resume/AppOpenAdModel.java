package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.LoadingAdsDialog;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager;
import com.haihd1.abmoblibrary.observer.Subject;

import java.util.Date;
import java.util.List;

public class AppOpenAdModel extends AppOpenAbstract {
    private Subject mSubject;
    private Activity mActivity;

    private static final String LOG_TAG = "AppOpenAdManager";
    private String AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921";

    @Override
    public void setSubject(Subject subject){
        mSubject = subject;
    }
    public void setActivity(Activity activity){
        mActivity = activity;
    }
    @Override
    public void initId(String id) {
        AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {

    }

    @Override
    public void initLayout(ViewGroup viewGroup) {

    }


    @Override
    public void loadAdmob(Activity activity) {
        if (GoogleMobileAdsConsentManager.getInstance(activity).getConsentResult(activity)) {
            loadAds(activity);
        }
    }

    @Override
    public void reloadAdmob() {

    }

    @Override
    public void setAdmobCallBack(AdmobCallBack admobCallBack) {
        this.admobCallBack = admobCallBack;
    }

    @Override
    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }
    private long loadTime = 0;


    @Override
    public void loadAds(Context context) {
        AdmobManager.getInstance().initializeMobileAdsSdk(context);
        if (isLoadingAd || isAdAvailable()) {
            return;
        }
        isLoadingAd = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        Log.e("vvvvvvvvvvvvvv", "loadAds: " + AD_UNIT_ID);
        Log.e("initializeMobileAdsSdk", "loadAds: " + AD_UNIT_ID);

        AppOpenAd.load(context, AD_UNIT_ID, adRequest, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isLoadingAd = false;
                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                if (admobCallBack != null) {
                    admobCallBack.onAdFailedToLoad();
                }
            }

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                mAppOpenAd = appOpenAd;
                isLoadingAd = false;
                loadTime = (new Date()).getTime();
                Log.d(LOG_TAG, "onAdLoaded.");
                if (mSubject != null) {
                    mSubject.setState(mActivity, actionCallBack, TYPE.APP_OPEN);
                }
                if (admobCallBack != null) {
                    admobCallBack.onAdLoaded();
                }
            }
        });

    }

    @Override
    protected void loadAppOpenSplash(Context context,String id) {
        AD_UNIT_ID = id;
        Log.e("vvvvvvvvvvvvvv", "loadAds:aaa " + AD_UNIT_ID);
        Log.e("initializeMobileAdsSdk", "loadAds: " + AD_UNIT_ID);

        AdmobManager.getInstance().initializeMobileAdsSdk(context);
        if (isLoadingAd || isAdAvailable()) {
            return;
        }
        isLoadingAd = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        AppOpenAd.load(context, AD_UNIT_ID, adRequest, new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isLoadingAd = false;
                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                if (admobCallBack != null) {
                    admobCallBack.onAdFailedToLoad();
                }
            }

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                mAppOpenAd = appOpenAd;
                isLoadingAd = false;
                loadTime = (new Date()).getTime();
                Log.d(LOG_TAG, "onAdLoaded.");
                if (mSubject != null) {
                    mSubject.setState(mActivity, actionCallBack,TYPE.APP_OPEN);
                }
                if (admobCallBack != null) {
                    admobCallBack.onAdLoaded();
                }
            }
        });
    }

    /**
     * Check if ad was loaded more than n hours ago.
     */
    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    /**
     * Check if ad exists and can be shown.
     */
    @Override
    public boolean isAdAvailable() {
        // Ad references in the app open beta will time out after four hours, but this time limit
        // may change in future beta versions. For details, see:
        // https://support.google.com/admob/answer/9341964?hl=en
        return mAppOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the activity that shows the app open ad
     */
    @Override
    public void showAdIfAvailable(Activity activity) {
        showAdIfAvailable(
                activity,
                new AdsApplication.OnShowAdCompleteListener() {
                    @Override
                    public void onShowAdComplete() {
                        // Empty because the user will go back to the activity that shows the ad.
                    }
                });

    }

    @Override
    public void showAdIfAvailable(Activity activity, AdsApplication.OnShowAdCompleteListener onShowAdCompleteListener) {
        // If the app open ad is already showing, do not show the ad again.

        if (isShowingAd) {
            Log.d(LOG_TAG, "The app open ad is already showing.");
            return;
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        Log.d("AppOpenAdManager", "showAdIfAvailable: " + isAdAvailable());
        if (!isAdAvailable()) {
            Log.d(LOG_TAG, "The app open ad is not ready yet.");
            onShowAdCompleteListener.onShowAdComplete();
//                if (googleMobileAdsConsentManager.canRequestAds()) {
            loadAds(activity);
//                }
            return;
        }
        if (dialog == null) {
            dialog = new LoadingAdsDialog(activity);
            dialog.show();
        }
        Log.d(LOG_TAG, "Will show ad.");
        mAppOpenAd.setFullScreenContentCallback(getFullScreenContentCallback(activity, onShowAdCompleteListener));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAppOpenAd.show(activity);
            }
        }, 1000);
    }

    @Override
    public void disableAppOpenResume() {
        this.enableAdsOpenResume = false;
    }

    @Override
    public void disableAppOpenResumeActivity(Activity activity) {
        Log.e("aaaaaaaaaaaaa", "disableAppOpenResumeActivity: " + listActivity.size() + "   " + listActivity);
        if (!listActivity.contains(activity)) {
            listActivity.add(activity);
        }
    }

    @Override
    public void enableAppOpenResume() {
        this.enableAdsOpenResume = true;
    }

    @Override
    public void enableAppOpenResumeActivity(Activity activity) {

    }


    private FullScreenContentCallback getFullScreenContentCallback(Activity activity, AdsApplication.OnShowAdCompleteListener onShowAdCompleteListener) {
        return new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                if (admobCallBack != null) {
                    admobCallBack.onAdClicked();
                }
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                isShowingAd = false;
                mAppOpenAd = null;
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                loadAds(activity);
                Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");
                onShowAdCompleteListener.onShowAdComplete();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                mAppOpenAd = null;
                isShowingAd = true;
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                loadAds(activity);
                onShowAdCompleteListener.onShowAdComplete();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.d(LOG_TAG, "onAdImpression.");
                isShowingAd = true;
                if (admobCallBack != null) {
                    admobCallBack.onAdImpression();
                }
            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();
                isShowingAd = true;
                Log.d(LOG_TAG, "onAdShowedFullScreenContent.");

            }
        };
    }
}
