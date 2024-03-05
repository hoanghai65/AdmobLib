package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.LoadingAdsDialog;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager;
import com.haihd1.abmoblibrary.observer.Subject;

import java.util.List;

public class RewardInterstitialModel extends RewardAbstract {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;
    private Subject mSubject;
    private Activity mActivity;

    @Override
    public void initId(String id) {
        this.AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {
        this.AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup viewGroup) {

    }

    @Override
    public void loadAdmob(Activity activity) {
        if (GoogleMobileAdsConsentManager.getInstance(activity).getConsentResult(activity)) {
            AdmobManager.getInstance().initializeMobileAdsSdk(activity);

            Log.e("iiiiiiiiiiiiiiii", " loadAdmob init" + "   " + adIsLoading);
            if (adIsLoading) {
                Log.e("iiiiiiiiiiiiiiii", "sddddd");
                return;
            }
            adIsLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedInterstitialAd.load(activity, AD_UNIT_ID, adRequest, new RewardedInterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    adIsLoading = false;
                    rewardedInterstitialAd = null;
                    Log.e("rrrrrrrrrrrrr", " onAdFailedToLoad");
                    if (admobCallBack != null) {
                        admobCallBack.onAdFailedToLoad();
                    }
                    actionCallBack.onNextAction();

                }

                @Override
                public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedAd) {
                    super.onAdLoaded(rewardedAd);
                    adIsLoading = false;
                    rewardedInterstitialAd = rewardedAd;
                    if (mSubject != null) {
                        mSubject.setState(activity, actionCallBack, TYPE.INTER);
                    }
                    Log.e("rrrrrrrrrrrrr", " onAdLoaded" + "   " + rewardedAd);
                    rewardedInterstitialAd.setFullScreenContentCallback(getfullScreenContentCallback(activity));
                    admobCallBack.onAdLoaded();
                }
            });
        }
    }


    @Override
    public void reloadAdmob() {

    }

    @Override
    public void setAdmobCallBack(AdmobCallBack admobCallBack) {

    }

    @Override
    public void setActionCallBack(ActionCallBack admobCallBack) {

    }

    @Override
    public void showAdsReward(Activity activity) {
        if (rewardedInterstitialAd != null ) {
            if (adsDialog == null) {
                adsDialog = new LoadingAdsDialog(activity);
            }
            if (!adIsLoading) {
                adsDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rewardedInterstitialAd.show(activity, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                if (actionCallBack != null && actionCallBack instanceof RewardListener){
                                    ((RewardListener) actionCallBack).onEarnedReward(rewardItem);
                                }
                            }
                        });

                    }
                }, 1000L);
            }

        } else {
            Log.e("iiiiiiiiiiiiiiii", " none showInter");
            loadAdmob(activity);
            if (actionCallBack != null) {
                actionCallBack.onNextAction();
            }
        }
    }

    @Override
    protected void setSubject(Subject subject) {
        this.mSubject = subject;
    }

    @Override
    protected void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    private FullScreenContentCallback getfullScreenContentCallback(Activity activity) {
        return new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                admobCallBack.onAdClicked();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
//                mInterstitialAd = null;
                if (actionCallBack != null) {
                    actionCallBack.onNextAction();
                }
                if (adsDialog != null) {
                    adsDialog.dismiss();
                    adsDialog = null;
                }
                if (admobCallBack != null) {
                    admobCallBack.onAdClosed();
                }
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                rewardedInterstitialAd = null;
                if (adsDialog != null) {
                    adsDialog.dismiss();
                    adsDialog = null;
                }
                if (actionCallBack != null) {
                    actionCallBack.onNextAction();
                }
                Log.e("iiiiiiiiiiiiiiii", "onAdFailedToShowFullScreenContent: ");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                if (admobCallBack != null) {
                    admobCallBack.onAdImpression();
                }
            }

            @Override
            public void onAdShowedFullScreenContent() {
                loadAdmob(activity);
                Log.e("iiiiiiiiiiiiiiii", "onAdShowedFullScreenContent");
            }
        };
    }

}
