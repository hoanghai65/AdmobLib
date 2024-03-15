package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.LoadingAdsDialog;
import com.haihd1.abmoblibrary.observer.Subject;

public abstract class RewardAbstract  extends AdsModel implements AdmobHelper {
    protected RewardedInterstitialAd rewardedInterstitialAd;
    protected RewardedAd mRewarded;
    protected boolean adIsLoading;
    protected Activity mActivity;
    protected abstract void loadAdmob(Activity activity);
    public abstract void showAdsReward(Activity activity);
    protected abstract void setSubject(Subject subject);
    protected abstract void setActivity(Activity activity);
    protected LoadingAdsDialog adsDialog;
}
