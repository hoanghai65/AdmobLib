package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;

import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.observer.Subject;
import com.haihd1.abmoblibrary.utils.callback.UMPResultListener;

public abstract class InterstitialAbstract extends AdsModel implements AdmobHelper {
    protected InterstitialAd mInterstitialAd;
    protected boolean adIsLoading;
    protected boolean isNotInterSplash = true;
    protected Activity mActivity;

    protected abstract void loadAdmob(Activity activity);
    public abstract void loadInterSplash(Activity activity);
    public abstract void showInter(Activity activity);
    public abstract void showInterSplash(Activity activity);
    protected abstract void setSubject(Subject subject);
    protected abstract void setActivity(Activity activity);
    protected LoadingAdsDialog adsDialog;

}
