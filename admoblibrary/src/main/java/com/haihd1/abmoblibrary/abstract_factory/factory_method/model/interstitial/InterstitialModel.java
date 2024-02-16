package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.haihd1.abmoblibrary.admob_builder.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;

import java.util.List;

public class InterstitialModel extends InterstitialAbstract {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;

    @Override
    public void initId(String id) {
        AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {
        AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup frameLayout) {

    }

    @Override
    public void loadAdmob(Activity activity) {
        AdmobManager.getInstance().initializeMobileAdsSdk(activity);
        Log.e("iiiiiiiiiiiiiiii", " loadAdmob init" + "   " + adIsLoading);
        if (adIsLoading) {
            Log.e("iiiiiiiiiiiiiiii", "sddddd");
            return;
        }
        adIsLoading = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                adIsLoading = false;
                mInterstitialAd = null;
                Log.e("iiiiiiiiiiiiiiii", " onAdFailedToLoad");
                if (admobCallBack != null) {
                    admobCallBack.onAdFailedToLoad();
                }
                actionCallBack.onNextAction();

            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                adIsLoading = false;
                mInterstitialAd = interstitialAd;
                Log.e("iiiiiiiiiiiiiiii", " onAdLoaded" + "   " + mInterstitialAd);
                mInterstitialAd.setFullScreenContentCallback(getfullScreenContentCallback(activity));
                admobCallBack.onAdLoaded();
            }
        });
    }

    @Override
    public void reloadAdmob() {

    }

    @Override
    public void setAdmobCallBack(AdmobCallBack admobCallBack) {
        this.admobCallBack = admobCallBack;
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
                Log.e("iiiiiiiiiiiiiiii", " onAdDismissedFullScreenContent" + actionCallBack);
                admobCallBack.onAdClosed();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                mInterstitialAd = null;
                if (adsDialog != null) {
                    adsDialog.dismiss();
                    adsDialog = null;
                }
                actionCallBack.onNextAction();
                Log.e("iiiiiiiiiiiiiiii", "onAdFailedToShowFullScreenContent: ");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                admobCallBack.onAdImpression();
            }

            @Override
            public void onAdShowedFullScreenContent() {
                loadAdmob(activity);
                Log.e("iiiiiiiiiiiiiiii", "onAdShowedFullScreenContent");
            }
        };
    }

    @Override
    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }

    @Override
    public void showInter(Activity activity) {
        if (mInterstitialAd != null) {
            Log.e("iiiiiiiiiiiiiiii", " showInter" + "   " + adIsLoading);
            if (adsDialog == null) {
                adsDialog = new LoadingAdsDialog(activity);
            }
            if (!adIsLoading) {
                adsDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mInterstitialAd.show(activity);
                    }
                }, 2000L);
            }

        } else {
            Log.e("iiiiiiiiiiiiiiii", " none showInter");
            loadAdmob(activity);
        }
    }
}
