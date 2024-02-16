package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;

public abstract class InterstitialAbstract extends AdsModel implements AdmobHelper {
    protected InterstitialAd mInterstitialAd;
    protected boolean adIsLoading;
    public abstract void showInter(Activity activity);
    protected LoadingAdsDialog adsDialog;

    protected void initializeMobileAdsSdk(Activity activity) {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(
                activity,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
//                        loadBanner(activity);
                        AdmobManager.getInstance().setIsMobileAdsInitializeCalled();
                    }
                });

    }
}
